package com.fzq.musicplayer.ui.fragments.localmusic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fzq.musicplayer.Constant;
import com.fzq.musicplayer.R;
import com.fzq.musicplayer.bean.MusicInfo;
import com.fzq.musicplayer.service.MusicService;
import com.fzq.musicplayer.ui.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;


/**
 * Created by fzq on 2017/8/19.
 */

public class DanQuAdapter extends RecyclerView.Adapter<DanQuAdapter.DanQuViewHolder> {

    private Context context;
    private List<MusicInfo> musicList;
    private LayoutInflater inflater;

    public DanQuAdapter(Context context, List<MusicInfo> musicList) {
        this.context = context;
        this.musicList = musicList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public DanQuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_fragment_danqu_list_item, parent, false);

        return new DanQuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DanQuViewHolder holder, int position) {

        MusicInfo musicInfo = musicList.get(position);

        holder.musicName.setText(musicInfo.getDisplayName());
        holder.geShou.setText(musicInfo.getArtist());


        //各个控件的点击事件的监听
        MyOnclickListener clickListener = new MyOnclickListener(position);
        holder.itemLayoutRoot.setOnClickListener(clickListener);
        holder.showMore.setOnClickListener(clickListener);

    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }


    class DanQuViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.danquFrag_listItem_layout_normal)
        RelativeLayout itemLayoutRoot;
        @BindView(R.id.danquFrag_listItem_text_musicName)
        TextView musicName;
        @BindView(R.id.danquFrag_listItem_text_geShou)
        TextView geShou;
        @BindView(R.id.danquFrag_listItem_text_showMore)
        TextView showMore;

        public DanQuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MyOnclickListener implements View.OnClickListener {
        private int clickPosition;
        MyOnclickListener(int clickPosition){
            this.clickPosition = clickPosition;
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.danquFrag_listItem_layout_normal:
                    Constant.playState = Constant.PLAY_STOP;
                    Constant.currentPlayingMusic = musicList.get(clickPosition);
                    Constant.currentPlayingPosition = clickPosition;
                    Constant.currentProgress = 0;

                    MainActivity.handler.sendEmptyMessage(MainActivity.DO_PLAY_START);

                    //改变当前正在播放的Item的背景，
                    // 这里要考虑到所有的Item中只有当前正在播放的背景和其他的不同

                    break;

                case R.id.danquFrag_listItem_text_showMore:
                    Toast.makeText(context, "position is: " + clickPosition, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

}
