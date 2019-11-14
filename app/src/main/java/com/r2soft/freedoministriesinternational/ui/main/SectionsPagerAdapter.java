package com.r2soft.freedoministriesinternational.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.r2soft.freedoministriesinternational.R;
import com.r2soft.freedoministriesinternational.ui.main.packages.DonationView;
import com.r2soft.freedoministriesinternational.ui.main.packages.HomeView;
import com.r2soft.freedoministriesinternational.ui.main.packages.MinistriesView;
import com.r2soft.freedoministriesinternational.ui.main.packages.SermonView;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{
                                                      R.string.tab_text_1,
                                                      R.string.tab_text_2,
                                                      R.string.tab_text_3 ,
                                                      R.string.tab_text_4
                                                      };

    private static Fragment[] views = new Fragment[4];

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        initFragments();
    }

    private void initFragments(){
        views[0] = new HomeView();
        views[1] = new MinistriesView();
        views[2] = new SermonView();
        views[3] = new DonationView();
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
//        return PlaceholderFragment.newInstance(position + 1);
        return  views[position];
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }

}