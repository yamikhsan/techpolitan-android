package com.studio.yami.onion.viewpagerapp.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.studio.yami.onion.viewpagerapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private static String NAME = "EXTRA NAME";
    private static String COLOR = "EXTRA COLOR";


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FrameLayout layout = view.findViewById(R.id.layout_fragment);
        TextView text = view.findViewById(R.id.tv_fragment);

        if(getArguments() != null){
            String name = getArguments().getString(NAME);
            int color = getArguments().getInt(COLOR);

            layout.setBackgroundColor(color);
            text.setText(name);
        }

    }

    public static MainFragment newInstance(String name, int color){

        Bundle bundle = new Bundle();
        bundle.putString(NAME, name);
        bundle.putInt(COLOR, color);

        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}
