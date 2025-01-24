package top.thevsk.longsong.reborn.service.meme;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.entity.sender.Message;
import top.thevsk.longsong.reborn.sender.ApiSender;
import top.thevsk.longsong.reborn.service.interfaces.IMessageService;
import top.thevsk.longsong.reborn.utils.EventUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class AtService extends BaseMemeService implements IMessageService {

    static {
        tokenMap = new HashMap<String, Meme>() {{
            put("看看你的", new Meme("can_can_need", MemeType.two, true));
            put("鞭打", new Meme("lash", MemeType.two, true));

            put("砍砍你的", new Meme("behead", MemeType.one, true));
            put("摸摸", new Meme("petpet", MemeType.one, true));
            put("舔", new Meme("prpr", MemeType.one, true));
            put("射", new Meme("shoot", MemeType.one, true));
            put("结婚", new Meme("marriage", MemeType.one, true));
            put("离婚", new Meme("divorce", MemeType.one, true));
            put("吃", new Meme("eat", MemeType.one, true));
        }};
    }

    @Override
    public void groupMessage(GroupMessageEvent event, ApiSender sender) {
        String token = EventUtils.startsWithIn(event, tokenMap.keySet());
        if (StrUtil.isBlank(token)) return;
        Long qq = EventUtils.getAt0(event);
        if (qq == null) return;
        Meme meme = tokenMap.get(token);
        if (meme.isNotAtMe()) {
            if (EventUtils.atMe(event)) return;
        }
        String memeTempFileName = send(
                meme.getAction(),
                meme.getType().equals(MemeType.one)
                        ? new File[]{downloadFaceImage(qq)}
                        : new File[]{downloadFaceImage(event.getUserId()), downloadFaceImage(qq)},
                null,
                null
        );
        if (StrUtil.isBlank(memeTempFileName)) return;
        sender.sendGroupMsg(
                event.getGroupId(),
                new Message()
                        .addMsg(reply(event))
                        .addMsg(tempImage(memeTempFileName))
        );
    }

    private static Map<String, Meme> tokenMap;

    @Data
    @AllArgsConstructor
    private static class Meme {
        private String action;
        private MemeType type;
        private boolean notAtMe;
    }

    private enum MemeType {
        one, two
    }
}
