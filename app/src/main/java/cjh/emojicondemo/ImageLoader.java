package cjh.emojicondemo;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by 磊.
 * Date 2016/10/18 18:02
 */

public class ImageLoader {

    public static void load(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(imageView);
    }

    public static void load(Context context, int redId, ImageView imageView) {
        Glide.with(context)
                .load(redId)
                .centerCrop()
                .into(imageView);
    }
}
