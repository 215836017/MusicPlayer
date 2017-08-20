package com.fzq.musicplayer.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.fzq.musicplayer.R;
import com.fzq.musicplayer.bean.MusicInfo;
import com.fzq.musicplayer.utils.MusicUtil;

import java.util.List;

/**
 * Created by fzq on 2017/6/14.
 */

public class TestActivity2 extends AppCompatActivity {

    private ListView listView;
    private MyMusicAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        List<MusicInfo> musicList = MusicUtil.musicList;

        //打印结果见最下面
        System.out.println(musicList.get(0).toString());
        System.out.println(musicList.get(1).toString());
        System.out.println(musicList.get(2).toString());


        listView = (ListView) findViewById(R.id.localAct_list_old);
        adapter = new MyMusicAdapter(this, musicList);
        listView.setAdapter(adapter);

    }
}


/*
06-25 10:30:10.934 13160-13160/com.fzq.musicplayer I/System.out: MusicInfo{songId=5027, title='小苹果', album='全球中文音乐榜上榜 中秋演唱会', albumId=4, displayName='xiaopingguo.mp3', musicNameNoTitle='null', artist='筷子兄弟', duration=202000, size=3239667, path='/storage/emulated/0/Music/xiaopingguo.mp3', lrcTitle='null', lrcSize='null'}
06-25 10:30:10.934 13160-13160/com.fzq.musicplayer I/System.out: MusicInfo{songId=5023, title='凤凰传奇 - 最炫民族风 - DJ版', album='<unknown>', albumId=3, displayName='zuixuanminzufeng.mp3', musicNameNoTitle='null', artist='<unknown>', duration=370834, size=5933409, path='/storage/emulated/0/Music/zuixuanminzufeng.mp3', lrcTitle='null', lrcSize='null'}
06-25 10:30:10.934 13160-13160/com.fzq.musicplayer I/System.out: MusicInfo{songId=5025, title='何洁 - 小情书', album='<unknown>', albumId=3, displayName='xiaoqingshu.mp3', musicNameNoTitle='null', artist='<unknown>', duration=238493, size=3815882, path='/storage/emulated/0/Music/xiaoqingshu.mp3', lrcTitle='null', lrcSize='null'}
06-25 10:30:10.949 13160-13223/com.fzq.musicplayer W/libEGL: [ANDROID_RECORDABLE] format: 1
 */