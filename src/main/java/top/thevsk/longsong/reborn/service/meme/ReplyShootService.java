package top.thevsk.longsong.reborn.service.meme;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import top.thevsk.longsong.reborn.entity.Cache;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.entity.sender.Message;
import top.thevsk.longsong.reborn.sender.ApiSender;
import top.thevsk.longsong.reborn.service.interfaces.IMessageService;
import top.thevsk.longsong.reborn.utils.EventUtils;

import java.io.File;

@Service
public class ReplyShootService extends BaseMemeService implements IMessageService {

    @Override
    public void groupMessage(GroupMessageEvent event, ApiSender sender) {
        if (!EventUtils.isReply(event)) return;
        if (!EventUtils.containsEquals(event, "射")) return;
        Integer messageId = EventUtils.getReply(event);
        if (messageId == null) return;
        GroupMessageEvent replyEvent = Cache.getGroupMessage(messageId);
        if (replyEvent == null) return;
        String image = EventUtils.getImage0(replyEvent);
        if (StrUtil.isBlank(image)) return;
        File file = downloadImage(image, messageId.toString());
        if (file == null || !file.exists()) return;
        sender.sendGroupMsg(
                event.getGroupId(),
                new Message()
                        .addMsg(reply(event))
                        .addMsg(tempImage(shootFile(file)))
        );
    }
}
