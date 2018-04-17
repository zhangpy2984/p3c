package cn.m2home;

import org.sonar.api.Plugin;

/**
 * @author zhangpengyue
 * @date 2018/4/12
 */
public class CustomPlugin implements Plugin {
    /**
     * This method is executed at runtime when:
     * <ul>
     * <li>Web Server starts</li>
     * <li>Compute Engine starts</li>
     * <li>Scanner starts</li>
     * </ul>
     *
     * @param context
     */
    @Override
    public void define(Context context) {
        context.addExtension(CustomRulesDefinition.class)
                .addExtension(CustomProfileDefinition.class)
                .addExtension(CustomSensor.class);
    }
}
