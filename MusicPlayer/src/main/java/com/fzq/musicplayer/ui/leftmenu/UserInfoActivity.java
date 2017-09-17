package com.fzq.musicplayer.ui.leftmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.fzq.musicplayer.R;

import butterknife.BindView;

/**
 * Created by fzq on 2017/8/15.
 */
public class UserInfoActivity extends AppCompatActivity {

    @BindView(R.id.userInfoAct_image)
    ImageView imageView;

    private boolean isLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        if(isLogin){
            imageView.setImageResource(R.drawable.after_login);
        }else{
            imageView.setImageResource(R.drawable.before_login);
        }
    }
}
