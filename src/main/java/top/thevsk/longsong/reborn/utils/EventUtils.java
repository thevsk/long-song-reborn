package top.thevsk.longsong.reborn.utils;

import top.thevsk.longsong.reborn.entity.event.ArrayMessage;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.entity.event.message.MessageEvent;
import top.thevsk.longsong.reborn.entity.event.notice.GroupNoticeEvent;
import top.thevsk.longsong.reborn.enums.ArrayMessageType;

public class EventUtils {

    public static boolean startsWith(MessageEvent event, String token) {
        if (event.getMessage().size() > 1) {
            if (event.getMessage().get(0).getType().equals(ArrayMessageType.text)) {
                return event.getMessage().get(0).getData().getString("text").startsWith(token);
            }
        }
        return false;
    }

    public static Long getAt0(MessageEvent event) {
        for (ArrayMessage arrayMessage : event.getMessage()) {
            if (arrayMessage.getType().equals(ArrayMessageType.at)) {
                return Long.valueOf(arrayMessage.getData().getString("qq"));
            }
        }
        return null;
    }

    public static boolean equals(MessageEvent event, String token) {
        if (event.getMessage().size() == 1) {
            if (event.getMessage().get(0).getType().equals(ArrayMessageType.text)) {
                return event.getMessage().get(0).getData().getString("text").equals(token);
            }
        }
        return false;
    }

    public static boolean atMe(GroupMessageEvent event) {
        for (ArrayMessage arrayMessage : event.getMessage()) {
            if (arrayMessage.getType().equals(ArrayMessageType.at)) {
                if (arrayMessage.getData().getString("qq").equals(String.valueOf(event.getSelfId()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean groupIdIn(GroupMessageEvent event, Long... groupIds) {
        for (Long groupId : groupIds) {
            if (event.getGroupId().equals(groupId)) return true;
        }
        return false;
    }

    public static boolean groupIdIn(GroupNoticeEvent event, Long... groupIds) {
        for (Long groupId : groupIds) {
            if (event.getGroupId().equals(groupId)) return true;
        }
        return false;
    }
}
