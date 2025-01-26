package top.thevsk.longsong.reborn.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@EnableScheduling
@Slf4j
public class NapCatSchedule {

    @Value("${napcat.start}")
    private String start;
    @Value("${napcat.stop}")
    private String stop;

    private Process process = null;

    @Scheduled(cron = "46 33 * * * ?")
    public void scheduled() {
        run();
    }

    public void run() {
        stop();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void start() {
        try {
            ProcessBuilder pb = new ProcessBuilder(start.split(" "));
            pb.redirectErrorStream(true);
            process = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                log.info(line);
            }
            log.info("exitCode = " + process.waitFor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        if (process != null) {
            process.destroy();
        }
        try {
            Runtime.getRuntime().exec(stop.split(" "));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
