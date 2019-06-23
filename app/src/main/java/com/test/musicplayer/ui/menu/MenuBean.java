package com.test.musicplayer.ui.menu;

public class MenuBean {

    private boolean isDivide;  // 是否是分割线
    private int leftImageId;
    private String menuTitle;
    private int msgCount;
    private String menuSepplement;
    private int rightImageId;
    private int sepplementMsgCount;
    private boolean isShowSwitch;
    private int menuItemTag;

    public MenuBean(boolean isDivide, int leftImageId, String menuTitle, int msgCount,
                    String menuSepplement, int rightImageId, int sepplementMsgCount,
                    boolean isShowSwitch, int menuItemTag) {
        this.isDivide = isDivide;
        this.leftImageId = leftImageId;
        this.menuTitle = menuTitle;
        this.msgCount = msgCount;
        this.menuSepplement = menuSepplement;
        this.rightImageId = rightImageId;
        this.sepplementMsgCount = sepplementMsgCount;
        this.isShowSwitch = isShowSwitch;
        this.menuItemTag = menuItemTag;

    }

    public boolean isDivide() {
        return isDivide;
    }

    public void setDivide(boolean divide) {
        isDivide = divide;
    }

    public int getLeftImageId() {
        return leftImageId;
    }

    public void setLeftImageId(int leftImageId) {
        this.leftImageId = leftImageId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public String getMenuSepplement() {
        return menuSepplement;
    }

    public void setMenuSepplement(String menuSepplement) {
        this.menuSepplement = menuSepplement;
    }

    public int getRightImageId() {
        return rightImageId;
    }

    public void setRightImageId(int rightImageId) {
        this.rightImageId = rightImageId;
    }

    public int getSepplementMsgCount() {
        return sepplementMsgCount;
    }

    public void setSepplementMsgCount(int sepplementMsgCount) {
        this.sepplementMsgCount = sepplementMsgCount;
    }

    public boolean isShowSwitch() {
        return isShowSwitch;
    }

    public void setShowSwitch(boolean showSwitch) {
        isShowSwitch = showSwitch;
    }

    public int getMenuItemTag() {
        return menuItemTag;
    }

    public void setMenuItemTag(int menuItemTag) {
        this.menuItemTag = menuItemTag;
    }
}
