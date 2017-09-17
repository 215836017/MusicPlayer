package com.fzq.musicplayer.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fzq.musicplayer.Constant;
import com.fzq.musicplayer.R;
import com.fzq.musicplayer.service.MusicService;
import com.fzq.musicplayer.utils.SharedPreferensUtil;

import java.util.HashMap;

public class WelcomeActivity extends AppCompatActivity {

    private SharedPreferences sp;

    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sp = SharedPreferensUtil.getInstance(this);

        Constant.currentPlayingPath = sp.getString(Constant.PLAYING_MUSIC_PATH, "");


        AudioAttributes attr = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder().setAudioAttributes(attr).setMaxStreams(20).build();

        soundMap.put(1, soundPool.load(this, R.raw.hello_kugou, 1));
//        soundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);

        startService(new Intent(this, MusicService.class));

        playWelcome();

    }

    /**
     * 这里有个坑就是：SoundPool.load加载文件之后不能立即执行play进行播放，这样的话是没有效果的，
     * 要等最少1秒之后再播放。
     */
    private void playWelcome(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    sleep(1 * 1000);
                    soundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
                    goToMainActivity();
                }catch (Exception e){}

            }
        }.start();
    }

    private void goToMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();

    }
}
