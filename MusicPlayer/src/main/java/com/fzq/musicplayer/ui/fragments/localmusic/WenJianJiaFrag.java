package com.fzq.musicplayer.ui.fragments.localmusic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fzq.musicplayer.R;

/**
 * Created by fzq on 2017/8/19.
 */

public class WenJianJiaFrag extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.layout_fragment_localmusic_wenjianjia, container, false);

        return rootView;
    }
}
