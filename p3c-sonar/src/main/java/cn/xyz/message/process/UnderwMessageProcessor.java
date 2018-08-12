package cn.xyz.message.process;

import cn.xyz.message.AbstractMessageProcessor;
import cn.xyz.message.IMessageTemplateChooser;
import cn.xyz.message.MsgTemplate;
import cn.xyz.message.data.UnderwMsgData;

import java.util.List;

/**
 * @author zhangpengyue
 * @date 2018/8/12
 */
public class UnderwMessageProcessor extends AbstractMessageProcessor<UnderwMsgData> {


    private IMessageTemplateChooser<UnderwMsgData> underwMsgDataIMessageTemplateChooser;


    /**
     * 准备数据
     *
     * @param data
     */
    @Override
    protected void prepareData(UnderwMsgData data) {

    }

    /**
     * 选择模板
     *
     * @param data
     * @return
     */
    @Override
    protected List<MsgTemplate<UnderwMsgData>> chooseTemplate(UnderwMsgData data) {
        return underwMsgDataIMessageTemplateChooser.chooseTemplate(data);
    }

    @Override
    protected UnderwMsgData buildData() {
        return new UnderwMsgData();
    }
}
