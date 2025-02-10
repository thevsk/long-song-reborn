package top.thevsk.longsong.reborn.plugins;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
@Slf4j
public class RunCmdPlugin {

    @Autowired
    private PlugConfig plugConfig;

    public void runCmds() {
        for (String cmd : plugConfig.getCmds()) {
            try {
                Thread.sleep(1000L);
                new Thread(() -> {
                    exec(cmd);
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void exec(String cmd) {
        try {
            ProcessBuilder pb = new ProcessBuilder(cmd.split(" "));
            pb.redirectErrorStream(true);
            Process process = pb.start();
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
}
