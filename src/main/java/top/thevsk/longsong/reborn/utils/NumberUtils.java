package top.thevsk.longsong.reborn.utils;

import java.util.Random;

public class NumberUtils {

    public static Integer random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
