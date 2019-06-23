package com.test.musicplayer.ui.actvities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.test.musicplayer.R;
import com.test.musicplayer.constants.SPConstants;
import com.test.musicplayer.utils.LogUtil;
import com.test.musicplayer.utils.SPUtil;
import com.test.musicplayer.utils.ScreenUtil;

public class NewVersionStartActivity extends AppCompatActivity implements View.OnClickListener {

    private final String tag = "NewVersionStartActivity.java";

    private ImageSwitcher imageSwitcher;
    private TextView textSkip;
    private TextView textStartApp;
    private ImageView imageCricle_01, imageCricle_02, imageCricle_03, imageCricle_04;
    private ImageView[] cricleImages = new ImageView[4];

    private final int imageRes[] = {
            R.drawable.my_image_01,
            R.drawable.my_image_02,
            R.drawable.my_image_03,
            R.drawable.my_image_04
    };

    private int imageIndex = 0;
    private int distance2Change = 0;
    private int x_down = 0;
    private int x_move = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_version_start);

        SPUtil.put(this, SPConstants.SP_FIRST_NEW_VERSION_START, getString(R.string.app_version));
        initDatas();
        initViews();
    }


    private void initViews() {
        imageSwitcher = findViewById(R.id.newVersionStartAct_imageSwitcher);
        textSkip = findViewById(R.id.newVersionStartAct_text_skip);
        textStartApp = findViewById(R.id.newVersionStartAct_text_start);
        imageCricle_01 = findViewById(R.id.newVersionStartAct_image_01);
        imageCricle_02 = findViewById(R.id.newVersionStartAct_image_02);
        imageCricle_03 = findViewById(R.id.newVersionStartAct_image_03);
        imageCricle_04 = findViewById(R.id.newVersionStartAct_image_04);

        textStartApp.setOnClickListener(this);
        textSkip.setOnClickListener(this);

        cricleImages[0] = imageCricle_01;
        cricleImages[1] = imageCricle_02;
        cricleImages[2] = imageCricle_03;
        cricleImages[3] = imageCricle_04;

        // 设置动画效果
        // imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
        //  imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
       /*
        imageSwitcher.setImageResource(imageRes[imageIndex]); 放在 imageSwitcher.setFactory前面会报如下错误：
         Caused by: java.lang.NullPointerException
        at android.widget.ImageSwitcher.setImageResource(ImageSwitcher.java:41)
        */
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(NewVersionStartActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                return imageView;
            }
        });
        imageSwitcher.setImageResource(imageRes[imageIndex]);
    }

    private void initDatas() {
        int[] wh = ScreenUtil.getScreenWidthAndHeight(this);
        LogUtil.d(tag, "initDatas() -- screen_width = " + wh[0] + ", screen_height = " + wh[1]);
        distance2Change = wh[0] / 4;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.newVersionStartAct_text_skip || v.getId() == R.id.newVersionStartAct_text_start) {
            startActivity(new Intent(this, AppMainActivity.class));
            this.finish();
        }
    }

    private boolean isDown = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x_down = (int) event.getX();
                isDown = true;
                break;

            case MotionEvent.ACTION_MOVE:
                x_move = (int) event.getX();
                if (isDown && (x_down - x_move) >= distance2Change) {
                    // to right
                    isDown = false;
                    if (imageIndex < (imageRes.length - 1)) {
                        imageIndex++;
                        imageSwitcher.setImageResource(imageRes[imageIndex]);
                        changeCricleImage();
                    }

                } else if (isDown && (x_move - x_down) >= distance2Change) {
                    // to left
                    isDown = false;
                    if (imageIndex > 0) {
                        imageIndex--;
                        imageSwitcher.setImageResource(imageRes[imageIndex]);
                        changeCricleImage();
                    }
                }

                break;

            case MotionEvent.ACTION_UP:
                x_down = 0;
                x_move = 0;

                break;
        }
        return true;
    }

    private void changeCricleImage() {

        if ((cricleImages.length - 1) == imageIndex) {
            textStartApp.setVisibility(View.VISIBLE);
        } else {
            textStartApp.setVisibility(View.GONE);
        }

        for (int i = 0; i < cricleImages.length; i++) {
            if (i == imageIndex) {
                cricleImages[i].setImageResource(R.drawable.btn_radio_on_holo_light);
            } else {
                cricleImages[i].setImageResource(R.drawable.btn_radio_off_holo_light);
            }
        }
    }


}
