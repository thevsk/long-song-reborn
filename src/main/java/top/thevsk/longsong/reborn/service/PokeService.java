package top.thevsk.longsong.reborn.service;

import org.springframework.stereotype.Service;
import top.thevsk.longsong.reborn.sender.ApiSender;
import top.thevsk.longsong.reborn.entity.event.notice.PokeNoticeEvent;
import top.thevsk.longsong.reborn.entity.sender.Message;
import top.thevsk.longsong.reborn.entity.sender.array.ArrayMessage;
import top.thevsk.longsong.reborn.enums.ArrayMessageType;
import top.thevsk.longsong.reborn.service.interfaces.INoticeService;
import top.thevsk.longsong.reborn.utils.EventUtils;

@Service
public class PokeService implements INoticeService {
    @Override
    public void pokeNotice(PokeNoticeEvent event, ApiSender sender) {
        if (!EventUtils.groupIdIn(event, 586135799L, 740751782L)) return;
        if (!event.getTargetId().equals(event.getSelfId())) return;
        sender.sendGroupMsg(
                event.getGroupId(),
                new Message().addMsg(
                        new ArrayMessage()
                                .setType(ArrayMessageType.image)
                                .setData(new ArrayMessage.Image(
                                        "http://192.168.1.132:8089/group1/default/20250117/14/07/1/0.jpg"
                                ))
                )
        );
    }
}
