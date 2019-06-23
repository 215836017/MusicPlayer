package com.test.musicplayer.ui.actvities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.test.musicplayer.R;
import com.test.musicplayer.utils.ToastUtil;

public class ChoseLoginModeActivity extends AppCompatActivity {

    private final String tag = "ChoseLoginModeActivity.java";

    private TextView textKugouService, textKugouPrivate;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_login_mode);

        initView();
    }

    private void initView() {
        textKugouService = findViewById(R.id.showAgreementLayout_text_service);
        textKugouPrivate = findViewById(R.id.showAgreementLayout_text_private);

        textKugouService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showService();
            }
        });

        textKugouPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrivate();
            }
        });
    }

    /**
     * R.layout.activity_login中的点击事件
     */
    public void choseLoginModeClick(View view) {

        switch (view.getId()) {
            case R.id.ChoseLoginModeAct_image_cancle:
                goToMainAct();
                break;

            case R.id.ChoseLoginModeAct_layout_weChatLogin:
                ToastUtil.showLongToast(this, "使用微信登录...");
                // TODO 需要检测是否安装了微信 -- 即跳转时是否可以成功
                break;

            case R.id.ChoseLoginModeAct_layout_QQLogin:
                ToastUtil.showLongToast(this, "使用QQ登录...");
                break;

            case R.id.ChoseLoginModeAct_text_otherLogin:
                intent.setClass(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goToMainAct();
        }
        return true;
    }

    private void showService() {
        intent.setClass(this, AgreementActivity.class);
        intent.putExtra(AgreementActivity.EXTRA_KUGOU_MSG, AgreementActivity.EXTRA_KUGOU_SERVICE);
        startActivity(intent);
    }

    private void showPrivate() {
        intent.setClass(this, AgreementActivity.class);
        intent.putExtra(AgreementActivity.EXTRA_KUGOU_MSG, AgreementActivity.EXTRA_KUGOU_PRIVATE);
        startActivity(intent);
    }


    public void goToMainAct() {
        intent.setClass(this, AppMainActivity.class);
        startActivity(intent);

        finish();
    }

}
