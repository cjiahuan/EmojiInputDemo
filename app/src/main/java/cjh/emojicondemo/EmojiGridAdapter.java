package cjh.emojicondemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by cjh on 16-11-8.
 */

public class EmojiGridAdapter extends BaseAdapter {

    private Context context;
    private int page;

    public EmojiGridAdapter(Context context, int page) {
        this.context = context;
        this.page = page;
    }

    @Override
    public int getCount() {
        return 24;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.chat_emoji, null);
            holder = new ViewHolder();
            holder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(holder);
        }

        holder = (ViewHolder) view.getTag();
        int position = page * 23 + i;
        if (position < EmojiUtils.emojis.length)
            ImageLoader.load(context, EmojiUtils.icons[position], holder.image);
        else
            holder.image.setVisibility(View.GONE);
        holder.image.setOnClickListener(view1 -> EventBus.getDefault().post(new EmojiEvent(EmojiUtils.emojis[page * 23 + i])));

        return view;
    }

    static class ViewHolder {
        public ImageView image;
    }
}
