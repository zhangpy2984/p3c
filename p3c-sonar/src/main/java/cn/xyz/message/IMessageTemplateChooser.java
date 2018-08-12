package cn.xyz.message;

import java.util.List;

/**
 * 消息模板选择器
 *
 * @author zhangpengyue
 * @date 2018/8/12
 */
public interface IMessageTemplateChooser<D> {
    List<MsgTemplate<D>> chooseTemplate(D data);
}
