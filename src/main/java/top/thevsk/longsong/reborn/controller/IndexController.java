package top.thevsk.longsong.reborn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.thevsk.longsong.reborn.entity.Cache;
import top.thevsk.longsong.reborn.entity.event.Event;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.entity.event.message.MessageEvent;
import top.thevsk.longsong.reborn.entity.event.message.PrivateMessageEvent;
import top.thevsk.longsong.reborn.entity.event.notice.NoticeEvent;
import top.thevsk.longsong.reborn.entity.event.notice.PokeNoticeEvent;
import top.thevsk.longsong.reborn.sender.ApiSender;
import top.thevsk.longsong.reborn.service.interfaces.IMessageService;
import top.thevsk.longsong.reborn.service.interfaces.INoticeService;
import top.thevsk.longsong.reborn.utils.ServiceBeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/longsong/reborn")
public class IndexController {

    @Autowired
    private ServiceBeanUtils serviceBeanUtils;
    @Autowired
    private ApiSender apiSender;

    @RequestMapping
    public void index(HttpServletRequest request, HttpServletResponse response) {
        try {
            Event baseEvent = Event.toEvent(request);
            serviceHandle(baseEvent);
            response.getWriter().print("{}");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void serviceHandle(Event event) {
        if (event instanceof MessageEvent) {
            for (IMessageService iMessageService : serviceBeanUtils.getMessageServiceList()) {
                if (event instanceof GroupMessageEvent) {
                    Cache.setGroupMessage((GroupMessageEvent) event);
                    iMessageService.groupMessage((GroupMessageEvent) event, apiSender);
                } else if (event instanceof PrivateMessageEvent) {
                    iMessageService.privateMessage((PrivateMessageEvent) event, apiSender);
                }
            }
        } else if (event instanceof NoticeEvent) {
            for (INoticeService iNoticeService : serviceBeanUtils.getNoticeServiceList()) {
                if (event instanceof PokeNoticeEvent) {
                    iNoticeService.pokeNotice((PokeNoticeEvent) event, apiSender);
                }
            }
        }
    }
}
