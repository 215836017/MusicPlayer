package com.test.musicplayer.ui.actvities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.test.musicplayer.R;
import com.test.musicplayer.constants.SPConstants;
import com.test.musicplayer.ui.views.CricleSkipProgressBar;
import com.test.musicplayer.utils.LogUtil;
import com.test.musicplayer.utils.SPUtil;

/**
 * 1. 闪屏页
 * 2. “跳过”动画
 * 3. 登录界面
 * 4. 如果关闭登录界面，则进入主界面
 */
public class WelcomeActivity extends AppCompatActivity implements CricleSkipProgressBar.OnProgressAnimListener, View.OnClickListener {

    private final String TAG = "WelcomeActivity.java";

    private CricleSkipProgressBar cricleSkipProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        checkNeedShowNewVersionActivity();
    }

    private void checkNeedShowNewVersionActivity() {
        String appVersion = getString(R.string.app_version);
        if (SPUtil.get(this, SPConstants.SP_FIRST_NEW_VERSION_START, "").equals(appVersion)) {
            startActivity(new Intent(this, NewVersionStartActivity.class));
            finish();

        } else {

            init();
        }
    }

    private void init() {
        // 还有一个闪屏页面
        cricleSkipProgressBar = findViewById(R.id.welcomeAct_skipBar);

        cricleSkipProgressBar.setOnProgressAnimListener(this);
        cricleSkipProgressBar.setOnClickListener(this);

        cricleSkipProgressBar.startAnim();
    }

    @Override
    public void progressAnimFinish() {
        goToMainAct();
    }

    @Override
    public void onClick(View v) {
        goToMainAct();
    }

    private void goToMainAct() {
        boolean hasLogin = (boolean) SPUtil.get(this, SPConstants.SP_HAS_LOGIN, false);
        LogUtil.i(TAG, "goToMainAct() -- hasLogin = " + hasLogin);
        if (hasLogin) {
            startActivity(new Intent(this, AppMainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }

        finish();
    }
}
