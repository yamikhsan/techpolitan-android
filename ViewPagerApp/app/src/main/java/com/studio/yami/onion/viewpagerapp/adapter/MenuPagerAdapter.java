package com.studio.yami.onion.viewpagerapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MenuPagerAdapter extends FragmentPagerAdapter {

    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    public MenuPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    public void addCategories(String title, Fragment fragment){
        titles.add(title);
        fragments.add(fragment);
    }
}
