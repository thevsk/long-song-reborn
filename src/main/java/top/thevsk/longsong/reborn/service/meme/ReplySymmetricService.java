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
public class ReplySymmetricService extends BaseMemeService implements IMessageService {

    @Override
    public void groupMessage(GroupMessageEvent event, ApiSender sender) {
        if (!EventUtils.isReply(event)) return;
        Integer messageId = EventUtils.getReply(event);
        if (messageId == null) return;
        GroupMessageEvent replyEvent = Cache.getGroupMessage(messageId);
        if (replyEvent == null) return;
        String image = EventUtils.getImage0(replyEvent);
        if (StrUtil.isBlank(image)) return;
        String arg = null;
        if (EventUtils.containsEquals(event, "对称左")) {
            arg = "left";
        }
        if (EventUtils.containsEquals(event, "对称右")) {
            arg = "right";
        }
        if (EventUtils.containsEquals(event, "对称上")) {
            arg = "top";
        }
        if (EventUtils.containsEquals(event, "对称下")) {
            arg = "bottom";
        }
        if (StrUtil.isBlank(arg)) return;
        File file = downloadImage(image, messageId.toString());
        if (file == null || !file.exists()) return;
        String symmetric = symmetric(file, arg);
        if (StrUtil.isBlank(symmetric)) return;
        sender.sendGroupMsg(
                event.getGroupId(),
                new Message()
                        .addMsg(reply(event))
                        .addMsg(tempImage(symmetric))
        );
    }
}
