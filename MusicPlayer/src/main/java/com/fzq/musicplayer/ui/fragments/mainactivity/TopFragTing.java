package com.fzq.musicplayer.ui.fragments.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fzq.musicplayer.R;
import com.fzq.musicplayer.ui.topfrags.LocalMusicActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fzq on 2017/7/9.
 */

public class TopFragTing extends Fragment {

    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_fragment_ting, container, false);
        ButterKnife.bind(this, rootView);

        context = getContext();

        return rootView;
    }

    private Intent intent = new Intent();
    @OnClick(R.id.mainAct_content_localMusic_layout)
    public void goToLocalMusicAct(){
        intent.setClass(context, LocalMusicActivity.class);
        startActivity(intent);
    }
}
