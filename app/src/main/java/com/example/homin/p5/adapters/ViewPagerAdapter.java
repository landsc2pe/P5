package com.example.homin.p5.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.homin.p5.fragments.InduceFragment;
import com.example.homin.p5.fragments.Tab1Fragment;
import com.example.homin.p5.fragments.Tab2Fragment;
import com.example.homin.p5.utils.LogTag;

/**
 * Created by HOMIN on 2016-08-21.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public static final String TAG = ViewPagerAdapter.class.getSimpleName();

    private int numOfTabs = 2;
    private boolean treeFolderState;
    private String tabTitles[] = new String[] { "Tab1", "Tab2" };

    Fragment[] cacheFragment;

    public ViewPagerAdapter(FragmentManager fm, boolean treeFolderState) {
        super(fm);
        this.treeFolderState = treeFolderState;
        cacheFragment = new Fragment[numOfTabs];
        if(LogTag.DEBUG) Log.d(TAG, ""+treeFolderState);

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (cacheFragment[position] == null) {
            if (position == 0) {
                fragment = new Tab1Fragment();
            } else if (position == 1){
                if (treeFolderState){
                    fragment = new Tab2Fragment();
                }else {
                    fragment = new InduceFragment();
                }
            }

        } else {
            cacheFragment[position] = fragment;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
