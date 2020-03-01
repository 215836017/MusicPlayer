package com.cakes.musicplayer.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.cakes.musicplayer.R;

import java.util.Random;

public class AdvertisementActivity extends AppCompatActivity {

    private ConstraintLayout layoutRoot;
    private TextView textSkip;

    /*** default value of countdown */
    private final int TIME_COUNT = 5;
    /*** update interval for countdown */
    private final int DELAY_TIME_TO_UPDATE = 1000;
    private int timeCount;

    private final int rootBackgrounds[] = {
            R.drawable.my_image_first,
            R.drawable.my_image_second,
            R.drawable.my_image_third,
            R.drawable.my_image_fourth,
            R.drawable.my_image_fifth,
            R.drawable.my_image_sixth
    };

    private final int MSG_UPDATE_TIME = 0x10;
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_UPDATE_TIME) {
                updateTime();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_advertisement);

        initDatas();
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessage(MSG_UPDATE_TIME);
    }

    private void initDatas() {
        timeCount = TIME_COUNT;
    }

    private void initViews() {
        layoutRoot = findViewById(R.id.activity_advertisement_layout_root);
        textSkip = findViewById(R.id.activity_advertisement_text_skip);

        // 模仿动态加载广告背景页
        Random random = new Random();
        layoutRoot.setBackgroundResource(random.nextInt(rootBackgrounds.length));

        textSkip.setText(getStringForTime());
        textSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivity();
            }
        });
    }

    private void updateTime() {
        if (timeCount < 0) {
            startActivity(new Intent(this, LocalMusicActivity.class));

        } else {
            handler.sendEmptyMessageDelayed(MSG_UPDATE_TIME, DELAY_TIME_TO_UPDATE);
            timeCount--;
            textSkip.setText(getStringForTime());
        }
    }

    private String getStringForTime() {
        return "跳过(" + timeCount + ")";
    }

    private void skipActivity() {
        handler.removeMessages(MSG_UPDATE_TIME);
        startActivity(new Intent(this, LocalMusicActivity.class));
    }
}
