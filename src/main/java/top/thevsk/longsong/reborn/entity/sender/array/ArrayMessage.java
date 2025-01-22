package top.thevsk.longsong.reborn.entity.sender.array;

import lombok.AllArgsConstructor;
import top.thevsk.longsong.reborn.entity.event.message.MessageEvent;
import top.thevsk.longsong.reborn.enums.ArrayMessageType;

@lombok.Data
public class ArrayMessage {

    public static ArrayMessage reply(MessageEvent event) {
        return new ArrayMessage().setType(ArrayMessageType.reply)
                .setData(
                        new ArrayMessage.Reply(
                                event.getMessageId()
                        )
                );
    }

    public static ArrayMessage image(String path) {
        return new ArrayMessage()
                .setType(ArrayMessageType.image)
                .setData(new ArrayMessage.Image(path));
    }

    public static ArrayMessage localImage(String file) {
        return new ArrayMessage()
                .setType(ArrayMessageType.image)
                .setData(new ArrayMessage.Image("*WEB_PATH*/material/" + file));
    }

    public static ArrayMessage text(String text) {
        return new ArrayMessage()
                .setType(ArrayMessageType.text)
                .setData(new ArrayMessage.Text(text));
    }

    private ArrayMessageType type;
    private Data data;

    public ArrayMessage setType(ArrayMessageType type) {
        this.type = type;
        return this;
    }

    public ArrayMessage setData(Data data) {
        this.data = data;
        return this;
    }

    @lombok.Data
    @AllArgsConstructor
    public static class Text extends Data {
        private String text;
    }

    @lombok.Data
    @AllArgsConstructor
    public static class Image extends Data {
        private String file;
    }

    @lombok.Data
    @AllArgsConstructor
    public static class Reply extends Data {
        private Integer id;
    }
}
