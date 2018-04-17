package cn.m2home;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.plugins.java.Java;
import org.sonar.squidbridge.rules.ExternalDescriptionLoader;

/**
 * @author zhangpengyue
 * @date 2018/4/12
 */
public class CustomRulesDefinition implements RulesDefinition {
    public static final String REPOSITORY_KEY = "p3c-sonar-repo";
    public static final String PLUGIN_NAME = "p3c-sonar-repo";
    public static final String XPATH_CLASS = "net.sourceforge.pmd.lang.rule.XPathRule";
    public static final String XPATH_EXPRESSION_PARAM = "xpath";
    public static final String XPATH_MESSAGE_PARAM = "message";
    private final RulesDefinitionXmlLoader xmlLoader;
    private static Logger logger = Loggers.get(CustomRulesDefinition.class);

    public CustomRulesDefinition(RulesDefinitionXmlLoader xmlLoader) {
        this.xmlLoader = xmlLoader;
    }

    @Override
    public void define(Context context) {

//        final InputStream stream = getClass().getResourceAsStream("/rules.xml");
        final NewRepository repository = context.createRepository(REPOSITORY_KEY, Java.KEY).setName(REPOSITORY_KEY);

        /*try {
            if (stream != null) {
                xmlLoader.load(repository, stream, Charsets.UTF_8);
            }
            repository.done();
        } finally {
            IOUtils.closeQuietly(stream);
        }*/

        extractRulesData(repository, "/rules.xml", "/html");

        repository.done();
    }

    static void extractRulesData(NewRepository repository, String xmlRulesFilePath, String htmlDescriptionFolder) {
        RulesDefinitionXmlLoader ruleLoader = new RulesDefinitionXmlLoader();
        ruleLoader.load(repository, CustomRulesDefinition.class.getResourceAsStream(xmlRulesFilePath), "UTF-8");
        ExternalDescriptionLoader.loadHtmlDescriptions(repository, htmlDescriptionFolder);
//        PropertyFileLoader.loadNames(repository, CustomRulesDefinition.class.getResourceAsStream("/org/sonar/l10n/pmd.properties"));
//        SqaleXmlLoader.load(repository, "/com/sonar/sqale/pmd-model.xml");
    }

}
