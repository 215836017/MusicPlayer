package com.test.musicplayer.ui.actvities;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.musicplayer.R;
import com.test.musicplayer.ui.fragments.FragmentAdapter;
import com.test.musicplayer.ui.fragments.login.FragLogin;
import com.test.musicplayer.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, FragLogin.OnLoginResult {

    private final String tag = "LoginActivity.java";

    private ImageView imageBack;
    private TextView textPhone, textAccount;
    private ViewPager viewPager;

    private FragLogin fragPhoneLogin, fragAccountLogin;
    private List<Fragment> fragList = new ArrayList<>();

    private final int PAGER_INDEX_PHONE = 0;
    private final int PAGER_INDEX_ACCOUNT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LogUtil.i(tag, "onCreate() -- start...");
        initDatas();
        initView();

    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i(tag, "onStop() -- start...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(tag, "onDestroy() -- start...");
    }

    private void initDatas() {
        LogUtil.i(tag, "initDatas() -- start...");
        fragPhoneLogin = new FragLogin();
        fragAccountLogin = new FragLogin();

        fragPhoneLogin.setViewType(FragLogin.LOGIN_TYPE_PHONE);
        fragAccountLogin.setViewType(FragLogin.LOGIN_TYPE_ACCOUNT);

        fragList.add(fragPhoneLogin);
        fragList.add(fragAccountLogin);

        fragPhoneLogin.setOnLoginResult(this);
        fragAccountLogin.setOnLoginResult(this);

    }

    private void initView() {
        imageBack = findViewById(R.id.LoginAct_iamge_back);
        textPhone = findViewById(R.id.LoginAct_text_phoneLogin);
        textAccount = findViewById(R.id.LoginAct_text_accountLogin);
        viewPager = findViewById(R.id.LoginAct_viewPager);

        imageBack.setOnClickListener(this);
        textPhone.setOnClickListener(this);
        textAccount.setOnClickListener(this);

        changeItem(PAGER_INDEX_PHONE);

        viewPager.setCurrentItem(PAGER_INDEX_PHONE);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragList);
        LogUtil.i(tag, "initView() -- viewPager.setAdapter 0000");
        viewPager.setAdapter(fragmentAdapter);
        LogUtil.i(tag, "initView() -- viewPager.setAdapter 1111");
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                changeItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.LoginAct_iamge_back) {
            finish();

        } else if (v.getId() == R.id.LoginAct_text_phoneLogin) {
            viewPager.setCurrentItem(PAGER_INDEX_PHONE);
            changeItem(PAGER_INDEX_PHONE);

        } else if (v.getId() == R.id.LoginAct_text_accountLogin) {
            viewPager.setCurrentItem(PAGER_INDEX_ACCOUNT);
            changeItem(PAGER_INDEX_ACCOUNT);
        }
    }

    private void changeItem(int index) {
        if (PAGER_INDEX_PHONE == index) {
            textPhone.setTextColor(Color.BLUE);
            textAccount.setTextColor(Color.BLACK);

        } else if (PAGER_INDEX_ACCOUNT == index) {
            textPhone.setTextColor(Color.BLACK);
            textAccount.setTextColor(Color.BLUE);
        }
    }


    @Override
    public void loginSucc(int loginType) {
        if (loginType == FragLogin.LOGIN_TYPE_PHONE) {

        } else if (loginType == FragLogin.LOGIN_TYPE_ACCOUNT) {

        }
    }

    @Override
    public void loginFail(int loginType) {
        if (loginType == FragLogin.LOGIN_TYPE_PHONE) {

        } else if (loginType == FragLogin.LOGIN_TYPE_ACCOUNT) {

        }
    }
}
