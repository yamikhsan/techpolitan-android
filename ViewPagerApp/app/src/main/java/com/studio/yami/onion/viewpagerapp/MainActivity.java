package com.studio.yami.onion.viewpagerapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.studio.yami.onion.viewpagerapp.adapter.MainPagerAdapter;
import com.studio.yami.onion.viewpagerapp.adapter.MenuPagerAdapter;
import com.studio.yami.onion.viewpagerapp.entity.MenuEntity;
import com.studio.yami.onion.viewpagerapp.fragment.MainFragment;
import com.studio.yami.onion.viewpagerapp.fragment.MenuFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ArrayList<MenuEntity> foodList, drinkList, fruitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager_main);
        createFragmentMenu();
        //createFragmentMain();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    private void dummyList(){

        foodList = new ArrayList<>();
        foodList.add(new MenuEntity("Ayam Bakar", "13.000"));
        foodList.add(new MenuEntity("Nasi Goreng", "18.000"));
        foodList.add(new MenuEntity("Sate Ayam", "16.000"));

        drinkList = new ArrayList<>();
        drinkList.add(new MenuEntity("Teh Hijau", "10.000"));
        drinkList.add(new MenuEntity("Kopi Tubruk", "7.000"));
        drinkList.add(new MenuEntity("Susu Segar", "100.000"));

        fruitList = new ArrayList<>();
        fruitList.add(new MenuEntity("Aple", "2.000"));
        fruitList.add(new MenuEntity("Banana", "1.500"));
        fruitList.add(new MenuEntity("Manggo", "3.000"));

    }

    private void createFragmentMenu(){

        dummyList();

        MenuPagerAdapter adapter = new MenuPagerAdapter(getSupportFragmentManager());

        MenuFragment foodFragment = MenuFragment.newInstance(foodList);
        MenuFragment drinkFragment = MenuFragment.newInstance(drinkList);
        MenuFragment fruitFragment = MenuFragment.newInstance(fruitList);

        adapter.addCategories("Food", foodFragment);
        adapter.addCategories("Drink", drinkFragment);
        adapter.addCategories("Fruit", fruitFragment);

        viewPager.setAdapter(adapter);

    }

    private void createFragmentMain(){

        String strApple = getResources().getString(R.string.apple);
        String strBanana = getResources().getString(R.string.banana);
        String strCherry = getResources().getString(R.string.cherry);

        MainFragment apple = MainFragment.newInstance(strApple, Color.RED);
        MainFragment banana = MainFragment.newInstance(strBanana, Color.YELLOW);
        MainFragment cherry = MainFragment.newInstance(strCherry, Color.BLUE);

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(strApple, apple);
        adapter.addFragment(strBanana, banana);
        adapter.addFragment(strCherry, cherry);

        viewPager.setAdapter(adapter);
    }

}
