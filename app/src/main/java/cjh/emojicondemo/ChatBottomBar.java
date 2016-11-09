package cjh.emojicondemo;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cjh on 16-11-7.
 */

public class ChatBottomBar extends LinearLayout {

    private Context context;

    @BindView(R.id.editText)
    EditText editText;

    @BindView(R.id.emojes)
    android.support.v4.view.ViewPager emojes;

    public ChatBottomBar(Context context) {
        super(context);
        inflateView(context);
    }

    public ChatBottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);
    }

    public ChatBottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ChatBottomBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateView(context);
    }

    private void inflateView(Context context) {
        this.context = context;
        View view = View.inflate(context, R.layout.chat_bottom, this);
        ButterKnife.bind(this, view);
        init();
    }

    private void init() {
        initEmoje();
    }

    private void initEmoje() {
        int pageCount = (int) Math.ceil(EmojiUtils.emojis.length / 23.0f);
        ArrayList<GridView> pageData = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            GridView gv = getGridView(i);
            pageData.add(gv);
        }
        emojes.setAdapter(new EmojiPageAdapter(context, pageData));
    }

    @NonNull
    private GridView getGridView(int i) {
        GridView gv = new GridView(context);
        gv.setVerticalScrollBarEnabled(false);
        gv.setAdapter(new EmojiGridAdapter(context, i));
        gv.setGravity(Gravity.CENTER);
        gv.setClickable(true);
        gv.setFocusable(true);
        gv.setNumColumns(8);
        return gv;
    }

    public void appandEmoje(String s) {
        rx.Observable
                .just(s)
                .subscribeOn(Schedulers.io())
                .map(s1 -> {
                    SpannableString emojeText = EmojiUtils.getEmojiText(editText.getText().toString() + s1);
                    return emojeText;
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s2 -> {
                    editText.setText("");
                    editText.append(s2);
                });


    }

}
