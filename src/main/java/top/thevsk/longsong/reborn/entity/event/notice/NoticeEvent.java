package top.thevsk.longsong.reborn.entity.event.notice;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.thevsk.longsong.reborn.entity.event.Event;

@Data
@EqualsAndHashCode(callSuper = true)
public class NoticeEvent extends Event {
    private String noticeType;
    private String subType;
    private Long userId;
}
