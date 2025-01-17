package top.thevsk.longsong.reborn.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Service;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.entity.sender.Message;
import top.thevsk.longsong.reborn.entity.sender.array.ArrayMessage;
import top.thevsk.longsong.reborn.sender.ApiSender;
import top.thevsk.longsong.reborn.service.interfaces.IMessageService;
import top.thevsk.longsong.reborn.utils.EventUtils;
import top.thevsk.longsong.reborn.utils.NumberUtils;
import top.thevsk.longsong.reborn.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FuckRandomService implements IMessageService {

    private Map<Long, List<JSONObject>> cache = new HashMap<>();

    @Override
    public void groupMessage(GroupMessageEvent event, ApiSender sender) {
        if (!EventUtils.equals(event, "草群友")) return;
        if (cache.get(event.getGroupId()) == null) {
            JSONArray groupMemberList = sender.getGroupMemberList(event.getGroupId()).getJSONArray("data");
            List<JSONObject> list = new ArrayList<>();
            for (int i = 0; i < groupMemberList.size(); i++) {
                JSONObject object = groupMemberList.getJSONObject(i);
                if (object.getInteger("last_sent_time") < TimeUtils.dateNDaysAgo(30)) continue;
                list.add(object);
            }
            cache.put(event.getGroupId(), list);
        }
        int size = cache.get(event.getGroupId()).size();
        if (size == 0) return;
        int random = NumberUtils.random(0, size - 1);
        String name =
                StrUtil.isBlank(cache.get(event.getGroupId()).get(random).getString("card")) ?
                        cache.get(event.getGroupId()).get(random).getString("nickname") :
                        cache.get(event.getGroupId()).get(random).getString("card");
        String qq = cache.get(event.getGroupId()).get(random).getString("user_id");
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
