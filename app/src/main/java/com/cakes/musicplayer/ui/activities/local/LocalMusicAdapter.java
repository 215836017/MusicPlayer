package com.cakes.musicplayer.ui.activities.local;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cakes.musicplayer.R;
import com.cakes.musicplayer.music.MusicInfoBean;
import com.cakes.musicplayer.music.MusicList;
import com.cakes.musicplayer.play.playing.PlayingHelper;
import com.cakes.musicplayer.ui.adapters.OnItemEventListener;

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
                PlayingHelper.getInstance().setPlayingListAndPosition(musicList, position);
            }
        });

        if (position == 2 || position == 4) {
            holder.imagePlaying.setVisibility(View.VISIBLE);
        }
        if (bean.isPlaying()) {
            holder.layoutRoot.setBackgroundColor(Color.BLUE);

        } else {
            holder.layoutRoot.setBackgroundColor(Color.WHITE);
        }
        holder.textName.setText(bean.getDisplayName());
        holder.textAuthor.setText(bean.getArtist());

        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "show more operations...", Toast.LENGTH_LONG).show();
            }
        });
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
        } else {
            return musicList.get(pos);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout layoutRoot;
        private ImageView imagePlaying;
        private TextView textName;
        private TextView textAuthor;
        private ImageView imageMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutRoot = itemView.findViewById(R.id.item_local_music_layout_root);
            imagePlaying = itemView.findViewById(R.id.item_local_music_image_playing);
            textName = itemView.findViewById(R.id.item_local_music_text_name);
            textAuthor = itemView.findViewById(R.id.item_local_music_text_author);
            imageMore = itemView.findViewById(R.id.item_local_music_image_more);

        }
    }

    public void updateItem(int pos, MusicInfoBean bean) {
        musicList.set(pos, bean);
        notifyItemChanged(pos);
    }
}
