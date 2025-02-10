package top.thevsk.longsong.reborn.sender;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.thevsk.longsong.reborn.entity.BotException;
import top.thevsk.longsong.reborn.entity.sender.Message;
import top.thevsk.longsong.reborn.utils.HttpUtils;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ApiSender {

    @Value("${onebot.server}")
    private String onebotServer;

    private String selfNickname = "";

    public String getSelfNickname() {
        if (selfNickname == null) {
            JSONObject loginInfo = send("get_login_info", new JSONObject());
            selfNickname = loginInfo.getJSONObject("data").getString("nickname");
        }
        return selfNickname;
    }

    public void markAllAsRead() {
        send("_mark_all_as_read", null);
    }

    public String getGroupMemberName(Long groupId, Long userId) {
        JSONObject groupMemberInfo = getGroupMemberInfo(groupId, userId).getJSONObject("data");
        if (groupMemberInfo == null) return null;
        return StrUtil.isBlank(groupMemberInfo.getString("card")) ?
                groupMemberInfo.getString("nickname") :
                groupMemberInfo.getString("card");
    }

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

    public void sendGroupForwardMsg(Long groupId, Message message) {
        JSONObject data = new JSONObject();
        data.put("group_id", groupId);
        data.put("messages", message.getMsg());
        send("send_forward_msg", data);
    }

    private JSONObject send(String action, JSONObject data) {
        data = data == null ? new JSONObject() : data;
        log.info("[发送消息] action {} ", action);
        log.info("[发送消息] data {} ", data.toString());
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        Request request = new Request.Builder()
                .url(onebotServer + "/" + action)
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
            throw new BotException("sender 发送消息失败：" + strResult);
        }
        return result;
    }
}
