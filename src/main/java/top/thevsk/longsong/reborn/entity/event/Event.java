package top.thevsk.longsong.reborn.entity.event;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import lombok.Data;
import top.thevsk.longsong.reborn.entity.BotException;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.entity.event.message.PrivateMessageEvent;
import top.thevsk.longsong.reborn.entity.event.notice.PokeNoticeEvent;
import top.thevsk.longsong.reborn.utils.HttpUtils;

import javax.servlet.http.HttpServletRequest;

@Data
public class Event {

    private Long time;
    private Long selfId;
    private String postType;

    public static Event toEvent(HttpServletRequest request) {
        String body = HttpUtils.readData(request);
        if (StrUtil.isBlank(body)) throw new BotException(" 空 event ");
        JSONObject data = JSONObject.parse(body);
        try {
            String postType = data.getString("post_type");
            String subType = data.getString("sub_type");
            String messageType = data.getString("message_type");
            String noticeType = data.getString("notice_type");
            switch (postType) {
                case "message":
                    switch (messageType) {
                        case "private":
                            return data.toJavaObject(PrivateMessageEvent.class, JSONReader.Feature.SupportSmartMatch);
                        case "group":
                            return data.toJavaObject(GroupMessageEvent.class, JSONReader.Feature.SupportSmartMatch);
                    }
                    return null;
                case "notice":
                    switch (noticeType) {
                        case "notify":
                            switch (subType) {
                                case "poke":
                                    return data.toJavaObject(PokeNoticeEvent.class, JSONReader.Feature.SupportSmartMatch);
                            }
                    }
                    return null;
                case "request":
                    return null;
                case "meta_event":
                    return null;
            }
        } catch (Exception e) {
            System.out.println(body);
        }
        return null;
    }
}
