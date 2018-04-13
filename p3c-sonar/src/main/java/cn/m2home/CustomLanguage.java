package cn.m2home;

import org.sonar.api.resources.AbstractLanguage;

/**
 * @author zhangpengyue
 * @date 2018/4/12
 */
public class CustomLanguage extends AbstractLanguage {
    public static final String KEY = "custom-key";
    public static final String NAME = "custom-name";

    public CustomLanguage() {
        super(KEY, NAME);
    }

    @Override
    public String[] getFileSuffixes() {
        return new String[]{"csm.xml"};
    }

}
