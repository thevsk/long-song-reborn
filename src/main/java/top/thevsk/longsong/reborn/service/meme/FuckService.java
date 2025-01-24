package top.thevsk.longsong.reborn.service.meme;

import org.springframework.stereotype.Service;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.entity.sender.Message;
import top.thevsk.longsong.reborn.sender.ApiSender;
import top.thevsk.longsong.reborn.service.interfaces.IMessageService;
import top.thevsk.longsong.reborn.utils.EventUtils;
import top.thevsk.longsong.reborn.utils.NumberUtils;

@Service
public class FuckService extends BaseMemeService implements IMessageService {

    @Override
    public void groupMessage(GroupMessageEvent event, ApiSender sender) {
        if (!EventUtils.startsWith(event, "草")) return;
        if (EventUtils.atMe(event)) return;
        Long qq = EventUtils.getAt0(event);
        if (qq == null) return;
        int random = NumberUtils.random(0, 1);
        String doRes;
        if (random == 0) {
            doRes = doQQ(event.getUserId(), qq);
        } else {
            doRes = do2QQ(event.getUserId(), qq);
        }
        sender.sendGroupMsg(
                event.getGroupId(),
                new Message()
                        .addMsg(reply(event))
                        .addMsg(tempImage(doRes))
        );
    }
}
