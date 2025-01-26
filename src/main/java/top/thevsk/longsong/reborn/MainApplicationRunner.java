package top.thevsk.longsong.reborn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import top.thevsk.longsong.reborn.schedule.MemeSchedule;
import top.thevsk.longsong.reborn.schedule.NapCatSchedule;
import top.thevsk.longsong.reborn.service.database.SqliteGroupDataService;
import top.thevsk.longsong.reborn.utils.ServiceBeanUtils;

@Component
public class MainApplicationRunner implements ApplicationRunner {

    @Autowired
    private ServiceBeanUtils serviceBeanUtils;
    @Autowired
    private SqliteGroupDataService sqliteGroupDataService;
    @Autowired
    private NapCatSchedule napCatSchedule;
    @Autowired
    private MemeSchedule memeSchedule;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        serviceBeanUtils.init();
        sqliteGroupDataService.init();
        napCatSchedule.run();
        memeSchedule.run();
    }
}
