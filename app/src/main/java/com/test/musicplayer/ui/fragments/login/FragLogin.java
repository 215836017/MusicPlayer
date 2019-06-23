package com.test.musicplayer.ui.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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

    private OnLoginResult onLoginResult;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.d(tag, "onAttach() -- start...");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(tag, "onCreate() -- start...");
    }


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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d(tag, "onActivityCreated() -- start...");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.d(tag, "onStart() -- start...");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d(tag, "onResume() -- start...");
        setFragType();
    }


    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d(tag, "onPause() -- start...");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.d(tag, "onStop() -- start...");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.d(tag, "onDestroyView() -- start...");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(tag, "onDestroy() -- start...");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.d(tag, "onDetach() -- start...");
    }

    public void setViewType(int viewType) {
        LogUtil.d(tag, "setViewType() -- start, viewType = " + viewType);
        loginType = viewType;
    }

    private void setFragType() {
        LogUtil.d(tag, "setFragType() -- start, loginType = " + loginType);
        if (loginType == LOGIN_TYPE_PHONE) {
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

        } else if (loginType == LOGIN_TYPE_ACCOUNT) {
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
                ToastUtil.showToast(context, "正在登录中...");
                doWorkForLogin();
                break;

            case R.id.fragLogin_layout_otherLogin:
                ToastUtil.showToast(context, "使用微博进行登录");
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
        String userName = editUserName.getText().toString().trim();
        if (loginType == LOGIN_TYPE_PHONE) {
            String verificationCode = editVerificationCode.getText().toString().trim();
            if (TextUtils.isEmpty(userName)) {
                ToastUtil.showToast(context, "手机号码不能为空");
            } else if (TextUtils.isEmpty(verificationCode)) {
                ToastUtil.showToast(context, "验证码不能为空");
            } else {
                ToastUtil.showToast(context, "正在通过手机号码方式登录...");
                LoginSuccThread loginSuccThread = new LoginSuccThread();
                loginSuccThread.start();
            }

        } else if (loginType == LOGIN_TYPE_ACCOUNT) {
            String pwd = editPwd.getText().toString().trim();
            if (TextUtils.isEmpty(userName)) {
                ToastUtil.showToast(context, "账号不能为空");
            } else if (TextUtils.isEmpty(pwd)) {
                ToastUtil.showToast(context, "密码不能为空");
            } else {
                ToastUtil.showToast(context, "正在通过账号方式登录...");
                LoginSuccThread loginSuccThread = new LoginSuccThread();
                loginSuccThread.start();
            }
        }
    }

    public void setOnLoginResult(OnLoginResult onLoginResult) {
        this.onLoginResult = onLoginResult;
    }

    public interface OnLoginResult {
        void loginSucc(int loginType);

        void loginFail(int loginType);
    }

    /**
     * 模拟登录的线程
     */
    class LoginSuccThread extends Thread {
        @Override
        public void run() {
            super.run();

            try {
                sleep(2000);
                onLoginResult.loginFail(loginType);

                sleep(3000);
                onLoginResult.loginSucc(loginType);
            } catch (Exception e) {

            }
        }
    }
}
