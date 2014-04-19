package com.xaozu.circle.app.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xaozu.circle.app.R;
import com.xaozu.circle.app.date.Category;

/**
 * Created by xaozu on 2014/4/19.
 * 这是一个现实用户信息的页面
 */
public class UserFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_user, null);
        return contentView;
    }

    public static UserFragment newInstance(Category category) {
        UserFragment fragment = new UserFragment();
        Bundle bundle = new Bundle();
        bundle.putString("EXTRA_CATEGORY", category.name());
        fragment.setArguments(bundle);
        return fragment;
    }


}
