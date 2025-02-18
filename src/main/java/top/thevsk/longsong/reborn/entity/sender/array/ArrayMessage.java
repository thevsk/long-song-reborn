package top.thevsk.longsong.reborn.entity.sender.array;

import lombok.AllArgsConstructor;
import top.thevsk.longsong.reborn.enums.ArrayMessageType;

import java.util.List;

@lombok.Data
public class ArrayMessage {

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

    @lombok.Data
    @AllArgsConstructor
    public static class Forward extends Data {
        private List<FakeNode> content;
    }

    @lombok.Data
    @AllArgsConstructor
    public static class FakeNode extends Data {
        private Long userId;
        private String nickname;
        private List<ArrayMessage> content;
    }
}
