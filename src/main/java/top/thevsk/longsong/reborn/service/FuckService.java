package top.thevsk.longsong.reborn.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Service;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.entity.sender.Message;
import top.thevsk.longsong.reborn.entity.sender.array.ArrayMessage;
import top.thevsk.longsong.reborn.sender.ApiSender;
import top.thevsk.longsong.reborn.service.interfaces.IMessageService;
import top.thevsk.longsong.reborn.utils.EventUtils;

@Service
public class FuckService implements IMessageService {

    @Override
    public void groupMessage(GroupMessageEvent event, ApiSender sender) {
        if (!EventUtils.startsWith(event, "草")) return;
        Long qq = EventUtils.getAt0(event);
        if (qq == null) return;
        if (qq.equals(event.getSelfId())) {
            sender.sendGroupMsg(
                    event.getGroupId(),
                    new Message()
                            .addMsg(ArrayMessage.reply(event))
                            .addMsg(ArrayMessage.localImage("0.jpg"))
            );
            return;
        }
        JSONObject groupMemberInfo = sender.getGroupMemberInfo(event.getGroupId(), qq).getJSONObject("data");
        if (groupMemberInfo == null) return;
        String name =
                StrUtil.isBlank(groupMemberInfo.getString("card")) ?
                        groupMemberInfo.getString("nickname") :
                        groupMemberInfo.getString("card");
        String image = "https://q1.qlogo.cn/g?b=qq&nk=" + qq + "&s=640";
        sender.sendGroupMsg(
                event.getGroupId(),
                new Message()
                        .addMsg(ArrayMessage.reply(event))
                        .addMsg(ArrayMessage.text("成功草到了 " + name + "(" + qq + ")"))
                        .addMsg(ArrayMessage.image(image))
        );
    }
}
