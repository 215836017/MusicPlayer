package com.fzq.musicplayer.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fzq.musicplayer.R;
import com.fzq.musicplayer.bean.MusicInfo;

import java.util.List;

/**
 * Created by fzq on 2017/6/14.
 */

public class MyMusicAdapter extends BaseAdapter {


    private List<MusicInfo> musicInfos;
    private LayoutInflater inflater;

    public MyMusicAdapter(Context context, List<MusicInfo> musicInfos) {
        this.musicInfos = musicInfos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return musicInfos.size();
    }

    @Override
    public MusicInfo getItem(int position) {
        return musicInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_fragment_danqu_list_item, parent, false);

//            viewHolder.name = (TextView) convertView.findViewById(R.id.layout_localMusic_name);
//            viewHolder.author = (TextView) convertView.findViewById(R.id.layout_localMusic_author);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MusicInfo musicInfo = musicInfos.get(position);

        viewHolder.name.setText(musicInfo.getTitle());
        viewHolder.author.setText(musicInfo.getDisplayName());


        return convertView;
    }


    class ViewHolder {
        TextView name;
        TextView author;

    }
}
