package top.thevsk.longsong.reborn.service.interfaces;

import top.thevsk.longsong.reborn.sender.ApiSender;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.entity.event.message.PrivateMessageEvent;

public interface IMessageService {

    default void groupMessage(GroupMessageEvent event, ApiSender sender) {
    }

    default void privateMessage(PrivateMessageEvent event, ApiSender sender) {
    }
}
