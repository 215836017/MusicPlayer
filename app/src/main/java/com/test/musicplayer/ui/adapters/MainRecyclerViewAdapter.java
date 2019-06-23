package com.test.musicplayer.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.test.musicplayer.R;
import com.test.musicplayer.music.Constant;
import com.test.musicplayer.music.MusicInfoBean;
import com.test.musicplayer.service.MusicService;

import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<MusicInfoBean> datas;
    private LayoutInflater inflater;

    public MainRecyclerViewAdapter(Context context, List<MusicInfoBean> datas) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.layout_localmusicact_recyclerview_item,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int pos) {
        final MusicInfoBean musicInfoBean = datas.get(pos);

        holder.textMusicName.setText(musicInfoBean.getMusicNameNoTail());
        holder.textMusicAuthor.setText(musicInfoBean.getArtist());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.currentPlayingPath = musicInfoBean.getPath();
                MusicService.serviceHandler.sendEmptyMessage(MusicService.msg_play);
            }
        });
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "pos = " + pos, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout layout;
        private TextView textMusicName;
        private TextView textMusicAuthor;
        private ImageButton btnMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.localMusicAct_recyclerView_item_layout_root);
            textMusicName = itemView.findViewById(R.id.localMusicAct_recyclerView_item_text_musicName);
            textMusicAuthor = itemView.findViewById(R.id.localMusicAct_recyclerView_item_text_musicAuthor);
            btnMore = itemView.findViewById(R.id.localMusicAct_recyclerView_item_bt_more);
        }
    }
}
