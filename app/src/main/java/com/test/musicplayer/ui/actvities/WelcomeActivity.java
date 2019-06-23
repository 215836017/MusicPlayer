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

    private final String tag = "WelcomeActivity.java";

    private CricleSkipProgressBar cricleSkipProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        checkNeedShowNewVersionActivity();
    }

    private void checkNeedShowNewVersionActivity() {
        String appVersion = getString(R.string.app_version);
        if (!SPUtil.get(this, SPConstants.SP_FIRST_NEW_VERSION_START, "").equals(appVersion)) {
            LogUtil.d(tag, "checkNeedShowNewVersionActivity() -- start NewVersionStartActivity");
            startActivity(new Intent(this, NewVersionStartActivity.class));
            finish();

        } else {

            init();
        }
    }

    private void init() {
        LogUtil.i(tag, "init() -- start...");
        // 还有一个闪屏页面
        cricleSkipProgressBar = findViewById(R.id.welcomeAct_skipBar);

        cricleSkipProgressBar.setMaxProgress(4);
        cricleSkipProgressBar.setOnProgressAnimListener(this);
        cricleSkipProgressBar.setOnClickListener(this);

        cricleSkipProgressBar.startAnim();
    }

    @Override
    public void progressAnimFinish() {
        LogUtil.i(tag, "progressAnimFinish() -- call finishWelcomAct()");
        finishWelcomAct();
    }

    @Override
    public void onClick(View v) {
        cricleSkipProgressBar.stopAnim();
        finishWelcomAct();
    }

    private void finishWelcomAct() {
        boolean hasLogin = (boolean) SPUtil.get(this, SPConstants.SP_HAS_LOGIN, false);
        LogUtil.i(tag, "finishWelcomAct() -- hasLogin = " + hasLogin);
        if (hasLogin) {
            startActivity(new Intent(this, AppMainActivity.class));
        } else {
            startActivity(new Intent(this, ChoseLoginModeActivity.class));
        }

        finish();
    }
}
