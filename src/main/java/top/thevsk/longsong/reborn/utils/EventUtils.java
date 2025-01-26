package top.thevsk.longsong.reborn.utils;

import top.thevsk.longsong.reborn.entity.event.ArrayMessage;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.entity.event.message.MessageEvent;
import top.thevsk.longsong.reborn.entity.event.notice.GroupNoticeEvent;
import top.thevsk.longsong.reborn.enums.ArrayMessageType;

public class EventUtils {

    public static String getImage0(MessageEvent event) {
        for (ArrayMessage arrayMessage : event.getMessage()) {
            if (arrayMessage.getType().equals(ArrayMessageType.image)) {
                return arrayMessage.getData().getString("url");
            }
        }
        return null;
    }

    public static String getText0(MessageEvent event) {
        for (ArrayMessage arrayMessage : event.getMessage()) {
            if (arrayMessage.getType().equals(ArrayMessageType.text)) {
                return arrayMessage.getData().getString("text");
            }
        }
        return null;
    }

    public static Long getAt0(MessageEvent event) {
        for (ArrayMessage arrayMessage : event.getMessage()) {
            if (arrayMessage.getType().equals(ArrayMessageType.at)) {
                return Long.valueOf(arrayMessage.getData().getString("qq"));
            }
        }
        return null;
    }

    public static boolean startsWith(MessageEvent event, String token) {
        if (event.getMessage().size() > 0) {
            if (event.getMessage().get(0).getType().equals(ArrayMessageType.text)) {
                return event.getMessage().get(0).getData().getString("text").trim().startsWith(token);
            }
        }
        return false;
    }

    public static String startsWithIn(MessageEvent event, Iterable<String> tokens) {
        for (String token : tokens) {
            if (event.getMessage().size() > 0) {
                if (event.getMessage().get(0).getType().equals(ArrayMessageType.text)) {
                    if (event.getMessage().get(0).getData().getString("text").trim().startsWith(token)) {
                        return token;
                    }
                }
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

    public static Integer getReply(MessageEvent event) {
        for (ArrayMessage arrayMessage : event.getMessage()) {
            if (arrayMessage.getType().equals(ArrayMessageType.reply)) {
                return Integer.valueOf(arrayMessage.getData().getString("id"));
            }
        }
        return null;
    }

    public static boolean isReply(MessageEvent event) {
        if (event.getMessage().size() > 0) {
            return event.getMessage().get(0).getType().equals(ArrayMessageType.reply);
        }
        return false;
    }

    public static String containsEqualsIn(MessageEvent event, Iterable<String> tokens) {
        for (String token : tokens) {
            for (ArrayMessage arrayMessage : event.getMessage()) {
                if (arrayMessage.getType().equals(ArrayMessageType.text)) {
                    if (arrayMessage.getData().getString("text").trim().equals(token)) {
                        return token;
                    }
                }
            }
        }
        return null;
    }

    public static boolean containsEquals(MessageEvent event, String token) {
        for (ArrayMessage arrayMessage : event.getMessage()) {
            if (arrayMessage.getType().equals(ArrayMessageType.text)) {
                if (arrayMessage.getData().getString("text").trim().equals(token)) {
                    return true;
                }
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
