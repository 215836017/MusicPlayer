package com.test.musicplayer.ui.menu;

import com.test.musicplayer.R;
import com.test.musicplayer.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MenuListData {

    private final String TAG = "MenuListData.java";

    public static MenuListData getInstance() {
        return new MenuListData();
    }

    public List<MenuBean> getMenuDatas() {
        List<MenuBean> datas = new ArrayList<>();

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "",
                0, 0, false, MenuItemTag.MENU_TAG_NEWS_CENTRE));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, false, MenuItemTag.MENU_TAG_SKIN_CENTRE));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, false, MenuItemTag.MENU_TAG_VIP_CENTRE));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, false, MenuItemTag.MENU_TAG_FLOW_MONTHLY));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, false, MenuItemTag.MENU_TAG_PRIVATE_CLOUD));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, false, MenuItemTag.MENU_TAG_TIME_CLOSURE));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, false, MenuItemTag.MENU_TAG_VIPER_SOUND_EFFECT));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, false, MenuItemTag.MENU_TAG_LISTEN_KNOW_MUSIC));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, false, MenuItemTag.MENU_TAG_MUSIC_TOOLS));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, true, MenuItemTag.MENU_TAG_DRIVING_MODE));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, true, MenuItemTag.MENU_TAG_SOUNDS));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, true, MenuItemTag.MENU_TAG_CHILDREN_AREA));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, true, MenuItemTag.MENU_TAG_MUSIC_MARKET));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, true, MenuItemTag.MENU_TAG_DESKTOP_LYRIC));

        datas.add(new MenuBean(false, R.drawable.ic_launcher_background,
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "menuSepplement",
                0, 0, true, MenuItemTag.MENU_TAG_LOCK_SCREEN_LYRIC));

        LogUtil.i(TAG, "getMenuDatas() -- datas.size = " + datas.size());
        return datas;
    }

    public List<MenuBean> updatMenuItem(MenuBean menuBean) {
        List<MenuBean> datas = getMenuDatas();
        int i = 0;
        int index = -1;
        for (; i < datas.size(); i++) {
            if (datas.get(i).getMenuItemTag() == menuBean.getMenuItemTag()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            datas.remove(index);
            datas.add(index, menuBean);
        }

        return datas;
    }
}
