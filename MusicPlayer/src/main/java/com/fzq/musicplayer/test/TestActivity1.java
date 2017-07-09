package com.fzq.musicplayer.test;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.fzq.musicplayer.R;

import java.io.File;
import java.io.IOException;

/**
 * 关联了3首歌，进行简单的上一首，下一首，开始/暂停，停止的测试
 * Created by fzq on 2017/5/9.
 */
public class TestActivity1 extends AppCompatActivity implements View.OnClickListener {

    private Button btnSong1, btnSong2, btnSong3;
    private Button btnPrevious, btnPlay, btnNext, btnStop;
    private SeekBar seekBar;

    private String[] songPaths = new String[3];

    private MediaPlayer mediaPlayer;

    /** 当前进度 */
    private int currentPosition;
    /** 当前音乐文件的路径 */
    private int currentSong = 0;
    /** 当前的播放状态 */
    private boolean isPlaying = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initViews();


//        testDatas();
        initDatas();
        initMediaPlayer();

    }


    private void initViews() {
        btnSong1 = (Button) findViewById(R.id.testAct_btn_song1);
        btnSong2 = (Button) findViewById(R.id.testAct_btn_song2);
        btnSong3 = (Button) findViewById(R.id.testAct_btn_song3);
        btnPrevious = (Button) findViewById(R.id.testAct_btn_previous);
        btnPlay = (Button) findViewById(R.id.testAct_btn_play);
        btnNext = (Button) findViewById(R.id.testAct_btn_next);
        btnStop = (Button) findViewById(R.id.testAct_btn_stop);
        seekBar = (SeekBar) findViewById(R.id.testAct_seekBar);

        btnSong1.setOnClickListener(this);
        btnSong2.setOnClickListener(this);
        btnSong3.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        seekBar.setProgress(currentPosition);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    private void initDatas() {
        String rootPath = Environment.getExternalStorageDirectory().toString();
        songPaths[0] = rootPath + "/music/xiaopingguo.mp3";
        songPaths[1] = rootPath + "/music/xiaoqingshu.mp3";
        songPaths[2] = rootPath + "/music/zuixuanminzufeng.mp3";

    }

    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                System.out.println("mediaPlayer --- setOnCompletionListener");
            }
        });


        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                System.out.println("mediaPlayer --- setOnPreparedListener");
            }
        });


        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                System.out.println("mediaPlayer --- setOnErrorListener");
                return false;
            }
        });


    }


    @Override
    public void onClick(View v) {
            switch (v.getId()) {

                case R.id.testAct_btn_song1:
                    currentSong = 0;
                    startPlay();
                    break;
                case R.id.testAct_btn_song2:
                    currentSong = 1;
                    startPlay();
                    break;
                case R.id.testAct_btn_song3:
                    currentSong = 2;
                    startPlay();
                    break;
                case R.id.testAct_btn_previous:
                    if (0 == currentSong) {
                        currentSong = 2;
                    } else {
                        currentSong--;
                    }
                    startPlay();
                    break;
                case R.id.testAct_btn_next:
                    if (2 == currentSong) {
                        currentSong = 0;
                    } else {
                        currentSong++;
                    }
                    startPlay();
                    break;
                case R.id.testAct_btn_play:
                    playOrPause();
                    break;
                case R.id.testAct_btn_stop:

                    break;


            }

    }



    private void playOrPause() {

        isPlaying = mediaPlayer.isPlaying();

        if (isPlaying) {
            btnPlay.setText("播放");
            currentPosition = mediaPlayer.getCurrentPosition();
        } else {
            btnPlay.setText("暂停");
            startPlay();
        }
    }

    private void startPlay() {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(songPaths[currentSong]);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
        }
    }


    private void testDatas() {
        System.out.println("TestActivity1.java ---- initViews()");

        File dataDirectory = Environment.getDataDirectory();
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File rootDirectory = Environment.getRootDirectory();

        boolean isSDExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (isSDExist) {
            System.out.println("sdCard exist");
            System.out.println("dataDirectory： " + dataDirectory.toString());
            System.out.println("externalStorageDirectory：" + externalStorageDirectory.toString());
            System.out.println("rootDirectory：" + rootDirectory.toString());

            /*
            输出结果是：
            sdCard exist
            dataDirectory： /data
            externalStorageDirectory：/storage/emulated/0
            rootDirectory：/system
            */

            //因此music的父目录是externalStorageDirectory
            File musicFile = new File(externalStorageDirectory.toString() + "/music/xiaopingguo.mp3");
            if (musicFile.exists()) {
                System.out.println("the size of xiaopingguo.mp3: " + musicFile.length());
            } else {
                System.out.println("music file is not exist");
            }

            File testFile = new File(externalStorageDirectory.toString() + "/Music/test.txt");
            if (testFile.exists()) {
                System.out.println("test " + testFile.length());
            } else {
                try {
                    testFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
