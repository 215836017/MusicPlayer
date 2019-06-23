package com.test.musicplayer.ui.actvities.menus;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.musicplayer.R;
import com.test.musicplayer.ui.fragments.setting.FragPingLun;
import com.test.musicplayer.ui.fragments.setting.FragSiXin;
import com.test.musicplayer.ui.fragments.setting.FragZan;
import com.test.musicplayer.utils.LogUtil;

public class NewsCentreActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "NewsCentreActivity.java";

    private ImageView imageBack;
    private ImageView imageMore;
    private TextView textSiXin, textPingLun, textZan;
    private ViewPager viewPager;

    private Fragment[] fragments = new Fragment[3];

    private int currentPageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_centre);

        initFragments();
        initViews();
    }

    private void initFragments() {
        fragments[0] = new FragSiXin();
        fragments[1] = new FragPingLun();
        fragments[2] = new FragZan();
//        FragSiXin fragSiXin = new FragSiXin();
//        FragPingLun fragPingLun = new FragPingLun();
//        FragZan fragZan = new FragZan();
    }

    private void initViews() {
        imageBack = findViewById(R.id.settingAct_image_back);
        imageMore = findViewById(R.id.settingAct_image_moreSettings);
        textSiXin = findViewById(R.id.settingAct_text_siXin);
        textPingLun = findViewById(R.id.settingAct_text_pingLun);
        textZan = findViewById(R.id.settingAct_text_zan);
        viewPager = findViewById(R.id.settingAct_layout_viewPager);

        imageBack.setOnClickListener(this);
        imageMore.setOnClickListener(this);
        textSiXin.setOnClickListener(this);
        textPingLun.setOnClickListener(this);
        textZan.setOnClickListener(this);

        viewPager.setCurrentItem(currentPageIndex);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                LogUtil.i(TAG, "onPageSelected() i = " + i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settingAct_image_back:
                this.finish();
                break;

            case R.id.settingAct_image_moreSettings:
                break;

            case R.id.settingAct_text_siXin:
                break;

            case R.id.settingAct_text_pingLun:
                break;

            case R.id.settingAct_text_zan:
                break;

        }
    }
}
