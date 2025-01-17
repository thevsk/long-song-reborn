package top.thevsk.longsong.reborn.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class HttpUtils {

    public static String readData(HttpServletRequest request) {
        BufferedReader br = null;
        String tmp;
        try {
            br = request.getReader();
            String line = br.readLine();
            if (line != null) {
                StringBuilder ret = new StringBuilder();
                ret.append(line);
                while ((line = br.readLine()) != null) {
                    ret.append('\n').append(line);
                }
                tmp = ret.toString();
                return tmp;
            }
            tmp = "";
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tmp;
    }

    public static String proceedRequest(OkHttpClient client, Request request) {
        try {
            Response temp = client.newCall(request).execute();
            ResponseBody body = temp.body();
            if (temp.isSuccessful()) {
                return Objects.requireNonNull(body).string();
            } else {
                Objects.requireNonNull(temp.body()).close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
