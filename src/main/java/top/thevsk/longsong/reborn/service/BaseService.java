package top.thevsk.longsong.reborn.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.thevsk.longsong.reborn.entity.event.message.MessageEvent;
import top.thevsk.longsong.reborn.entity.sender.array.ArrayMessage;
import top.thevsk.longsong.reborn.enums.ArrayMessageType;

import java.io.File;
import java.util.UUID;

@Service
public class BaseService {

    @Value("${material.web-path}")
    private String webPath;
    @Value("${material.temp-path}")
    private String tempPath;

    public File downloadImage(String url, String name) {
        name = StrUtil.isBlank(name) ? UUID.randomUUID().toString().replace("-", "") : name;
        File file = new File(tempPath + File.separator + name + ".jpg");
        if (file.exists()) return file;
        HttpUtil.downloadFile(url, file);
        return file;
    }

    public File downloadFaceImage(Long qq) {
        File file = new File(tempPath + File.separator + qq + ".jpg");
        if (file.exists()) return file;
        HttpUtil.downloadFile(faceImage(qq), file);
        return file;
    }

    public String faceImage(Long qq) {
        return "https://q1.qlogo.cn/g?b=qq&nk=" + qq + "&s=640";
    }

    public ArrayMessage reply(MessageEvent event) {
        return new ArrayMessage().setType(ArrayMessageType.reply)
                .setData(
                        new ArrayMessage.Reply(
                                event.getMessageId()
                        )
                );
    }

    public ArrayMessage image(String path) {
        return new ArrayMessage()
                .setType(ArrayMessageType.image)
                .setData(new ArrayMessage.Image(path));
    }

    public ArrayMessage localImage(String file) {
        return new ArrayMessage()
                .setType(ArrayMessageType.image)
                .setData(new ArrayMessage.Image(webPath + "/local/" + file));
    }

    public ArrayMessage tempImage(String file) {
        return new ArrayMessage()
                .setType(ArrayMessageType.image)
                .setData(new ArrayMessage.Image(webPath + "/temp/" + file));
    }

    public ArrayMessage text(String text) {
        return new ArrayMessage()
                .setType(ArrayMessageType.text)
                .setData(new ArrayMessage.Text(text));
    }
}
