package com.test.musicplayer.ui.actvities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.test.musicplayer.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * R.layout.activity_login中的点击事件
     */
    public void clickToCancle(View view) {
        if (view.getId() == R.id.loginAct_btn_cancle) {
            goToMainAct();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goToMainAct();
        }
        return true;
    }

    public void goToMainAct() {
        startActivity(new Intent(this, AppMainActivity.class));

        finish();
    }
}
