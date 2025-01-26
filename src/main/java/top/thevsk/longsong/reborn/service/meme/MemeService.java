package top.thevsk.longsong.reborn.service.meme;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import org.springframework.stereotype.Service;
import top.thevsk.longsong.reborn.entity.event.message.GroupMessageEvent;
import top.thevsk.longsong.reborn.entity.sender.Message;
import top.thevsk.longsong.reborn.sender.ApiSender;
import top.thevsk.longsong.reborn.service.interfaces.IMessageService;
import top.thevsk.longsong.reborn.utils.EventUtils;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MemeService extends BaseMemeService implements IMessageService {

    static {
        tokenMap = new LinkedHashMap<String, Meme>() {{
            put("看看你的", new Meme("can_can_need", MemeType.two));
            put("鞭打", new Meme("lash", MemeType.two));
            put("草", new Meme("do", MemeType.two));
            put("揍", new Meme("beat_up", MemeType.two));
            put("遇到困难请拨打", new Meme("call_110", MemeType.two));
            put("击剑", new Meme("fencing", MemeType.two));
            put("抱", new Meme("hug", MemeType.two));
            put("采访", new Meme("interview", MemeType.two));
            put("亲", new Meme("kiss", MemeType.two));
            put("撕", new Meme("rip", MemeType.two));
            put("贴", new Meme("rub", MemeType.two));
            put("上坟", new Meme("tomb_yeah", MemeType.two));

            put("砍砍你的", new Meme("behead", MemeType.one, true, true));
            put("摸摸", new Meme("petpet", MemeType.one, true, true));
            put("舔", new Meme("prpr", MemeType.one, true, true));
            put("射", new Meme("shoot", MemeType.one, true, true));
            put("结婚", new Meme("marriage", MemeType.one, true, true));
            put("离婚", new Meme("divorce", MemeType.one, true, true));
            put("吃", new Meme("eat", MemeType.one, true, true));
            put("换位思考", new Meme("empathy", MemeType.one, true, true));
            put("飞机杯", new Meme("fleshlight", MemeType.one, true, true));
            put("禁止", new Meme("forbid", MemeType.one, true, true));
            put("抓", new Meme("grab", MemeType.one, true, true));
            put("打胶", new Meme("jerk_off", MemeType.one, true, true));
            put("撅", new Meme("little_do", MemeType.one, true, true));
            put("双手", new Meme("stretch", MemeType.one, true, true));
            put("添乱", new Meme("add_chaos", MemeType.one, true, true));
            put("上瘾", new Meme("addiction", MemeType.one, true, true));
            put("一样", new Meme("alike", MemeType.one, true, true));
            put("戒导", new Meme("abstinence", MemeType.one, true, true));
            put("二次元入口", new Meme("acg_entrance", MemeType.one, true, true));
            put("一直", new Meme("always", MemeType.one, true, true));
            put("防诱拐", new Meme("anti_kidnap", MemeType.one, true, true));
            put("阿尼亚喜欢", new Meme("anya_suki", MemeType.one, true, true));
            put("鼓掌", new Meme("applaud", MemeType.one, true, true));
            put("阿罗娜扔", new Meme("arona_throw", MemeType.one, true, true));
            put("打工人", new Meme("back_to_work", MemeType.one, true, true));
            put("拍头", new Meme("beat_head", MemeType.one, true, true));
            put("啃", new Meme("bite", MemeType.one, true, true));
            put("高血压", new Meme("blood_pressure", MemeType.one, true, true));
            put("波奇手稿", new Meme("bocchi_draft", MemeType.one, true, true));
            put("咖波画", new Meme("capoo_draw", MemeType.one, true, true));
            put("咖波指", new Meme("capoo_point", MemeType.one, true, true));
            put("咖波撕", new Meme("capoo_rip", MemeType.one, true, true));
            put("咖波蹭", new Meme("capoo_rub", MemeType.one, true, true));
            put("咖波炖", new Meme("capoo_stew", MemeType.one, true, true));
            put("咖波撞", new Meme("capoo_strike", MemeType.one, true, true));
            put("字符画", new Meme("charpic", MemeType.one, true, true));
            put("追列车", new Meme("chase_train", MemeType.one, true, true));
            put("鼠鼠搓", new Meme("clauvio_twist", MemeType.one, true, true));
            put("小丑", new Meme("clown", MemeType.one, true, true));
            put("小丑面具", new Meme("clown_mask", MemeType.one, true, true));
            put("迷惑", new Meme("confuse", MemeType.one, true, true));
            put("兑换券", new Meme("coupon", MemeType.one, true, true));
            put("捂脸", new Meme("cover_face", MemeType.one, true, true));
            put("群青", new Meme("cyan", MemeType.one, true, true));
            put("像样的亲亲", new Meme("decent_kiss", MemeType.one, true, true));
            put("恐龙", new Meme("dinosaur", MemeType.one, true, true));
            put("注意力涣散", new Meme("distracted", MemeType.one, true, true));
            put("狗都不玩", new Meme("dog_dislike", MemeType.one, true, true));
            put("管人痴", new Meme("dog_of_vtb", MemeType.one, true, true));
            put("不要靠近", new Meme("dont_go_near", MemeType.one, true, true));
            put("别碰", new Meme("dont_touch", MemeType.one, true, true));
            put("意若思镜", new Meme("erised_mirror", MemeType.one, true, true));
            put("灰飞烟灭", new Meme("fade_away", MemeType.one, true, true));
            put("我打宿傩", new Meme("fight_with_sunuo", MemeType.one, true, true));
            put("红温", new Meme("flush", MemeType.one, true, true));
            put("关注", new Meme("follow", MemeType.one, true, true));
            put("芙莉莲拿", new Meme("frieren_take", MemeType.one, true, true));
            put("哈哈镜", new Meme("funny_mirror", MemeType.one, true, true));
            put("垃圾", new Meme("garbage", MemeType.one, true, true));
            put("猩猩扔", new Meme("gorilla_throw", MemeType.one, true, true));
            put("锤", new Meme("hammer", MemeType.one, true, true));
            put("凉宫春日举", new Meme("haruhi_raise", MemeType.one, true, true));
            put("打穿", new Meme("hit_screen", MemeType.one, true, true));
            put("抱紧", new Meme("hold_tight", MemeType.one, true, true));
            put("抱大腿", new Meme("hug_leg", MemeType.one, true, true));
            put("胡桃啃", new Meme("hutao_bite", MemeType.one, true, true));
            put("杰瑞盯", new Meme("jerry_stare", MemeType.one, true, true));
            put("啾啾", new Meme("jiujiu", MemeType.one, true, true));
            put("跳", new Meme("jump", MemeType.one, true, true));
            put("凯露指", new Meme("karyl_point", MemeType.one, true, true));
            put("压岁钱不要交给", new Meme("keep_your_money", MemeType.one, true, true));
            put("踢球", new Meme("kick_ball", MemeType.one, true, true));
            put("卡比锤", new Meme("kirby_hammer", MemeType.one, true, true));
            put("可莉吃", new Meme("klee_eat", MemeType.one, true, true));
            put("敲", new Meme("knock", MemeType.one, true, true));
            put("泉此方看", new Meme("konata_watch", MemeType.one, true, true));
            put("偷学", new Meme("learn", MemeType.one, true, true));
            put("左右横跳", new Meme("left_right_jump", MemeType.one, true, true));
            put("让我进去", new Meme("let_me_in", MemeType.one, true, true));
            put("舔糖", new Meme("lick_candy", MemeType.one, true, true));
            put("等价无穷小", new Meme("lim_x_0", MemeType.one, true, true));
            put("听音乐", new Meme("listen_music", MemeType.one, true, true));
            put("小天使", new Meme("little_angel", MemeType.one, true, true));
            put("加载中", new Meme("loading", MemeType.one, true, true));
            put("看扁", new Meme("look_flat", MemeType.one, true, true));
            put("寻狗启事", new Meme("lost_dog", MemeType.one, true, true));
            put("永远爱你", new Meme("love_you", MemeType.one, true, true));
            put("真寻看书", new Meme("mahiro_readbook", MemeType.one, true, true));
            put("旅行伙伴觉醒", new Meme("maimai_awaken", MemeType.one, true, true));
            put("旅行伙伴加入", new Meme("maimai_join", MemeType.one, true, true));
            put("米哈游", new Meme("mihoyo", MemeType.one, true, true));
            put("上香", new Meme("mourning", MemeType.one, true, true));
            put("我老婆", new Meme("my_wife", MemeType.one, true, true));
            put("纳西妲啃", new Meme("nahida_bite", MemeType.one, true, true));
            put("亚文化取名机", new Meme("name_generator", MemeType.one, true, true));
            put("需要", new Meme("need", MemeType.one, true, true));
            put("无响应", new Meme("no_response", MemeType.one, true, true));
            put("我推的网友", new Meme("oshi_no_ko", MemeType.one, true, true));
            put("out", new Meme("out", MemeType.one, true, true));
            put("加班", new Meme("overtime", MemeType.one, true, true));
            put("这像画吗", new Meme("paint", MemeType.one, true, true));
            put("小画家", new Meme("painter", MemeType.one, true, true));
            put("推锅", new Meme("pass_the_buck", MemeType.one, true, true));
            put("拍", new Meme("pat", MemeType.one, true, true));
            put("佩佩举", new Meme("pepe_raise", MemeType.two, true, true));
            put("完美", new Meme("perfect", MemeType.one, true, true));
            put("摸", new Meme("petpet", MemeType.one, true, true, new JSONObject() {{
                put("circle", true);
            }}));
            put("捏", new Meme("pinch", MemeType.one, true, true));
            put("像素化", new Meme("pixelate", MemeType.one, true, true));
            put("普拉娜吃", new Meme("plana_eat", MemeType.one, true, true));
            put("普拉娜舔", new Meme("plana_eat", MemeType.one, true, true));
            put("顶", new Meme("play", MemeType.one, true, true));
            put("玩", new Meme("play", MemeType.one, true, true));
            put("一起玩", new Meme("play_together", MemeType.one, true, true));
            put("土豆", new Meme("potato", MemeType.one, true, true));
            put("捣", new Meme("pound", MemeType.one, true, true));
            put("打印", new Meme("printing", MemeType.one, true, true));
            put("舔屏", new Meme("prpr", MemeType.one, true, true));
            put("prpr", new Meme("prpr", MemeType.one, true, true));
            put("打拳", new Meme("punch", MemeType.one, true, true));
            put("四棱锥", new Meme("pyramid", MemeType.one, true, true));
            put("金字塔", new Meme("pyramid", MemeType.one, true, true));
            put("举", new Meme("raise_image", MemeType.one, true, true));
            put("看书", new Meme("read_book", MemeType.one, true, true));
            put("怒撕", new Meme("rip_angrily", MemeType.one, true, true));
            put("诈尸", new Meme("rise_dead", MemeType.one, true, true));
            put("秽土转生", new Meme("rise_dead", MemeType.one, true, true));
            put("滚", new Meme("roll", MemeType.one, true, true));
            put("三维旋转", new Meme("rotate_3d", MemeType.one, true, true));
            put("快逃", new Meme("run_away", MemeType.one, true, true));
            put("催眠app", new Meme("saimin_app", MemeType.one, true, true));
            put("挠头", new Meme("scratch_head", MemeType.one, true, true));
            put("世界第一可爱", new Meme("sekaiichi_kawaii", MemeType.one, true, true));
            put("晃脑", new Meme("shake_head", MemeType.one, true, true));
            put("白子舔", new Meme("shiroko_pero", MemeType.one, true, true));
            put("震惊", new Meme("shock", MemeType.one, true, true));
            put("坐得住", new Meme("sit_still", MemeType.one, true, true));
            put("砸", new Meme("smash", MemeType.one, true, true));
            put("卖掉了", new Meme("sold_out", MemeType.one, true, true));
            put("无语", new Meme("speechless", MemeType.one, true, true));
            put("踩", new Meme("step_on", MemeType.one, true, true));
            put("炖", new Meme("stew", MemeType.one, true, true));
            put("科目三", new Meme("subject3", MemeType.one, true, true));
            put("吸", new Meme("suck", MemeType.one, true, true));
            put("精神支柱", new Meme("support", MemeType.one, true, true));
            put("回旋转", new Meme("swirl_turn", MemeType.one, true, true));
            put("唐可可举牌", new Meme("tankuku_raisesign", MemeType.one, true, true));
            put("嘲讽", new Meme("taunt", MemeType.one, true, true));
            put("讲课", new Meme("teach", MemeType.one, true, true));
            put("拿捏", new Meme("tease", MemeType.one, true, true));
            put("望远镜", new Meme("telescope", MemeType.one, true, true));
            put("体温枪", new Meme("thermometer_gun", MemeType.one, true, true));
            put("想什么", new Meme("think_what", MemeType.one, true, true));
            put("丢", new Meme("throw", MemeType.one, true, true));
            put("抛", new Meme("throw_gif", MemeType.one, true, true));
            put("捶", new Meme("thump", MemeType.one, true, true));
            put("爆捶", new Meme("thump_wildly", MemeType.one, true, true));
            put("紧贴", new Meme("tightly", MemeType.one, true, true));
            put("该走了", new Meme("time_to_go", MemeType.one, true, true));
            put("汤姆嘲笑", new Meme("tom_tease", MemeType.one, true, true));
            put("恍惚", new Meme("trance", MemeType.one, true, true));
            put("转", new Meme("turn", MemeType.one, true, true));
            put("搓", new Meme("twist", MemeType.one, true, true));
            put("反了", new Meme("upside_down", MemeType.one, true, true));
            put("震动", new Meme("vibrate", MemeType.one, true, true));
            put("墙纸", new Meme("wallpaper", MemeType.one, true, true));
            put("胡桃平板", new Meme("walnut_pad", MemeType.one, true, true));
            put("胡桃放大", new Meme("walnut_zoom", MemeType.one, true, true));
            put("洗衣机", new Meme("washer", MemeType.one, true, true));
            put("波纹", new Meme("wave", MemeType.one, true, true));
            put("我想上的", new Meme("what_I_want_to_do", MemeType.one, true, true));
            put("最想要的东西", new Meme("what_he_wants", MemeType.one, true, true));
            put("为什么艾特我", new Meme("why_at_me", MemeType.one, true, true));
            put("风车转", new Meme("windmill_turn", MemeType.one, true, true));
            put("木鱼", new Meme("wooden_fish", MemeType.one, true, true));
            put("膜拜", new Meme("worship", MemeType.one, true, true));
            put("致电", new Meme("you_should_call", MemeType.one, true, true));
            put("你的跨年", new Meme("your_new_years_eve", MemeType.one, true, true));

            put("上对称", new Meme("symmetric", MemeType.reply, true, new JSONObject() {{
                put("direction", "top");
            }}));
            put("下对称", new Meme("symmetric", MemeType.reply, true, new JSONObject() {{
                put("direction", "bottom");
            }}));
            put("左对称", new Meme("symmetric", MemeType.reply, true, new JSONObject() {{
                put("direction", "left");
            }}));
            put("右对称", new Meme("symmetric", MemeType.reply, true, new JSONObject() {{
                put("direction", "right");
            }}));
            put("玩游戏", new Meme("play_game", MemeType.reply));
        }};
    }

    @Override
    public void groupMessage(GroupMessageEvent event, ApiSender sender) {
        if (EventUtils.equals(event, "meme")) {
            String sb = "meme列表：\n" + StrUtil.join("、", tokenMap.keySet());
            Message node = new Message().addMsg(text(sb));
            sender.sendGroupForwardMsg(
                    event.getGroupId(),
                    new Message()
                            .addMsg(
                                    fakeNode(
                                            event.getSelfId(),
                                            sender.getSelfNickname(),
                                            node.getMsg()
                                    )
                            )
            );
        }
        if (EventUtils.isReply(event)) {
            replyMeme(event, sender);
        } else {
            defMessage(event, sender);
        }
    }

    private void replyMeme(GroupMessageEvent event, ApiSender sender) {
        String token = EventUtils.containsEqualsIn(event, tokenMap.keySet());
        if (token == null) return;
        Meme meme = tokenMap.get(token);
        if (!meme.isCanReply() && meme.getType() != MemeType.reply) return;
        Integer messageId = EventUtils.getReply(event);
        if (messageId == null) return;
        GroupMessageEvent replyEvent = getGroupDataService().getGroupMessage(messageId, event.getGroupId());
        if (replyEvent == null) return;
        String image = EventUtils.getImage0(replyEvent);
        if (StrUtil.isBlank(image)) return;
        File file = downloadImage(image, messageId.toString());
        if (file == null || !file.exists()) return;
        if (meme.isNotAtMe()) {
            if (EventUtils.atMe(event)) return;
        }
        String memeFile = send(
                meme.getAction(),
                new File[]{file},
                null,
                meme.getArgs()
        );
        sender.sendGroupMsg(
                event.getGroupId(),
                new Message()
                        .addMsg(reply(event))
                        .addMsg(tempImage(memeFile))
        );
    }

    private void defMessage(GroupMessageEvent event, ApiSender sender) {
        String token = EventUtils.startsWithIn(event, tokenMap.keySet());
        if (StrUtil.isBlank(token)) return;
        Meme meme = tokenMap.get(token);
        if (meme.getType() == MemeType.reply) return;
        Long qq = EventUtils.getAt0(event);
        if (qq == null) return;
        if (meme.isNotAtMe()) {
            if (EventUtils.atMe(event)) return;
        }
        String memeTempFileName = send(
                meme.getAction(),
                meme.getType().equals(MemeType.one)
                        ? new File[]{downloadFaceImage(qq)}
                        : new File[]{downloadFaceImage(event.getUserId()), downloadFaceImage(qq)},
                null,
                meme.getArgs()
        );
        if (StrUtil.isBlank(memeTempFileName)) return;
        sender.sendGroupMsg(
                event.getGroupId(),
                new Message()
                        .addMsg(reply(event))
                        .addMsg(tempImage(memeTempFileName))
        );
    }

    private static Map<String, Meme> tokenMap;

    @Data
    private static class Meme {
        private String action;
        private MemeType type;
        private boolean notAtMe = true;
        private boolean canReply = false;
        private JSONObject args = null;

        Meme(String action, MemeType type) {
            this.action = action;
            this.type = type;
        }

        Meme(String action, MemeType type, boolean notAtMe) {
            this.action = action;
            this.type = type;
            this.notAtMe = notAtMe;
        }

        Meme(String action, MemeType type, boolean notAtMe, boolean canReply) {
            this.action = action;
            this.type = type;
            this.notAtMe = notAtMe;
            this.canReply = canReply;
        }

        Meme(String action, MemeType type, boolean notAtMe, JSONObject args) {
            this.action = action;
            this.type = type;
            this.notAtMe = notAtMe;
            this.args = args;
        }

        Meme(String action, MemeType type, boolean notAtMe, boolean canReply, JSONObject args) {
            this.action = action;
            this.type = type;
            this.notAtMe = notAtMe;
            this.canReply = canReply;
            this.args = args;
        }
    }

    private enum MemeType {
        one, two, reply
    }
}
