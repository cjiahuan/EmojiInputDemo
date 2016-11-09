package cjh.emojicondemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.util.SparseArray;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Inflater;

/**
 * Created by cjh on 16-11-7.
 */

public class EmojiUtils {

    private static HashMap<Pattern, Integer> emoMap = new HashMap<>();

    public static final String DELETE_KEY = "em_delete_delete_expression";

    public static String[] emojis = new String[]{
            "[微笑]",
            "[撇嘴]",
            "[色]",
            "[发呆]",
            "[得意]",
            "[流泪]",
            "[害羞]",
            "[闭嘴]",
            "[睡]",
            "[大哭]",
            "[尴尬]",
            "[发怒]",
            "[调皮]",
            "[呲牙]",
            "[惊讶]",
            "[难过]",
            "[酷]",
            "[冷汗]",
            "[抓狂]",
            "[吐]",
            "[偷笑]",
            "[愉快]",
            "[白眼]",
            "[傲慢]",
            "[饥饿]",
            "[困]",
            "[惊恐]",
            "[流汗]",
            "[憨笑]",
            "[悠闲]",
            "[奋斗]",
            "[咒骂]",
            "[疑问]",
            "[嘘]",
            "[晕]",
            "[疯了]",
            "[衰]",
            "[骷髅]",
            "[敲打]",
            "[再见]",
            "[擦汗]",
            "[抠鼻]",
            "[鼓掌]",
            "[糗大了]",
            "[坏笑]",
            "[左哼哼]",
            "[右哼哼]",
            "[哈欠]",
            "[鄙视]",
            "[委屈]",
            "[快哭了]",
            "[阴险]",
            "[亲亲]",
            "[吓]",
            "[可怜]",
            "[菜刀]",
            "[西瓜]",
            "[啤酒]",
            "[篮球]",
            "[乒乓]",
            "[咖啡]",
            "[饭]",
            "[猪头]",
            "[玫瑰]",
            "[凋谢]",
            "[嘴唇]",
            "[爱心]",
            "[心碎]",
            "[蛋糕]",
            "[闪电]",
            "[炸弹]",
            "[刀]",
            "[足球]",
            "[瓢虫]",
            "[便便]",
            "[月亮]",
            "[太阳]",
            "[礼物]",
            "[拥抱]",
            "[强]",
            "[弱]",
            "[握手]",
            "[胜利]",
            "[抱拳]",
            "[勾引]",
            "[拳头]",
            "[差劲]",
            "[爱你]",
            "[NO]",
            "[OK]"
    };

    public static int[] icons = new int[]{
            R.drawable.ee_1,
            R.drawable.ee_2,
            R.drawable.ee_3,
            R.drawable.ee_4,
            R.drawable.ee_5,
            R.drawable.ee_6,
            R.drawable.ee_7,
            R.drawable.ee_8,
            R.drawable.ee_9,
            R.drawable.ee_10,
            R.drawable.ee_11,
            R.drawable.ee_12,
            R.drawable.ee_13,
            R.drawable.ee_14,
            R.drawable.ee_15,
            R.drawable.ee_16,
            R.drawable.ee_17,
            R.drawable.ee_18,
            R.drawable.ee_19,
            R.drawable.ee_20,
            R.drawable.ee_21,
            R.drawable.ee_22,
            R.drawable.ee_23,
            R.drawable.ee_24,
            R.drawable.ee_25,
            R.drawable.ee_26,
            R.drawable.ee_27,
            R.drawable.ee_28,
            R.drawable.ee_29,
            R.drawable.ee_30,
            R.drawable.ee_31,
            R.drawable.ee_32,
            R.drawable.ee_33,
            R.drawable.ee_34,
            R.drawable.ee_35,
            R.drawable.ee_36,
            R.drawable.ee_37,
            R.drawable.ee_38,
            R.drawable.ee_39,
            R.drawable.ee_40,
            R.drawable.ee_41,
            R.drawable.ee_42,
            R.drawable.ee_43,
            R.drawable.ee_44,
            R.drawable.ee_45,
            R.drawable.ee_46,
            R.drawable.ee_47,
            R.drawable.ee_48,
            R.drawable.ee_49,
            R.drawable.ee_50,
            R.drawable.ee_51,
            R.drawable.ee_52,
            R.drawable.ee_53,
            R.drawable.ee_54,
            R.drawable.ee_55,
            R.drawable.ee_56,
            R.drawable.ee_57,
            R.drawable.ee_58,
            R.drawable.ee_59,
            R.drawable.ee_60,
            R.drawable.ee_61,
            R.drawable.ee_62,
            R.drawable.ee_63,
            R.drawable.ee_64,
            R.drawable.ee_65,
            R.drawable.ee_66,
            R.drawable.ee_67,
            R.drawable.ee_68,
            R.drawable.ee_69,
            R.drawable.ee_70,
            R.drawable.ee_71,
            R.drawable.ee_72,
            R.drawable.ee_73,
            R.drawable.ee_74,
            R.drawable.ee_75,
            R.drawable.ee_76,
            R.drawable.ee_77,
            R.drawable.ee_78,
            R.drawable.ee_79,
            R.drawable.ee_80,
            R.drawable.ee_81,
            R.drawable.ee_82,
            R.drawable.ee_83,
            R.drawable.ee_84,
            R.drawable.ee_85,
            R.drawable.ee_86,
            R.drawable.ee_87,
            R.drawable.ee_88,
            R.drawable.ee_89,
            R.drawable.ee_90,
    };

    static {
        for (int i = 0; i < emojis.length; i++) {
            emoMap.put(Pattern.compile(Pattern.quote(emojis[i])), icons[i]);
        }
    }

    public static SpannableString getEmojiText(String s) {
        SpannableString spannable = new SpannableString(s);
        for (Map.Entry<Pattern, Integer> entry : emoMap.entrySet()) {
            Matcher matcher = entry.getKey().matcher(spannable);
            while (matcher.find()) {
                for (ImageSpan span : spannable.getSpans(matcher.start(),
                        matcher.end(), ImageSpan.class))
                    if (spannable.getSpanStart(span) >= matcher.start()
                            && spannable.getSpanEnd(span) <= matcher.end())
                        spannable.removeSpan(span);
                    else
                        break;
                Drawable drawable = MainActivity.context.getResources().getDrawable(entry.getValue());
                drawable.setBounds(0, 0, 60, 60);
                ImageSpan imageSpan = new ImageSpan(drawable);
                spannable.setSpan(imageSpan,
                        matcher.start(), matcher.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return spannable;
    }
}
