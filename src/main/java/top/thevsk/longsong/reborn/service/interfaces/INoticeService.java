package top.thevsk.longsong.reborn.service.interfaces;

import top.thevsk.longsong.reborn.sender.ApiSender;
import top.thevsk.longsong.reborn.entity.event.notice.PokeNoticeEvent;

public interface INoticeService {

    default void pokeNotice(PokeNoticeEvent event, ApiSender sender) {
    }
}
