package top.thevsk.longsong.reborn.service.meme;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSONObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.thevsk.longsong.reborn.service.BaseService;

import java.io.File;
import java.util.UUID;

@Service
public class BaseMemeService extends BaseService {

    @Value("${meme.server}")
    private String memeServer;
    @Value("${material.temp-path}")
    private String tempPath;

    String goodNews(String text) {
        return send("good_news", null, text, null);
    }

    String symmetric(File file, String direction) {
        return send("symmetric", new File[]{file}, null, new JSONObject() {{
            put("direction", direction);
        }});
    }

    String doQQ(Long qq1, Long qq2) {
        return send("do", new File[]{downloadFaceImage(qq1), downloadFaceImage(qq2)}, null, null);
    }

    String canCanNeedQQ(Long qq1, Long qq2) {
        return send("can_can_need", new File[]{downloadFaceImage(qq1), downloadFaceImage(qq2)}, null, null);
    }

    String beheadQQ(Long qq) {
        return send("behead", new File[]{downloadFaceImage(qq)}, null, null);
    }

    String empathyQQ(Long qq) {
        return send("empathy", new File[]{downloadFaceImage(qq)}, null, null);
    }

    String petPetQQ(Long qq) {
        return send("petpet", new File[]{downloadFaceImage(qq)}, null, new JSONObject() {{
            put("circle", "true");
        }});
    }

    String prprQQ(Long qq) {
        return send("prpr", new File[]{downloadFaceImage(qq)}, null, null);
    }

    String shootQQ(Long qq) {
        return send("shoot", new File[]{downloadFaceImage(qq)}, null, null);
    }

    String startQQ(Long qq, String texts) {
        return send("genshin_start", new File[]{downloadFaceImage(qq)}, texts, null);
    }

    String marriageQQ(Long qq) {
        return send("marriage", new File[]{downloadFaceImage(qq)}, null, null);
    }

    String divorceQQ(Long qq) {
        return send("divorce", new File[]{downloadFaceImage(qq)}, null, null);
    }

    String eatQQ(Long qq) {
        return send("eat", new File[]{downloadFaceImage(qq)}, null, null);
    }

    private String send(String action, File[] files, String texts, JSONObject args) {
        MultipartBody.Builder body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        if (files != null && files.length > 0) {
            for (File file : files) {
                RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                body.addFormDataPart("images", file.getName(), fileBody);
            }
        }
        if (texts != null) {
            body.addFormDataPart("texts", texts);
        }
        if (args != null) {
            body.addFormDataPart("args", args.toString());
        }
        Request request = new Request.Builder()
                .url(memeServer + "/" + action + "/")
                .post(body.build())
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            String name = UUID.randomUUID().toString().replace("-", "") + ".jpg";
            if (response.isSuccessful() && response.body() != null) {
                File result = new File(tempPath + File.separator + name);
                FileUtil.writeFromStream(response.body().byteStream(), result);
            } else {
                return null;
            }
            return name;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
