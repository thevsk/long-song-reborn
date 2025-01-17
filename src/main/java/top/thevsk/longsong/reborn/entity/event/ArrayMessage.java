package top.thevsk.longsong.reborn.entity.event;

import com.alibaba.fastjson2.JSONObject;
import top.thevsk.longsong.reborn.enums.ArrayMessageType;

@lombok.Data
public class ArrayMessage {

    private ArrayMessageType type;
    private JSONObject data;
}
