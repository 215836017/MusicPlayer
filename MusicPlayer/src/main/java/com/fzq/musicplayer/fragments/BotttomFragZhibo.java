package com.fzq.musicplayer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fzq.musicplayer.R;

/**
 * Created by fzq on 2017/7/9.
 */

public class BotttomFragZhibo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_fragment_kan_zhibo, container, false);

        return rootView;
    }
}
