package top.thevsk.longsong.reborn.service.meme;

import org.springframework.stereotype.Service;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.entity.sender.Message;
import top.thevsk.longsong.reborn.sender.ApiSender;
import top.thevsk.longsong.reborn.service.interfaces.IMessageService;
import top.thevsk.longsong.reborn.utils.EventUtils;

@Service
public class StartService extends BaseMemeService implements IMessageService {

    @Override
    public void groupMessage(GroupMessageEvent event, ApiSender sender) {
        if (!EventUtils.startsWith(event, "启动")) return;
        if (EventUtils.atMe(event)) return;
        Long qq = EventUtils.getAt0(event);
        if (qq == null) return;
        String texts = "启动！";
        String groupMemberName = sender.getGroupMemberName(event.getGroupId(), qq);
        if (groupMemberName.length() <= 8) {
            texts = groupMemberName + "，启动！";
        }
        sender.sendGroupMsg(
                event.getGroupId(),
                new Message()
                        .addMsg(reply(event))
                        .addMsg(tempImage(startQQ(qq, texts)))
        );
    }
}
