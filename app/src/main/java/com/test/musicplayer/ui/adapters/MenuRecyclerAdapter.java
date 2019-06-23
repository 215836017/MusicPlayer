package com.test.musicplayer.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.test.musicplayer.R;
import com.test.musicplayer.ui.actvities.menus.*;
import com.test.musicplayer.ui.menu.MenuBean;
import com.test.musicplayer.ui.menu.MenuItemTag;
import com.test.musicplayer.ui.views.MsgTipView;
import com.test.musicplayer.utils.LogUtil;

import java.util.List;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder> {

    private final String TAG = "MenuRecyclerAdapter.java";

    private Context context;
    private List<MenuBean> datas;
    private LayoutInflater inflater;

    public MenuRecyclerAdapter(Context context, List<MenuBean> datas) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.layout_main_left_menu_item,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int pos) {
        final MenuBean menuBean = datas.get(pos);
        int menuTag = menuBean.getMenuItemTag();
        if (menuBean.isDivide()) {
            holder.devideView.setVisibility(View.VISIBLE);
            holder.layoutRoot.setVisibility(View.GONE);
        } else {
            holder.devideView.setVisibility(View.GONE);
            holder.layoutRoot.setVisibility(View.VISIBLE);
            holder.imageLeft.setImageResource(menuBean.getLeftImageId());
            holder.textTitle.setText(menuBean.getMenuTitle());
            int msgCount = menuBean.getMsgCount();
            if (msgCount <= 0) {
                holder.tipViewLeft.setVisibility(View.GONE);
            } else {
                // TODO  holder.tipViewLeft.setMsgCount(msgCount);
            }

            if ((menuTag == MenuItemTag.MENU_TAG_DESKTOP_LYRIC)
                    || (menuTag == MenuItemTag.MENU_TAG_LOCK_SCREEN_LYRIC)) {
                LogUtil.i(TAG, "onBindViewHolder() -- pos = " + pos + ", menuTag = " + menuTag);
                holder.layoutRoot.setClickable(false);
                holder.switchView.setVisibility(View.VISIBLE);
                holder.layoutRight.setVisibility(View.GONE);

                holder.switchView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickSwitch(pos);
                    }
                });

            } else {
                holder.layoutRoot.setClickable(true);
                holder.switchView.setVisibility(View.GONE);
                holder.layoutRight.setVisibility(View.VISIBLE);


                String menuSepplement = menuBean.getMenuSepplement();
                if (TextUtils.isEmpty(menuSepplement)) {
                    holder.textSepplement.setVisibility(View.GONE);
                } else {
                    holder.textSepplement.setText(menuSepplement);
                }
                int rightImageId = menuBean.getRightImageId();
                if (rightImageId > 0) {
                    holder.imageRight.setImageResource(rightImageId);
                } else {
                    holder.imageRight.setVisibility(View.GONE);
                }
                int sepplementMsgCount = menuBean.getSepplementMsgCount();
                if (sepplementMsgCount <= 0) {
                    holder.tipViewRight.setVisibility(View.GONE);
                } else {
                    // TODO   holder.tipViewRight.setMsgCount(sepplementMsgCount);
                }


                holder.layoutRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickRootLayout(pos);
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private void clickRootLayout(int pos) {
        LogUtil.i(TAG, "clickRootLayout() -- pos = " + pos);
        Intent intent = new Intent();
        switch (datas.get(pos).getMenuItemTag()) {
            case MenuItemTag.MENU_TAG_NEWS_CENTRE:
                intent.setClass(context, NewsCentreActivity.class);
                break;

            case MenuItemTag.MENU_TAG_SKIN_CENTRE:
                intent.setClass(context, SkinCentreActivity.class);
                break;

            case MenuItemTag.MENU_TAG_VIP_CENTRE:
                intent.setClass(context, VipCentreActivity.class);
                break;

            case MenuItemTag.MENU_TAG_FLOW_MONTHLY:
                intent.setClass(context, FlowMonthlyActivity.class);
                break;

            case MenuItemTag.MENU_TAG_PRIVATE_CLOUD:
                intent.setClass(context, PrivateCloudActivity.class);
                break;

            case MenuItemTag.MENU_TAG_TIME_CLOSURE:
                intent.setClass(context, TimeClosureActivity.class);
                break;

            case MenuItemTag.MENU_TAG_VIPER_SOUND_EFFECT:
                intent.setClass(context, ViperSoundEffectActivity.class);
                break;

            case MenuItemTag.MENU_TAG_LISTEN_KNOW_MUSIC:
                intent.setClass(context, ListenKnowMusicActivity.class);
                break;

            case MenuItemTag.MENU_TAG_MUSIC_TOOLS:
                intent.setClass(context, MusicToolsActivity.class);
                break;

            case MenuItemTag.MENU_TAG_DRIVING_MODE:
                intent.setClass(context, DrivingModeActivity.class);
                break;

            case MenuItemTag.MENU_TAG_SOUNDS:
                intent.setClass(context, SoundsActivity.class);
                break;

            case MenuItemTag.MENU_TAG_CHILDREN_AREA:
                intent.setClass(context, ChildrenAreaActivity.class);
                break;

            case MenuItemTag.MENU_TAG_MUSIC_MARKET:
                intent.setClass(context, MusicMarketActivity.class);
                break;
        }

        context.startActivity(intent);
    }

    private void clickSwitch(int pos) {
        LogUtil.i(TAG, "clickSwitch() -- pos = " + pos);
        switch (datas.get(pos).getMenuItemTag()) {
            case MenuItemTag.MENU_TAG_DESKTOP_LYRIC:
                LogUtil.i(TAG, "clickSwitch() -- MenuItemTag.MENU_TAG_DESKTOP_LYRIC = "
                        + MenuItemTag.MENU_TAG_DESKTOP_LYRIC);
                break;

            case MenuItemTag.MENU_TAG_LOCK_SCREEN_LYRIC:
                LogUtil.i(TAG, "clickSwitch() -- MenuItemTag.MENU_TAG_LOCK_SCREEN_LYRIC = "
                        + MenuItemTag.MENU_TAG_LOCK_SCREEN_LYRIC);
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private View devideView;
        private RelativeLayout layoutRoot;
        private ImageView imageLeft;
        private TextView textTitle;
        private MsgTipView tipViewLeft;
        private LinearLayout layoutRight;
        private TextView textSepplement;
        private ImageView imageRight;
        private MsgTipView tipViewRight;
        private Switch switchView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            devideView = itemView.findViewById(R.id.leftmenu_item_view_devide);
            layoutRoot = itemView.findViewById(R.id.leftmenu_item_layout_root);
            imageLeft = itemView.findViewById(R.id.leftmenu_item_iamge_left);
            textTitle = itemView.findViewById(R.id.leftmenu_item_text_title);
            tipViewLeft = itemView.findViewById(R.id.leftmenu_item_tipView_left);
            layoutRight = itemView.findViewById(R.id.leftmenu_item_layout_right);
            textSepplement = itemView.findViewById(R.id.leftmenu_item_text_Sepplement);
            imageRight = itemView.findViewById(R.id.leftmenu_item_iamge_right);
            tipViewRight = itemView.findViewById(R.id.leftmenu_item_tipView_right);
            switchView = itemView.findViewById(R.id.leftmenu_item_switch);
        }
    }
}
