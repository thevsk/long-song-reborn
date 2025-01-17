package top.thevsk.longsong.reborn.entity.event.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.thevsk.longsong.reborn.entity.event.ArrayMessage;
import top.thevsk.longsong.reborn.entity.event.Event;
import top.thevsk.longsong.reborn.entity.event.Sender;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MessageEvent extends Event {

    private String messageType;
    private String subType;
    private Integer messageId;
    private Long userId;
    private List<ArrayMessage> message;
    private String rawMessage;
    private Integer font;
    private Sender sender;
}
