package com.test.musicplayer.ui.actvities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.musicplayer.R;
import com.test.musicplayer.ui.actvities.menus.SettingActivity;
import com.test.musicplayer.ui.adapters.MenuRecyclerAdapter;
import com.test.musicplayer.ui.fragments.FragListen;
import com.test.musicplayer.ui.fragments.FragMe;
import com.test.musicplayer.ui.fragments.FragSing;
import com.test.musicplayer.ui.fragments.FragWatch;
import com.test.musicplayer.ui.fragments.FragmentAdapter;
import com.test.musicplayer.ui.menu.MenuListData;
import com.test.musicplayer.ui.recycler.RecyclerDecoration;
import com.test.musicplayer.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

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
 *
 *
 * 关于Android 悬浮窗问题以及仿网易云音乐底部播放控制栏实现
 * https://blog.csdn.net/canra/article/details/79393377
 * https://blog.csdn.net/zhangphil/article/details/51038080
 */
public class AppMainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String tag = "AppMainActivity.java";

    // -------------- left menu -------------
    private RecyclerView menuRecyclerView;
    private LinearLayout layoutSetting, layoutExitLogin, layoutClose;

    // -------------- right main -------------
    private DrawerLayout drawerLayout;
    private ImageView imageMenu;
    private ImageView textMe, textTing, textKan, textChang;
    private ImageView imageMore;
    private TextView searchVIew;
    private ViewPager viewPager;

    private List<Fragment> fragmentList = new ArrayList<>();
    private FragMe fragMe;
    private FragListen fragListen;
    private FragSing fragSing;
    private FragWatch fragWatch;

    private final int FRAG_INDEX_ME = 0;
    private final int FRAG_INDEX_HEAR = 0;
    private final int FRAG_INDEX_WATCH = 0;
    private final int FRAG_INDEX_SING = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
        initViews();

    }

    private void initFragments() {
        fragMe = new FragMe();
        fragListen = new FragListen();
        fragWatch = new FragWatch();
        fragSing = new FragSing();

        fragmentList.add(fragMe);
        fragmentList.add(fragListen);
        fragmentList.add(fragWatch);
        fragmentList.add(fragSing);
    }

    private void initViews() {
        initLeftMenuViews();
        initMainViews();
    }

    private void initLeftMenuViews() {
        LogUtil.i(tag, "initLeftMenuViews() -- start...");
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
        viewPager = findViewById(R.id.mainAct_viewPager);

        imageMenu.setOnClickListener(this);
        textMe.setOnClickListener(this);
        textTing.setOnClickListener(this);
        textKan.setOnClickListener(this);
        textChang.setOnClickListener(this);
        imageMore.setOnClickListener(this);
        searchVIew.setOnClickListener(this);

        changeFragment(FRAG_INDEX_HEAR);
        viewPager.setCurrentItem(FRAG_INDEX_HEAR);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mainAct_leftMenu_bottomLayout_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;

            case R.id.mainAct_leftMenu_bottomLayout_exitLogin:
                startActivity(new Intent(this, ChoseLoginModeActivity.class));
                break;

            case R.id.mainAct_leftMenu_bottomLayout_close:
                exitApp();
                break;

            case R.id.mainAct_text_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;

            case R.id.mainAct_text_me:
                changeFragment(FRAG_INDEX_ME);
                break;

            case R.id.mainAct_text_ting:
                changeFragment(FRAG_INDEX_HEAR);
                break;

            case R.id.mainAct_text_kan:
                changeFragment(FRAG_INDEX_WATCH);
                break;

            case R.id.mainAct_text_chang:
                changeFragment(FRAG_INDEX_SING);
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

    private void changeFragment(int fragIndex) {

    }
}
