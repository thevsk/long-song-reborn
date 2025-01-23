package top.thevsk.longsong.reborn.entity;

import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;

import java.util.LinkedHashMap;

public class Cache {

    private static LinkedHashMap<Integer, GroupMessageEvent> groupMessageCache;

    private static final int MAX_CACHE_COUNT = 100;

    static {
        groupMessageCache = new LinkedHashMap<>();
    }

    public static GroupMessageEvent getGroupMessage(Integer messageId) {
        if (groupMessageCache.containsKey(messageId)) return groupMessageCache.get(messageId);
        return null;
    }

    public synchronized static void setGroupMessage(GroupMessageEvent event) {
        if (groupMessageCache.size() > MAX_CACHE_COUNT) {
            groupMessageCache.remove(groupMessageCache.keySet().iterator().next());
        }
        groupMessageCache.put(event.getMessageId(), event);
    }
}
