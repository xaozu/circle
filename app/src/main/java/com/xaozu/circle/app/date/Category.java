
package com.xaozu.circle.app.date;

public enum Category {
    popular("我"), everyone("圈子"), debuts("排行");
    private String mDisplayName;

    Category(String displayName) {
        mDisplayName = displayName;
    }

    public String getDisplayName() {
        return mDisplayName;
    }
}
