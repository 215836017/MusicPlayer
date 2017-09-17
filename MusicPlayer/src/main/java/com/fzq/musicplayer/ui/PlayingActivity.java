package com.fzq.musicplayer.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.fzq.musicplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fzq on 2017/9/16.
 * 正在播放的界面， 显示歌词， 进行上/下 一首的切换，还有其他的操作
 */
public class PlayingActivity extends AppCompatActivity {

    @BindView(R.id.playingAct_btn_play)
    Button btnPlayOrPause;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        ButterKnife.bind(this);
    }




    ///////////// 上一首， 暂停/播放， 下一首 三个按钮的点击事件////////////////
    @OnClick(R.id.playingAct_btn_previous)
    public void playPrevious(){

    }

    @OnClick(R.id.playingAct_btn_play)
    public void playOrPause(){

    }


    @OnClick(R.id.playingAct_btn_nxet)
    public void playNext(){

    }
}
