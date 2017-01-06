package com.example.cille_000.laesomondo.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class NavDrawerItem {
    private boolean showNotify;
    private Drawable icon;
    private String title;

    public NavDrawerItem() {
    }

    public NavDrawerItem(boolean showNotify, Drawable icon, String title) {
        this.showNotify = showNotify;
        this.icon = icon;
        this.title = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
