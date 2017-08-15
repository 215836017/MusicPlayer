package com.fzq.musicplayer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fzq.musicplayer.R;
import com.fzq.musicplayer.fragments.FragmegtAdapter;
import com.fzq.musicplayer.fragments.TopFragChang;
import com.fzq.musicplayer.fragments.TopFragKan;
import com.fzq.musicplayer.fragments.TopFragTing;
import com.fzq.musicplayer.ui.leftmenu.DingShiGuanBiActivity;
import com.fzq.musicplayer.ui.leftmenu.HuiYuanZhongXinActivity;
import com.fzq.musicplayer.ui.leftmenu.KuiSheYinXiaoActivity;
import com.fzq.musicplayer.ui.leftmenu.LingShengCaiLingActivity;
import com.fzq.musicplayer.ui.leftmenu.PiFuZhongXinActivity;
import com.fzq.musicplayer.ui.leftmenu.SettingActivity;
import com.fzq.musicplayer.ui.leftmenu.UserInfoActivity;
import com.fzq.musicplayer.ui.leftmenu.UserLevelActivity;
import com.fzq.musicplayer.ui.leftmenu.XiaoXiZhongXinActitity;
import com.fzq.musicplayer.ui.leftmenu.YinYueGongJuActivity;
import com.fzq.musicplayer.view.MyViewPagerOut;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fzq on 2017/6/25.
 * 资料：
 * Android中Scrollview、ViewPager冲突问题汇总（已解决）:
 * 1. http://www.cnblogs.com/bavariama/p/3472510.html
 * 2. http://blog.csdn.net/gdutxiaoxu/article/details/52939127
 * 3. http://blog.csdn.net/mcy478643968/article/details/19609407
 * <p>
 * Android向右滑动关闭界面（仿iOS）:
 * 1. http://blog.csdn.net/u011164565/article/details/52539689
 * 2. http://blog.csdn.net/xiaanming/article/details/20934541
 * 3. http://www.jb51.net/article/112843.htm
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainAct_viewPager)
    MyViewPagerOut myViewPager;

    private Intent intent = new Intent();

    private FragmegtAdapter adapter;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initDatas();
    }

    private void initDatas() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new TopFragTing());
        fragmentList.add(new TopFragKan());
        fragmentList.add(new TopFragChang());

        adapter = new FragmegtAdapter(getSupportFragmentManager(), fragmentList);

        myViewPager.setAdapter(adapter);

    }


    @OnClick({R.id.leftMenu_image_kuGou_Logo, R.id.leftMenu_text_kuGou_userName})
    public void goToUserInfoAct() {
        intent.setClass(this, UserInfoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.leftMenu_text_kuGou_level)
    public void goToUserLevelAct() {
        intent.setClass(this, UserLevelActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.leftMenu_layout_xiaoxizhongxin)
    public void goToXiaoXiZhongXinAct() {
        intent.setClass(this, XiaoXiZhongXinActitity.class);
        startActivity(intent);
    }

    @OnClick(R.id.leftMenu_layout_pifuzhongxin)
    public void goToPiFuZhongXinAct() {
        intent.setClass(this, PiFuZhongXinActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.leftMenu_layout_huiyuanhongxin)
    public void goToHuiYuanZhongXinAct() {
        intent.setClass(this, HuiYuanZhongXinActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.leftMenu_layout_dingshiguanbi)
    public void goToDingShiGuanBiAct() {
        intent.setClass(this, DingShiGuanBiActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.leftMenu_layout_kuisheyinxiao)
    public void goToKuiSheYinXiaoAct() {
        intent.setClass(this, KuiSheYinXiaoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.leftMenu_layout_yinyuegongju)
    public void goToYinYueGongJuAct() {
        intent.setClass(this, YinYueGongJuActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.leftMenu_layout_lingshengcailing)
    public void goToLingShengCaiLingAct() {
        intent.setClass(this, LingShengCaiLingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.leftMenu_switch_wifilianwang)
    public void wifiLianWang() {
        //仅wifi下联网
    }

    @OnClick(R.id.leftMenu_switch_zhuomiangeci)
    public void zhuoMianGeCi() {
        //是否开启桌面歌词
    }

    @OnClick(R.id.leftMenu_switch_suopingeci)
    public void suoPingGeCi() {
        //是否开启锁屏歌词
    }


    @OnClick(R.id.leftMenu_btn_setting)
    public void goToSettingAct() {
        intent.setClass(this, SettingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.leftMenu_btn_exit)
    public void exitApp(){
        //退出程序
    }


}
