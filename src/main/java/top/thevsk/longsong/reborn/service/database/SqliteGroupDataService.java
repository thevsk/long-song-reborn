package top.thevsk.longsong.reborn.service.database;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.utils.SQLiteUtils;
import top.thevsk.longsong.reborn.utils.TimeUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

@Service
public class SqliteGroupDataService {

    @Value("${sqlite.path}")
    private String sqlitePath;
    private SQLiteUtils sqLite;

    public void init() {
        try {
            sqLite = new SQLiteUtils(sqlitePath + File.separator + "group.db");
            sqLite.createTable("message", new ArrayList<SQLiteUtils.TableColumn>() {
                {
                    add(new SQLiteUtils.TableColumn("messageId"));
                    add(new SQLiteUtils.TableColumn("groupId"));
                    add(new SQLiteUtils.TableColumn("userId"));
                    add(new SQLiteUtils.TableColumn("nickname"));
                    add(new SQLiteUtils.TableColumn("date"));
                    add(new SQLiteUtils.TableColumn("event"));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GroupMessageEvent getGroupMessage(Integer messageId, Long groupId) {
        try {
            Map<String, Object> result = sqLite.executeQueryMap(
                    "select * from message where messageId = ? and groupId = ? limit 1",
                    messageId, groupId
            );
            if (result == null) return null;
            return JSONObject.parse(result.get("event").toString()).toJavaObject(GroupMessageEvent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveGroupMessage(GroupMessageEvent event) {
        try {
            sqLite.execute(
                    "insert into message values(?, ?, ?, ?, ?, ?)",
                    event.getMessageId(),
                    event.getGroupId(),
                    event.getUserId(),
                    event.getAnonymous() != null ? event.getAnonymous().getName() : event.getSender().getNickname(),
                    LocalDateTimeUtil.format(
                            LocalDateTimeUtil.of(event.getTime() * 1000),
                            "yyyy-MM-dd HH:mm:ss"
                    ),
                    JSON.toJSONString(event)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
