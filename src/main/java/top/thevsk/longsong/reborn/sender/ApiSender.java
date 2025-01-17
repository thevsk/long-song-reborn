package top.thevsk.longsong.reborn.sender;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;
import top.thevsk.longsong.reborn.entity.BotException;
import top.thevsk.longsong.reborn.entity.sender.Message;
import top.thevsk.longsong.reborn.utils.HttpUtils;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ApiSender {

    public JSONObject getGroupMemberInfo(Long groupId, Long userId) {
        JSONObject data = new JSONObject();
        data.put("group_id", groupId);
        data.put("user_id", userId);
        return send("get_group_member_info", data);
    }

    public JSONObject getGroupMemberList(Long groupId) {
        JSONObject data = new JSONObject();
        data.put("group_id", groupId);
        return send("get_group_member_list", data);
    }

    public void sendGroupMsg(Long groupId, Message message) {
        JSONObject data = new JSONObject();
        data.put("group_id", groupId);
        data.put("message", message.getMsg());
        send("send_group_msg", data);
    }

    private JSONObject send(String action, JSONObject data) {
        log.info("[发送消息] action {} ", action);
        log.info("[发送消息] data {} ", data.toString());
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        Request request = new Request.Builder()
                .url("http://192.168.1.132:12300/" + action)
                .post(RequestBody.create(mediaType, data.toString()))
                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();
        String strResult = HttpUtils.proceedRequest(client, request);
        if (StrUtil.isBlank(strResult)) {
            throw new BotException("空 sender 返回值");
        }
        JSONObject result = JSONObject.parse(strResult);
        String status = result.getString("status");
        if ("failed".equals(status)) {
            throw new BotException("sender 发送消息失败");
        }
        return result;
    }
}