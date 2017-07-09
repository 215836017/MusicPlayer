package com.fzq.musicplayer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.fzq.musicplayer.R;
import com.fzq.musicplayer.fragments.FragmegtAdapter;
import com.fzq.musicplayer.fragments.TopFragChang;
import com.fzq.musicplayer.fragments.TopFragKan;
import com.fzq.musicplayer.fragments.TopFragTing;
import com.fzq.musicplayer.test.TestActivity2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fzq on 2017/6/25.
 * 资料：
 * Android中Scrollview、ViewPager冲突问题汇总（已解决）:
 * 1. http://www.cnblogs.com/bavariama/p/3472510.html
 * 2. http://blog.csdn.net/gdutxiaoxu/article/details/52939127
 * 3. http://blog.csdn.net/mcy478643968/article/details/19609407
 *
 * Android向右滑动关闭界面（仿iOS）:
 * 1. http://blog.csdn.net/u011164565/article/details/52539689
 * 2. http://blog.csdn.net/xiaanming/article/details/20934541
 * 3. http://www.jb51.net/article/112843.htm
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ViewPager viewPager;
    private FragmegtAdapter adapter;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initDatas();
    }

    private void initViews(){
        viewPager = (ViewPager) findViewById(R.id.mainAct_viewPager);

    }

    private void initDatas(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new TopFragTing());
        fragmentList.add(new TopFragKan());
        fragmentList.add(new TopFragChang());

        adapter = new FragmegtAdapter(getSupportFragmentManager(), fragmentList);

        viewPager.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
//        switch (v.getId()){
//            case R.id.mainAct_content_localMusic_layout:
//                intent.setClass(this, TestActivity2.class);
//                break;
//
//            default:
//                break;
//        }

        startActivity(intent);
    }
}
