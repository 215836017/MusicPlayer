package com.test.musicplayer.ui.actvities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.musicplayer.R;
import com.test.musicplayer.ui.actvities.menus.SettingActivity;
import com.test.musicplayer.ui.adapters.MenuRecyclerAdapter;
import com.test.musicplayer.ui.menu.MenuListData;
import com.test.musicplayer.ui.recycler.RecyclerDecoration;

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

    private final String TAG = "AppMainActivity.java";

    // -------------- left menu -------------
    private RecyclerView menuRecyclerView;
    private LinearLayout layoutSetting, layoutExitLogin, layoutClose;

    // -------------- right main -------------
    private DrawerLayout drawerLayout;
    private TextView imageMenu;
    private TextView textMe, textTing, textKan, textChang;
    private TextView imageMore;
    private TextView searchVIew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initFragments();
    }

    private void initViews() {
        initLeftMenuViews();
        initMainViews();
    }

    private void initLeftMenuViews() {
        menuRecyclerView = findViewById(R.id.mainAct_leftMenu_recyclerView);
        layoutSetting = findViewById(R.id.mainAct_leftMenu_bottomLayout_setting);
        layoutExitLogin = findViewById(R.id.mainAct_leftMenu_bottomLayout_exitLogin);
        layoutClose = findViewById(R.id.mainAct_leftMenu_bottomLayout_close);

        layoutSetting.setOnClickListener(this);
        layoutExitLogin.setOnClickListener(this);
        layoutClose.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        menuRecyclerView.setLayoutManager(layoutManager);
        menuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        menuRecyclerView.addItemDecoration(new RecyclerDecoration(this, OrientationHelper.HORIZONTAL));
        MenuRecyclerAdapter menuAdapter = new MenuRecyclerAdapter(this, MenuListData.getInstance().getMenuDatas());
        menuRecyclerView.setAdapter(menuAdapter);
    }

    private void initMainViews() {
        drawerLayout = findViewById(R.id.mainAct_drawerLayout);
        imageMenu = findViewById(R.id.mainAct_text_menu);
        textMe = findViewById(R.id.mainAct_text_me);
        textTing = findViewById(R.id.mainAct_text_ting);
        textKan = findViewById(R.id.mainAct_text_kan);
        textChang = findViewById(R.id.mainAct_text_chang);
        imageMore = findViewById(R.id.mainAct_text_more);
        searchVIew = findViewById(R.id.mainAct_searchView);

        imageMenu.setOnClickListener(this);
        textMe.setOnClickListener(this);
        textTing.setOnClickListener(this);
        textKan.setOnClickListener(this);
        textChang.setOnClickListener(this);
        imageMore.setOnClickListener(this);
        searchVIew.setOnClickListener(this);
    }

    private void initFragments() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mainAct_leftMenu_bottomLayout_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;

            case R.id.mainAct_leftMenu_bottomLayout_exitLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.mainAct_leftMenu_bottomLayout_close:
                exitApp();
                break;

            case R.id.mainAct_text_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;

            case R.id.mainAct_text_me:
                break;

            case R.id.mainAct_text_ting:
                break;

            case R.id.mainAct_text_kan:
                break;

            case R.id.mainAct_text_chang:
                break;

            case R.id.mainAct_text_more:
                break;

            case R.id.mainAct_searchView:
                break;
        }
    }

    /**
     * 完全退出整个程序
     */
    private void exitApp() {

    }
}
