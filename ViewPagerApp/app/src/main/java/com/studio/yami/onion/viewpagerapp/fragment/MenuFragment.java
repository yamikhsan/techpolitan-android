package com.studio.yami.onion.viewpagerapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.studio.yami.onion.viewpagerapp.R;
import com.studio.yami.onion.viewpagerapp.adapter.MenuAdapter;
import com.studio.yami.onion.viewpagerapp.entity.MenuEntity;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private static final String MENU = "EXTRA MENU";

    public static MenuFragment newInstance(ArrayList<MenuEntity> menus) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(MENU, menus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null){
            List<MenuEntity> menus = getArguments().getParcelableArrayList(MENU);

            if (view instanceof RecyclerView) {

                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(new MenuAdapter(menus));
            }
        }

    }
}
