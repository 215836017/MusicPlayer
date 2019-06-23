package com.test.musicplayer.ui.actvities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.musicplayer.R;
import com.test.musicplayer.ui.fragments.login.FragLogin;

public class LoginActivity extends AppCompatActivity {

    private final String tag = "LoginActivity.java";

    private ImageView imageBack;
    private TextView textPhone, textAccount;
    private ViewPager viewPager;

    private FragLogin fragPhoneLogin, fragAccountLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        initDatas();
    }

    private void initView() {
        imageBack = findViewById(R.id.LoginAct_iamge_back);
        textPhone = findViewById(R.id.LoginAct_text_phoneLogin);
        textAccount = findViewById(R.id.LoginAct_text_accountLogin);
        viewPager = findViewById(R.id.LoginAct_viewPager);

    }

    private void initDatas() {
        fragPhoneLogin = new FragLogin();
        fragAccountLogin = new FragLogin();

        fragPhoneLogin.setViewType(FragLogin.LOGIN_TYPE_PHONE);
        fragAccountLogin.setViewType(FragLogin.LOGIN_TYPE_ACCOUNT);
    }
}
