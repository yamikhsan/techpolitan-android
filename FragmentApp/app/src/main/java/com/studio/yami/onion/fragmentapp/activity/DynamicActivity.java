package com.studio.yami.onion.fragmentapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.studio.yami.onion.fragmentapp.fragment.HomeFragment;
import com.studio.yami.onion.fragmentapp.fragment.MainFragment;
import com.studio.yami.onion.fragmentapp.R;

public class DynamicActivity extends AppCompatActivity {

//    private int idFr;
    private Fragment home = new HomeFragment();
    private Fragment main = new MainFragment();

    FragmentManager fm;

    int fragContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);

        fm = getSupportFragmentManager();
        fragContainer = R.id.frame_dynamic;
        Button btnReplace = findViewById(R.id.btn_replace);

//        fm.beginTransaction().add(fragContainer, home).commit();
//        idFr = 1;

//        btnReplace.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                switch (idFr){
//                    case 1:
//                        fm.beginTransaction().replace(fragContainer, main).addToBackStack("").commit();
//                        idFr = 2;
//                        break;
//
//                    case 2:
//                        fm.beginTransaction().replace(fragContainer, home).addToBackStack("").commit();
//                        idFr = 1;
//                        break;
//
//                }
//            }
//        });

        if(savedInstanceState == null){

            FragmentTransaction ft = fm.beginTransaction();
            ft.add(fragContainer, home);
            ft.add(fragContainer, main);
            ft.hide(home);
            ft.commit();

        }

        btnReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = fm.beginTransaction();
                if(home.isHidden()){
                    transaction.hide(main);
                    transaction.show(home);
                } else if(main.isHidden()){
                    transaction.hide(home);
                    transaction.show(main);
                }

                transaction.commit();

            }
        });

    }
}
