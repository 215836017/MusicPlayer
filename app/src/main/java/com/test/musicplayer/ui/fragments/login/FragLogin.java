package com.test.musicplayer.ui.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.musicplayer.R;
import com.test.musicplayer.ui.actvities.AgreementActivity;
import com.test.musicplayer.utils.LogUtil;
import com.test.musicplayer.utils.ToastUtil;

public class FragLogin extends Fragment implements View.OnClickListener {

    private final String tag = "FragLogin.java";

    public static final int LOGIN_TYPE_PHONE = 1;
    public static final int LOGIN_TYPE_ACCOUNT = 2;
    private static final String USERNAME_PHONE = "手机号码";
    private static final String USERNAME_ACCOUNT = "账号";
    private static final String USERNAME_HINT_PHONE = "请输入手机号码";
    private static final String USERNAME_HINT_ACCOUNT = "手机/邮箱/用户名/酷狗ID";
    private static final String PWD_HINT_PHONE = "请输入验证码";
    private static final String PWD_HINT_ACCOUNT = "请输入密码";


    private TextView textUserName;
    private EditText editUserName;
    private RelativeLayout layoutPhone;
    private TextView textSendVerificationCode;
    private EditText editVerificationCode;
    private LinearLayout layoutPwd;
    private EditText editPwd;
    private Button btnLogin;
    private TextView textForgetPwd;
    private LinearLayout layoutOtherLogin;
    private TextView textKugouService, textKugouPrivate;

    private Context context;
    private int loginType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d(tag, "onCreateView() -- start...");

        context = getActivity();
        View rootView = inflater.inflate(R.layout.frag_login, container, false);
        textUserName = rootView.findViewById(R.id.fragLogin_text_userName);
        editUserName = rootView.findViewById(R.id.fragLogin_editText_userName);
        layoutPhone = rootView.findViewById(R.id.fragLogin_layout_pwdPhone);
        textSendVerificationCode = rootView.findViewById(R.id.fragLogin_text_sendVerificationCode);
        editVerificationCode = rootView.findViewById(R.id.fragLogin_editText_verificationCode);
        layoutPwd = rootView.findViewById(R.id.fragLogin_layout_pwdAccount);
        editPwd = rootView.findViewById(R.id.fragLogin_editText_pwd);
        btnLogin = rootView.findViewById(R.id.fragLogin_btn_login);
        textForgetPwd = rootView.findViewById(R.id.fragLogin_text_forgetPwd);
        layoutOtherLogin = rootView.findViewById(R.id.fragLogin_layout_otherLogin);
        textKugouService = rootView.findViewById(R.id.showAgreementLayout_text_service);
        textKugouPrivate = rootView.findViewById(R.id.showAgreementLayout_text_private);

        return rootView;
    }

    public void setViewType(int viewType) {
        LogUtil.d(tag, "setViewType() -- start, viewType = " + viewType);
        loginType = viewType;
        if (viewType == LOGIN_TYPE_PHONE) {
            textUserName.setText(USERNAME_PHONE);
            editUserName.setHint(USERNAME_HINT_PHONE);
            layoutPhone.setVisibility(View.VISIBLE);
            layoutPwd.setVisibility(View.GONE);

            textSendVerificationCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doWorkForSendVerificationCode();
                }
            });
            editVerificationCode.setHint(PWD_HINT_PHONE);
            textForgetPwd.setVisibility(View.GONE);

        } else if (viewType == LOGIN_TYPE_ACCOUNT) {
            textUserName.setText(USERNAME_ACCOUNT);
            editUserName.setHint(USERNAME_HINT_ACCOUNT);
            layoutPhone.setVisibility(View.GONE);
            layoutPwd.setVisibility(View.VISIBLE);
            editPwd.setHint(PWD_HINT_ACCOUNT);
            textForgetPwd.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 执行发送验证码的流程
     */
    private void doWorkForSendVerificationCode() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LogUtil.d(tag, "onViewCreated() -- start...");

        super.onViewCreated(view, savedInstanceState);

        btnLogin.setOnClickListener(this);
        layoutOtherLogin.setOnClickListener(this);
        textKugouService.setOnClickListener(this);
        textKugouPrivate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.fragLogin_btn_login:
                ToastUtil.showToast(context, "使用微博进行登录");
                break;

            case R.id.fragLogin_layout_otherLogin:
                doWorkForLogin();
                break;

            case R.id.showAgreementLayout_text_service:
                intent.setClass(context, AgreementActivity.class);
                intent.putExtra(AgreementActivity.EXTRA_KUGOU_MSG, AgreementActivity.EXTRA_KUGOU_SERVICE);
                startActivity(intent);
                break;

            case R.id.showAgreementLayout_text_private:
                intent.setClass(context, AgreementActivity.class);
                intent.putExtra(AgreementActivity.EXTRA_KUGOU_MSG, AgreementActivity.EXTRA_KUGOU_PRIVATE);
                startActivity(intent);
                break;

        }
    }

    /**
     * 进行登录
     */
    private void doWorkForLogin() {
        if (loginType == LOGIN_TYPE_PHONE) {

        } else if (loginType == LOGIN_TYPE_ACCOUNT) {

        }
    }
}
