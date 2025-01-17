package top.thevsk.longsong.reborn.entity.event.notice;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PokeNoticeEvent extends GroupNoticeEvent {
    private Long targetId;
}
