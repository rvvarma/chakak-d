package com.inducesmile.androidmusicplayer.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.inducesmile.androidmusicplayer.fragment.PlaylistFragment;
import com.inducesmile.androidmusicplayer.fragment.SongFragment;

public class CustomFragmentPageAdapter extends FragmentPagerAdapter{

    private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 4;

    public CustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PlaylistFragment();
            case 1:
                return new SongFragment();
            case 2:
                return new SongFragment();
            case 3:
                return new PlaylistFragment();
            /*case 1:
                return new PlaylistFragment();
            case 2:
                return new AlbumFragment();
            case 3:
                return new ArtistFragment();*/
        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "New Orders";
            case 1:
                return "Pending";
            case 2:
                return "Delivered";
            case 3:
                return "Artists";
        }
        return null;
    }
}
