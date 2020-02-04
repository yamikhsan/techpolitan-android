package com.studio.yami.onion.viewpagerapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studio.yami.onion.viewpagerapp.R;
import com.studio.yami.onion.viewpagerapp.entity.MenuEntity;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private List<MenuEntity> menus;

    public MenuAdapter(List<MenuEntity> menus) {
        this.menus = menus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.onBind(menus.get(position));
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.item_name);
            price = view.findViewById(R.id.item_price);
        }

        private void onBind(MenuEntity menu){
            name.setText(menu.getName());
            price.setText(menu.getPrice());
        }

    }
}
