package com.cakes.musicplayer.ui.activities;

import android.os.Handler;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {

    public final int MSG_LEVEL_LOCAL = 0;
    public final int MSG_LEVEL_PLAY = 100;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMsgInHandler(msg);
        }
    };

    public abstract void handleMsgInHandler(Message message);
}
