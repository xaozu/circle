package com.xaozu.circle.app.activity;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.xaozu.circle.app.R;
import com.xaozu.circle.app.activity.fragment.BaseFragment;
import com.xaozu.circle.app.activity.fragment.DrawerFragment;
import com.xaozu.circle.app.activity.fragment.UserFragment;
import com.xaozu.circle.app.date.Category;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * 主页
 */
public class MainActivity extends BaseActivity {
    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;
    private UserFragment mContentFragment;
    private Category mCategory;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerLayout.setScrimColor(Color.argb(100, 0, 0, 0));

        initActionBar();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        setCategory(Category.popular);
        replaceFragment(R.id.left_drawer, new DrawerFragment());
    }

    protected void replaceFragment(int viewId, BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(viewId, fragment).commit();
    }
    private void initActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setCategory(Category category) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        if (mCategory == category) {
            return;
        }
        mCategory = category;
        setTitle(mCategory.getDisplayName());
        mContentFragment = UserFragment.newInstance(category);
        replaceFragment(R.id.content_frame, mContentFragment);
    }

}
