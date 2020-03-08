package com.cakes.musicplayer.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cakes.musicplayer.R;
import com.cakes.musicplayer.music.MusicInfoBean;
import com.cakes.musicplayer.music.MusicList;
import com.cakes.musicplayer.play.PlayHelper;

import java.util.List;

public class LocalMusicAdapter extends RecyclerView.Adapter<LocalMusicAdapter.ViewHolder> {

    private final String TAG = "LocalMusicAdapter";

    private Context context;
    private List<MusicInfoBean> musicList;
    private OnItemEventListener itemEventListener;

    public LocalMusicAdapter(Context context, OnItemEventListener listener) {
        this.context = context;
        this.musicList = MusicList.getInstance().getSdcardMusicList();
        this.itemEventListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.layout_localmusic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final MusicInfoBean bean = musicList.get(position);

        holder.layoutRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemEventListener.playMusic(position, bean);
                PlayHelper.getInstance().setPlayingListAndPosition(musicList, position);
            }
        });

        holder.textName.setText(bean.getDisplayName());
        holder.textAuthor.setText(bean.getArtist());
    }

    @Override
    public int getItemCount() {
        if (null != musicList) {
            return musicList.size();
        }
        return -1;
    }


    public MusicInfoBean getMusicInfoBean(int pos) {
        if (pos < 0 || pos >= getItemCount()) {
            return null;
        }else{
            return musicList.get(pos);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout layoutRoot;
        private ImageView imageIcon;
        private TextView textName;
        private TextView textAuthor;
        private ImageView imageMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutRoot = itemView.findViewById(R.id.item_local_music_layout_root);
            textName = itemView.findViewById(R.id.item_local_music_text_name);
            textAuthor = itemView.findViewById(R.id.item_local_music_text_author);
            imageMore = itemView.findViewById(R.id.item_local_music_btn_more);

        }
    }
}
