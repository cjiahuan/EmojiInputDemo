package cjh.emojicondemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static Context context;

    @BindView(R.id.chatBottomBar)
    ChatBottomBar chatBottomBar;

    @Subscribe
    public void onEmojiEvent(EmojiEvent emojiEvent) {
        chatBottomBar.appandEmoje(emojiEvent.s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
