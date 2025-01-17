package top.thevsk.longsong.reborn.entity.sender;

import top.thevsk.longsong.reborn.entity.sender.array.ArrayMessage;

import java.util.ArrayList;
import java.util.List;

public class Message {

    public Message() {
        msg = new ArrayList<>();
    }

    private List<ArrayMessage> msg;

    public List<ArrayMessage> getMsg() {
        return msg;
    }

    public Message addMsg(ArrayMessage arrayMessage) {
        msg.add(arrayMessage);
        return this;
    }
}
