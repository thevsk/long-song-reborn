package top.thevsk.longsong.reborn.entity.event.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.thevsk.longsong.reborn.entity.event.Anonymous;

@Data
@EqualsAndHashCode(callSuper = true)
public class GroupMessageEvent extends MessageEvent {
    private Long groupId;
    private Anonymous anonymous;
}
