package cn.m2home;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.Report;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.processor.PmdRunnable;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.plugins.java.Java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangpengyue
 * @date 2018/4/12
 */
public class CustomSensor implements Sensor {


    private static Logger logger = Loggers.get(Sensor.class);


    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("CustomSensor")
                .onlyOnLanguage(Java.KEY);
    }

    @Override
    public void execute(SensorContext context) {
        final File reportFile = new File(context.fileSystem().workDir(), "report.xml");

        logger.info("report file path is " + context.fileSystem().workDir());

        logger.info("start run pmd analysis...");
        runPMD(context, reportFile);
        logger.info("end run pmd analysis.");

        try {
            logger.debug("contents: \n{}", IOUtils.toString(new FileInputStream(reportFile)));
        } catch (IOException e) {
            logger.error("fail to print content. error is {}", e);
        }
        logger.info("start report...");
        convertToIssues(context, doc(reportFile));
        logger.info("end report.");
    }

    private void convertToIssues(SensorContext context, Document doc) {
        final Element root = doc.getRootElement();
        final List<Element> files = root.elements("file");
        for (Element file : files) {

            final List<Element> violations = file.elements("violation");
            final String filePath = file.attributeValue("name");
            final FileSystem fs = context.fileSystem();
            final InputFile inputFile = fs.inputFile(fs.predicates().hasAbsolutePath(filePath));
            if (inputFile == null) {
                logger.info("fs predicates that there is no {}", filePath);
                continue;
            }
            for (Element violation : violations) {
                final String rule = violation.attributeValue("rule");
                final int beginLine = Integer.parseInt(violation.attributeValue("beginline"));
                final int endLine = Integer.parseInt(violation.attributeValue("endline"));
                final int beginColumn = Integer.parseInt(violation.attributeValue("begincolumn"));
                final int endColumn = Integer.parseInt(violation.attributeValue("endcolumn"));
                final NewIssue newIssue = context.newIssue()
                        .forRule(RuleKey.of(CustomRulesDefinition.REPOSITORY_KEY, rule));
                final NewIssueLocation newIssueLocation = newIssue
                        .newLocation()
                        .on(inputFile)
                        .at(inputFile.newRange(beginLine, beginColumn, endLine, endColumn))
                        .message(violation.getText());
                newIssue.at(newIssueLocation).save();
            }
        }
    }

    private Document doc(File reportFile) {
        Document doc = null;
        try {
            doc = new SAXReader().read(reportFile);
        } catch (DocumentException e) {
            logger.error("Cannot read report xml file: {}.", reportFile);
        }
        return doc;
    }

    private void runPMD(SensorContext context, File reportFile) {
//        final String dir = context.settings().getString("sonar.sources");
        logger.info("context setting {}", context.config().get("sonar.sources"));
        final String dir = context.settings().getString("sonar.sources");
        final File file = new File(dir);
        logger.info("files listed here: {}", Arrays.toString(file.listFiles()));
        String path = "rulesets/java/";
        String ruleFile = path + "ali-comment.xml";
        String[] pmdArgs = {
                "-f", "xml",
                "-R", ruleFile,
                "-d", dir,
                "-r", reportFile.getAbsolutePath(),
                "-e", context.settings().getString("sonar.sourceEncoding"),
                "-language", "xml",
                "-version", "1.0"
        };

        logger.info("the pmd args are: {}.", Arrays.toString(pmdArgs));
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
            PMD.run(pmdArgs);
        } finally {
            Thread.currentThread().setContextClassLoader(loader);
        }
    }
}
