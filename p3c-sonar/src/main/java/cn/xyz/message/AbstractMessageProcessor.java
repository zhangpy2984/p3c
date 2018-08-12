package cn.xyz.message;

import net.sf.saxon.type.ListType;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

/**
 * @author zhangpengyue
 * @date 2018/8/12
 */
public abstract class AbstractMessageProcessor<D> {
    public void sendMsg(Map<String, Object> keyMap) {
        //构建一个数据对象，后续的数据都从这个对象中通过
        D data = buildData();
        //初始化基本数据，即讲消息传递过来的数据给初始化
        initData(data, keyMap);
        //准备必要数据
        prepareData(data);
        List<MsgTemplate<D>> tplList = chooseTemplate(data);
        tplList.forEach(tpl -> {
            tpl.deal(data);
        });
    }


    /**
     * 准备数据
     *
     * @param data
     */
    protected abstract void prepareData(D data);

    /**
     * 选择模板
     *
     * @param data
     * @return
     */
    protected abstract List<MsgTemplate<D>> chooseTemplate(D data);

    /**
     * 完成发送后续处理
     *
     * @param data
     */
    protected void afterProcess(Data data) {
        //TODO 默认可记录日志
    }

    /**
     * 构建一个数据对象
     *
     * @return
     */
    protected abstract D buildData();

    /**
     * 初始化数据，将传入的keyword通过反射塞入到每个data的私有变量中
     *
     * @param data
     * @param keyMap
     */
    private void initData(D data, Map<String, Object> keyMap) {

    }
}
