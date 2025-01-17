package top.thevsk.longsong.reborn.utils;

import org.springframework.stereotype.Component;
import top.thevsk.longsong.reborn.service.interfaces.IMessageService;
import top.thevsk.longsong.reborn.service.interfaces.INoticeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ServiceBeanUtils {

    private List<IMessageService> messageServiceList;
    private List<INoticeService> noticeServiceList;

    public List<IMessageService> getMessageServiceList() {
        return messageServiceList;
    }

    public List<INoticeService> getNoticeServiceList() {
        return noticeServiceList;
    }

    public void init() {
        messageServiceList = new ArrayList<>();
        noticeServiceList = new ArrayList<>();
        Map<String, IMessageService> messageBeans = SpringUtil.getBeansOfType(IMessageService.class);
        for (String key : messageBeans.keySet()) {
            messageServiceList.add(messageBeans.get(key));
        }
        Map<String, INoticeService> noticeBeans = SpringUtil.getBeansOfType(INoticeService.class);
        for (String key : noticeBeans.keySet()) {
            noticeServiceList.add(noticeBeans.get(key));
        }
    }
}
