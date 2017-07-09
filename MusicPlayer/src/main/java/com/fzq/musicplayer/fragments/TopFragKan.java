package com.fzq.musicplayer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fzq.musicplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fzq on 2017/7/9.
 */

public class TopFragKan extends Fragment {

    private View rootView;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private FragmegtAdapter adapter;
    private FragmentActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.layout_fragment_kan, container, false);
        activity = getActivity();
        viewPager = (ViewPager) rootView.findViewById(R.id.fragKan_viewPager);
        fragments = new ArrayList<>();
        fragments.add(new BotttomFragZhibo());
        fragments.add(new BotttomFragTuijian());
        fragments.add(new BotttomFragDuanpian());
        fragments.add(new BotttomFragMV());
        fragments.add(new BotttomFragJiemu());
        fragments.add(new BotttomFragYanchu());

        adapter = new FragmegtAdapter(activity.getSupportFragmentManager(), fragments);

        viewPager.setAdapter(adapter);
        return rootView;
    }
}
