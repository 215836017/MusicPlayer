package com.fzq.musicplayer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fzq.musicplayer.Constant;
import com.fzq.musicplayer.R;
import com.fzq.musicplayer.service.MusicService;
import com.fzq.musicplayer.ui.fragments.FragmentAdapter;
import com.fzq.musicplayer.ui.fragments.mainactivity.TopFragChang;
import com.fzq.musicplayer.ui.fragments.mainactivity.TopFragKan;
import com.fzq.musicplayer.ui.fragments.mainactivity.TopFragTing;
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
import com.fzq.musicplayer.utils.MusicUtil;
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

    public static Handler handler;
    public static final int DO_PLAY_START = 0x10;
    public static final int DO_PLAY_PAUSE = 0x11;
    public static final int DO_PLAY_STOP = 0x12;
    public static final int DO_PLAY_FINISH = 0x13;
    public static final int DO_PLAY_UPDATE_PROGRESS = 0x14;  //每隔一秒更新进度条

    ////////////////////////// 主界面中右侧页面加载Fragment相关 /////////////////////////////
    @BindView(R.id.mainAct_viewPager)
    MyViewPagerOut myViewPager;

    private FragmentAdapter adapter;
    private List<Fragment> fragmentList;

    /**
     *  进度条的进度值，开发播放后每隔一秒更新
     */
    private int progressCount;
    ////////////////////////// 主界面中右侧页面底部播放栏相关 ////////////////////////////////
    @BindView(R.id.layout_playingBar_image_album)
    ImageView playingBar_imageAlbum;
    @BindView(R.id.layout_playingBar_seekBar_progress)
    SeekBar playingBar_seekBar;
    @BindView(R.id.layout_playingBar_text_singer)
    TextView playingBar_textSinger;
    @BindView(R.id.layout_playingBar_text_musicName)
    TextView playingBar_textMusicName;
    @BindView(R.id.layout_playingBar_image_liebiao)
    ImageView playingBar_imageMenu;
    @BindView(R.id.layout_playingBar_image_next)
    ImageView playingBar_imageNext;
    @BindView(R.id.layout_playingBar_image_play)
    ImageView playingBar_imagePlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //加载 “听”， “看”，“唱”三个Fragment
        initDatas();

        //从sp中加载并初始化上次播放的信息，防止没有选择歌曲而是直接点击了开始按钮
        initMusicInfo();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case DO_PLAY_START:
                        initStateAndPlay();
                       break;
                    case DO_PLAY_PAUSE:
                        MusicService.handler.sendEmptyMessage(MusicService.PLAY_PAUSE);
                        playingBar_imagePlay.setImageResource(R.drawable.songplayer_player);
                        break;
                    case DO_PLAY_STOP:
                        break;
                    case DO_PLAY_FINISH:
                        playingBar_seekBar.setProgress(0);
                        playingBar_imagePlay.setImageResource(R.drawable.songplayer_player);
                        break;
                    case DO_PLAY_UPDATE_PROGRESS:
                        progressCount ++;
                        playingBar_seekBar.setProgress(progressCount);
                        break;
                    default:
                        break;

                }
            }
        };

        setSeekBarListener();
    }

    /**
     * 加载 “听”， “看”，“唱”三个Fragment
     */
    private void initDatas() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new TopFragTing());
        fragmentList.add(new TopFragKan());
        fragmentList.add(new TopFragChang());

        adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        myViewPager.setAdapter(adapter);
    }

    /**
     * 从sp中加载并初始化上次播放的信息，防止没有选择歌曲而是直接点击了开始按钮
     */
    private void initMusicInfo(){

    }


    /**
     * 开始播放时设置相应的状态
     */
    private void initStateAndPlay(){
        if(Constant.playState == Constant.PLAY_STOP){
            System.out.println("测试播放： initStateAndPlay -- Constant.playState == Constant.PLAY_STOP");
            progressCount = 0;
            playingBar_seekBar.setMax(Constant.currentPlayingMusic.getDuration() / 1000);
            playingBar_seekBar.setProgress(0);
            //TODO 设置playingBar_imageAlbum的图片
            playingBar_textSinger.setText(Constant.currentPlayingMusic.getArtist());
            playingBar_textMusicName.setText(Constant.currentPlayingMusic.getMusicNameNoTail());

        }
        playingBar_imagePlay.setImageResource(R.drawable.songplayer_stop);
        MusicService.handler.sendEmptyMessage(MusicService.PLAY_START);
    }

    //////////////////////////// 底部的播放状态栏中各个控件的监听 /////////////////////////////////
    @OnClick(R.id.layout_playingBar_image_play)
    public void playOrPause(){
        System.out.println("测试播放： 点击了 暂停/播放 按钮");
        if(Constant.playState == Constant.PLAY_PLAYING){
            handler.sendEmptyMessage(DO_PLAY_PAUSE);
        }else{
            handler.sendEmptyMessage(DO_PLAY_START);
        }
    }

    @OnClick(R.id.layout_playingBar_image_next)
    public void playNext(){

        if(Constant.currentPlayingPosition < MusicUtil.musicList.size() - 1){
            Constant.currentPlayingPosition ++;
        }else {
            Constant.currentPlayingPosition =  0;
        }
        Constant.currentPlayingMusic = MusicUtil.musicList.get(Constant.currentPlayingPosition);

        Constant.playState = Constant.PLAY_STOP;
        Constant.currentProgress = 0;

        handler.sendEmptyMessage(DO_PLAY_START);
    }

    @OnClick(R.id.layout_playingBar_image_liebiao)
    public void showCatchList(){
        // TODO  待完成。。。。
        Toast.makeText(this, "显示待播放列表", Toast.LENGTH_LONG).show();
    }

    /**
     * SeekBar的滑动监听
     */
    private void setSeekBarListener() {
        playingBar_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Constant.currentProgress = progressCount = seekBar.getProgress();
                MusicService.handler.sendEmptyMessage(MusicService.CHANGE_PROGRESS);
            }
        });
    }


    //////////////////////// 以下是主界面中左侧的抽屉菜单中的按钮 ////////////////////////////////////
    private Intent intent = new Intent();

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
    public void exitApp() {
        //退出程序
    }


}
