package com.test.musicplayer.ui.menu;

import com.test.musicplayer.R;
import com.test.musicplayer.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import static com.test.musicplayer.ui.menu.MenuItemTag.MENU_ICONS;

public class MenuListData {

    private final String TAG = "MenuListData.java";

    public static MenuListData getInstance() {
        return new MenuListData();
    }

    public List<MenuBean> getMenuDatas() {
        List<MenuBean> datas = new ArrayList<>();

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_NEWS_CENTRE],
                MenuItemTag.MENU_STR_NEWS_CENTRE, 2, "",
                0, 0, false, MenuItemTag.MENU_TAG_NEWS_CENTRE));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_SKIN_CENTRE],
                MenuItemTag.MENU_STR_SKIN_CENTRE, 2, "爱cos的酷奇",
                0, 2, false, MenuItemTag.MENU_TAG_SKIN_CENTRE));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_VIP_CENTRE],
                MenuItemTag.MENU_STR_VIP_CENTRE, 2, "",
                0, 0, false, MenuItemTag.MENU_TAG_VIP_CENTRE));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_FLOW_MONTHLY],
                MenuItemTag.MENU_STR_FLOW_MONTHLY, 2, "听歌免流量",
                0, 0, false, MenuItemTag.MENU_TAG_FLOW_MONTHLY));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_PRIVATE_CLOUD],
                MenuItemTag.MENU_STR_PRIVATE_CLOUD, 2, "",
                0, 0, false, MenuItemTag.MENU_TAG_PRIVATE_CLOUD));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_TIME_CLOSURE],
                MenuItemTag.MENU_STR_TIME_CLOSURE, 2, "",
                0, 0, false, MenuItemTag.MENU_TAG_TIME_CLOSURE));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_VIPER_SOUND_EFFECT],
                MenuItemTag.MENU_STR_VIPER_SOUND_EFFECT, 2, "",
                0, 0, false, MenuItemTag.MENU_TAG_VIPER_SOUND_EFFECT));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_LISTEN_KNOW_MUSIC],
                MenuItemTag.MENU_STR_LISTEN_KNOW_MUSIC, 2, "",
                0, 0, false, MenuItemTag.MENU_TAG_LISTEN_KNOW_MUSIC));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_MUSIC_TOOLS],
                MenuItemTag.MENU_STR_MUSIC_TOOLS, 2, "听觉保护等",
                0, 0, false, MenuItemTag.MENU_TAG_MUSIC_TOOLS));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_DRIVING_MODE],
                MenuItemTag.MENU_STR_DRIVING_MODE, 2, "",
                0, 0, true, MenuItemTag.MENU_TAG_DRIVING_MODE));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_SOUNDS],
                MenuItemTag.MENU_STR_SOUNDS, 2, "",
                0, 0, true, MenuItemTag.MENU_TAG_SOUNDS));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_CHILDREN_AREA],
                MenuItemTag.MENU_STR_CHILDREN_AREA, 2, "快乐成长 有我陪伴",
                0, 0, true, MenuItemTag.MENU_TAG_CHILDREN_AREA));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_MUSIC_MARKET],
                MenuItemTag.MENU_STR_MUSIC_MARKET, 2, "粉丝专属优惠",
                0, 0, true, MenuItemTag.MENU_TAG_MUSIC_MARKET));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_DESKTOP_LYRIC],
                MenuItemTag.MENU_STR_DESKTOP_LYRIC, 2, "",
                0, 0, true, MenuItemTag.MENU_TAG_DESKTOP_LYRIC));

        datas.add(new MenuBean(false, MENU_ICONS[MenuItemTag.MENU_TAG_LOCK_SCREEN_LYRIC],
                MenuItemTag.MENU_STR_LOCK_SCREEN_LYRIC, 2, "",
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
