package com.fzq.musicplayer.ui.topfrags;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fzq.musicplayer.R;
import com.fzq.musicplayer.ui.fragments.FragmentAdapter;
import com.fzq.musicplayer.ui.fragments.localmusic.DanQuFrag;
import com.fzq.musicplayer.ui.fragments.localmusic.GeShouFrag;
import com.fzq.musicplayer.ui.fragments.localmusic.WenJianJiaFrag;
import com.fzq.musicplayer.ui.fragments.localmusic.ZhuanJiFrag;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fzq on 2017/8/18.
 * 在主界面中点击 “本地音乐”后进入这里
 */

public class LocalMusicActivity extends AppCompatActivity {

    @BindView(R.id.localAct_viewPager)
    ViewPager viewPager;
    //danqu
    @BindView(R.id.localAct_textView_danqu)
    TextView danQuText;
    @BindView(R.id.localAct_textView_danquCount)
    TextView danQuCount;

    //geshou
    @BindView(R.id.localAct_textView_geshou)
    TextView geShouText;
    @BindView(R.id.localAct_textView_geshouCount)
    TextView geShouCount;

    //zhuanji
    @BindView(R.id.localAct_textView_zhuanji)
    TextView zhuanJiText;
    @BindView(R.id.localAct_textView_zhuanjiCount)
    TextView zhuanJiCount;

    //wenjianjia
    @BindView(R.id.localAct_textView_wenjianjia)
    TextView wenJianJiaText;
    @BindView(R.id.localAct_textView_wenjianjiaCount)
    TextView wenJianJiaCount;

    private FragmentAdapter fragmentAdapter;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);

        //适配ViewPager和其中包含的Fragment，并且响应pager的变化
        initFrags();


    }

    /**
     * 适配ViewPager和其中包含的Fragment，并且响应pager的变化
     */
    private void initFrags() {

        fragmentList = new ArrayList<>();
        fragmentList.add(new DanQuFrag());
        fragmentList.add(new GeShouFrag());
        fragmentList.add(new ZhuanJiFrag());
        fragmentList.add(new WenJianJiaFrag());

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentAdapter);
        //默认状态
        viewPager.setCurrentItem(0);
        setCurrentPagerItem(0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentPagerItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.localAct_layout_danqu, R.id.localAct_layout_geshou, R.id.localAct_layout_zhuanji, R.id.localAct_layout_wenjianjia})
    public void changePagerItem(View view) {
        switch (view.getId()) {
            case R.id.localAct_layout_danqu:
                setCurrentPagerItem(0);
                break;
            case R.id.localAct_layout_geshou:
                setCurrentPagerItem(1);
                break;
            case R.id.localAct_layout_zhuanji:
                setCurrentPagerItem(2);
                break;
            case R.id.localAct_layout_wenjianjia:
                setCurrentPagerItem(3);
                break;
        }

    }


    private void setCurrentPagerItem(int index) {
        //记得还有跟随的指示器

        if (index == 0) {
            viewPager.setCurrentItem(0);

            //danqu
            danQuText.setTextColor(Color.GREEN);
            danQuCount.setTextColor(Color.GREEN);
            //geshou
            geShouText.setTextColor(Color.WHITE);
            geShouCount.setTextColor(Color.WHITE);
            //zhuanji
            zhuanJiText.setTextColor(Color.WHITE);
            zhuanJiCount.setTextColor(Color.WHITE);
            //wenjainjia
            wenJianJiaText.setTextColor(Color.WHITE);
            wenJianJiaCount.setTextColor(Color.WHITE);
        } else if (index == 1) {
            viewPager.setCurrentItem(1);

            //danqu
            danQuText.setTextColor(Color.WHITE);
            danQuCount.setTextColor(Color.WHITE);
            //geshou
            geShouText.setTextColor(Color.GREEN);
            geShouCount.setTextColor(Color.GREEN);
            //zhuanji
            zhuanJiText.setTextColor(Color.WHITE);
            zhuanJiCount.setTextColor(Color.WHITE);
            //wenjainjia
            wenJianJiaText.setTextColor(Color.WHITE);
            wenJianJiaCount.setTextColor(Color.WHITE);
        } else if (index == 2) {
            viewPager.setCurrentItem(2);

            //danqu
            danQuText.setTextColor(Color.WHITE);
            danQuCount.setTextColor(Color.WHITE);
            //geshou
            geShouText.setTextColor(Color.WHITE);
            geShouCount.setTextColor(Color.WHITE);
            //zhuanji
            zhuanJiText.setTextColor(Color.GREEN);
            zhuanJiCount.setTextColor(Color.GREEN);
            //wenjainjia
            wenJianJiaText.setTextColor(Color.WHITE);
            wenJianJiaCount.setTextColor(Color.WHITE);
        } else if (index == 3) {
            viewPager.setCurrentItem(3);

            //danqu
            danQuText.setTextColor(Color.WHITE);
            danQuCount.setTextColor(Color.WHITE);
            //geshou
            geShouText.setTextColor(Color.WHITE);
            geShouCount.setTextColor(Color.WHITE);
            //zhuanji
            zhuanJiText.setTextColor(Color.WHITE);
            zhuanJiCount.setTextColor(Color.WHITE);
            //wenjainjia
            wenJianJiaText.setTextColor(Color.GREEN);
            wenJianJiaCount.setTextColor(Color.GREEN);
        }
    }
}
