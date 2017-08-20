package com.fzq.musicplayer.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fzq.musicplayer.Constant;
import com.fzq.musicplayer.R;
import com.fzq.musicplayer.test.TestActivity2;
import com.fzq.musicplayer.utils.SharedPreferensUtil;

public class WelcomeActivity extends AppCompatActivity {

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sp = SharedPreferensUtil.getInstance();

        Constant.currentPlayingPath = sp.getString(Constant.PLAYING_MUSIC_PATH, "");


        findViewById(R.id.wclAct_btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });
    }

    private void goToMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
