package top.thevsk.longsong.reborn.entity.event.notice;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GroupNoticeEvent extends NoticeEvent {
    private Long groupId;
}
