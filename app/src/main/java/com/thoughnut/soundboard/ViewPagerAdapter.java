package com.thoughnut.soundboard;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    private final List<Fragment> fragmentList = new ArrayList<>();

public void addFrag(Fragment fragment){
    fragmentList.add(fragment);
}


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    @Nullable
    public CharSequence getPageTitle(int position){

        if (position == 0){
            return "Arrie";
        }
        if (position == 1){
            return "Jappie";
        }
        if (position == 2){
            return "Rest";
        }
        if (position == 3){
            return "JC";
        }
        if (position ==4){
            return  "Zelf";
        }


        return null;
    }

}
