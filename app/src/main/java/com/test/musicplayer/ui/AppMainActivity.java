package com.test.musicplayer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.test.musicplayer.R;
import com.test.musicplayer.music.MusicFileHelper;
import com.test.musicplayer.utils.LogUtil;

/**
 * 参考链接：
 * https://www.cnblogs.com/ixuea/p/9267355.html
 * https://www.300168.com/yidong/show-2752.html
 * http://www.sohu.com/a/198518846_762571
 * https://github.com/aa112901/remusic
 * https://www.jianshu.com/p/73c711c697ce
 * https://www.cnblogs.com/JLZT1223/p/8108783.html
 * https://blog.csdn.net/Danna_lucky/article/details/50907644
 * https://github.com/agxxxx/AIDLMusicPlayer
 */
public class AppMainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String tag = "AppMainActivity.java";

    private Button btn2LocalMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addListeners();
    }

    private void init() {
        btn2LocalMusic = findViewById(R.id.MainAct_btn_toLocalMusic);

    }

    private void addListeners() {
        btn2LocalMusic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.MainAct_btn_toLocalMusic:
                if (MusicFileHelper.isGetMusicFinish) {
                    intent.setClass(this, LocalMusicActivity.class);
                    startActivity(intent);
                } else {
                    showToast("正在查询,请稍后再试!");
                }
                break;
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        LogUtil.d(tag, msg);
    }
}
