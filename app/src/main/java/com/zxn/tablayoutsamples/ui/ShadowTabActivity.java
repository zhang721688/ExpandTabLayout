package com.zxn.tablayoutsamples.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.zxn.tablayout.CommonTabLayout;
import com.zxn.tablayout.listener.CustomTabEntity;
import com.zxn.tablayoutsamples.R;
import com.zxn.tablayoutsamples.entity.TabEntity;
import com.zxn.tablayoutsamples.utils.ViewFindUtils;

import java.util.ArrayList;
import java.util.Random;

public class ShadowTabActivity extends AppCompatActivity {
    Random mRandom = new Random();
    private final Context mContext = this;
    private final ArrayList<Fragment> mFragments = new ArrayList<>();
    private final ArrayList<Fragment> mFragments2 = new ArrayList<>();
    private final String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private final String[] mTitles10 = {"外卖", "自取", "预订"};
    private final String[] mContents = {"10", "5.5", "100", "20"};
    private final int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private final int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private final ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private final ArrayList<CustomTabEntity> mTabEntities10 = new ArrayList<>();
    private View mDecorView;
    private ViewPager mViewPager;
    private CommonTabLayout mTabLayout_9;
    private CommonTabLayout mTabLayout_10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow_tab);

        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
            mFragments2.add(SimpleCardFragment.getInstance("Switch Fragment " + title));
        }


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mContents[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        for (int i = 0; i < mTitles10.length; i++) {
            mTabEntities10.add(new TabEntity(mTitles10[i]));
        }

        mDecorView = getWindow().getDecorView();
        mViewPager = ViewFindUtils.find(mDecorView, R.id.vp_2);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        /** indicator圆角色块2 */
        mTabLayout_9 = ViewFindUtils.find(mDecorView, R.id.tl_9);
        mTabLayout_10 = ViewFindUtils.find(mDecorView, R.id.tl_10);
        mTabLayout_9.setTabData(mTabEntities);
        mTabLayout_10.setTabData(mTabEntities10);
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
