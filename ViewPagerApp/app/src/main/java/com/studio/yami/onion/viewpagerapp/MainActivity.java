package com.studio.yami.onion.viewpagerapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.studio.yami.onion.viewpagerapp.adapter.MainPagerAdapter;
import com.studio.yami.onion.viewpagerapp.adapter.MenuPagerAdapter;
import com.studio.yami.onion.viewpagerapp.entity.MenuEntity;
import com.studio.yami.onion.viewpagerapp.fragment.MainFragment;
import com.studio.yami.onion.viewpagerapp.fragment.MenuFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
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

        foodList = dummyGenerate("Paket Nasi Ayam ", 16000, 2000, R.drawable.food);
//        foodList.add(new MenuEntity("Ayam Bakar", "13.000"));
//        foodList.add(new MenuEntity("Nasi Goreng", "18.000"));
//        foodList.add(new MenuEntity("Sate Ayam", "16.000"));

        drinkList = dummyGenerate("Paket Susu Segar ", 20000, 5000, R.drawable.drink);
//        drinkList.add(new MenuEntity("Teh Hijau", "10.000"));
//        drinkList.add(new MenuEntity("Kopi Tubruk", "7.000"));
//        drinkList.add(new MenuEntity("Susu Segar", "100.000"));

        fruitList = dummyGenerate("Paket Buah ", 10000, 3000, R.drawable.fruit);
//        fruitList.add(new MenuEntity("Aple", "2.000"));
//        fruitList.add(new MenuEntity("Banana", "1.500"));
//        fruitList.add(new MenuEntity("Manggo", "3.000"));

    }

    private ArrayList<MenuEntity> dummyGenerate(String name, int value, int increment, int img){

        ArrayList<MenuEntity> m = new ArrayList<>();
        DecimalFormat format = new DecimalFormat("#,###");
        int limit = 20;
        String desc = getResources().getString(R.string.desc);

        for(int i=0; i<limit; i++){
            int price = value + (increment * i);
            int color = (i % 2) == 0 ? R.color.colorBlue : R.color.colorBlue25;
            String _name = name + (i + 1);
            m.add(
                    new MenuEntity(
                            _name,
                            format.format(price),
                            _name + "\n" + desc,
                            ContextCompat.getColor(this, color),
                            img
                    )
            );
        }
        return m;
    }

    private void createFragmentMenu(){

//        dummyList();

        getJson();

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

    private void getJson(){

        String foodJson = loadJSON(R.raw.food);
        String drinkJson = loadJSON(R.raw.drink);
        String fruitJson = loadJSON(R.raw.fruit);

        foodList = setJson(foodJson, R.drawable.food);
        drinkList = setJson(drinkJson, R.drawable.drink);
        fruitList = setJson(fruitJson, R.drawable.fruit);

    }

    private ArrayList<MenuEntity> setJson(String json, int img){
        ArrayList<MenuEntity> m = new ArrayList<>();
        try{
            JSONArray arr = new JSONArray(json);
            for(int i=0; i<arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                String name = obj.getString("name");
                String price = obj.getString("price");
                String desc = obj.getString("desc");
                MenuEntity menu = new MenuEntity(
                        name,
                        price,
                        desc,
                        ContextCompat.getColor(this, R.color.colorBlue),
                        img
                );
                m.add(menu);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
        return m;
    }

    public String loadJSON(int id) {
        String json;
        try {
            InputStream is = getResources().openRawResource(id);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
