package top.thevsk.longsong.reborn.service.meme;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.entity.sender.Message;
import top.thevsk.longsong.reborn.sender.ApiSender;
import top.thevsk.longsong.reborn.service.interfaces.IMessageService;
import top.thevsk.longsong.reborn.utils.EventUtils;

@Service
public class GoodNewsService extends BaseMemeService implements IMessageService {

    @Override
    public void groupMessage(GroupMessageEvent event, ApiSender sender) {
        if (!EventUtils.startsWith(event, "喜报")) return;
        String text = EventUtils.getText0(event);
        if (StrUtil.isBlank(text)) return;
        text = text.replace("喜报", "").trim();
        if (StrUtil.isBlank(text)) return;
        String result = goodNews(text);
        if (StrUtil.isBlank(result)) return;
        sender.sendGroupMsg(
                event.getGroupId(),
                new Message()
                        .addMsg(reply(event))
                        .addMsg(tempImage(result))
        );
    }
}
