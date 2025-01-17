package top.thevsk.longsong.reborn.utils;

public class TimeUtils {

    public static Integer dateNDaysAgo(int n) {
        int now = Math.toIntExact(System.currentTimeMillis() / 1000);
        return now - 60 * 60 * 24 * n;
    }
}
