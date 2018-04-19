package cn.m2home;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableMap;
import net.sourceforge.pmd.PMDConfiguration;
import net.sourceforge.pmd.SourceCodeProcessor;
import net.sourceforge.pmd.lang.LanguageVersion;
import net.sourceforge.pmd.lang.java.JavaLanguageModule;
import org.sonar.api.batch.fs.internal.DefaultFileSystem;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.plugins.java.Java;
import org.sonar.plugins.java.api.JavaResourceLocator;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author zhangpengyue
 * @date 2018/4/19
 */
public class ThreadCustomSensor implements Sensor {

    private static Logger logger = Loggers.get(Sensor.class);

    private static final Map<String, String> JAVA_VERSIONS = ImmutableMap.<String, String>builder()
            .put("1.1", "1.3")
            .put("1.2", "1.3")
            .put("5", "1.5")
            .put("6", "1.6")
            .put("7", "1.7")
            .put("8", "1.8")
            .build();

    private JavaResourceLocator javaResourceLocator;

    /**
     * Populate {@link SensorDescriptor} of this sensor.
     *
     * @param descriptor
     */
    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("ThreadCustomSensor")
                .onlyOnLanguage(Java.KEY);
    }

    /**
     * The actual sensor code.
     *
     * @param context
     */
    @Override
    public void execute(SensorContext context) {
        String fileName = "";
        DefaultFileSystem fileSystem = new DefaultFileSystem(new File("."));
        PMDConfiguration configuration = new PMDConfiguration();
        configuration.setDefaultLanguageVersion(languageVersion("1.8"));
        configuration.setClassLoader(Thread.currentThread().getContextClassLoader());
        configuration.setSourceEncoding(fileSystem.encoding().name());
        SourceCodeProcessor processor = new SourceCodeProcessor(configuration);
//        processor.processSourceCode();
    }

    List<File> listFile(SensorContext context) {
        final String dir = context.settings().getString("sonar.sources");
        final File file = new File(dir);
        return null;
    }

    static LanguageVersion languageVersion(String javaVersion) {
        String version = normalize(javaVersion);
        LanguageVersion languageVersion = new JavaLanguageModule().getVersion(version);
        if (languageVersion == null) {
            throw new IllegalArgumentException("Unsupported Java version for PMD: " + version);
        }
        logger.info("Java version: " + version);
        return languageVersion;
    }

    private static String normalize(String version) {
        return Functions.forMap(JAVA_VERSIONS, version).apply(version);
    }

//    private URLClassLoader createClassloader() {
//        Collection<File> classpathElements = javaResourceLocator.classpath();
//        List<URL> urls = Lists.newArrayList();
//        for (File file : classpathElements) {
//            try {
//                urls.add(file.toURI().toURL());
//            } catch (MalformedURLException e) {
//                throw new IllegalStateException("Fail to create the project classloader. Classpath element is invalid: " + file, e);
//            }
//        }
//        return new URLClassLoader(urls.toArray(new URL[urls.size()]), null);
//    }
}
