package com.fzq.myapplication.slide_viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.fzq.myapplication.FragmentAdapter;
import com.fzq.myapplication.R;
import com.fzq.myapplication.fragments.TopFrag1;
import com.fzq.myapplication.fragments.TopFrag2;
import com.fzq.myapplication.fragments.TopFrag3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fzq on 2017/7/22.
 */

public class SlideViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideLayout slideLayout;
    private FragmentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_viewpager);

        viewPager = (ViewPager) findViewById(R.id.slideAndViewPager);
        slideLayout = (SlideLayout) findViewById(R.id.slideLayout);


        List<Fragment> lists = new ArrayList<>();
        lists.add(new TopFrag1());
        lists.add(new TopFrag2());
        lists.add(new TopFrag3());


        adapter = new FragmentAdapter(getSupportFragmentManager(), lists);
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        if (slideLayout.isOpened()) {
            slideLayout.close();
        } else {
            super.onBackPressed();
        }
    }
}
