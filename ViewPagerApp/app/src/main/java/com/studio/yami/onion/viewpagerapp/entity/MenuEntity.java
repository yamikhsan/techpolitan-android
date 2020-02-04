package com.studio.yami.onion.viewpagerapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuEntity implements Parcelable {

    private String name, price;

    public MenuEntity(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.price);
    }

    private MenuEntity(Parcel in) {
        this.name = in.readString();
        this.price = in.readString();
    }

    public static final Parcelable.Creator<MenuEntity> CREATOR = new Parcelable.Creator<MenuEntity>() {
        @Override
        public MenuEntity createFromParcel(Parcel source) {
            return new MenuEntity(source);
        }

        @Override
        public MenuEntity[] newArray(int size) {
            return new MenuEntity[size];
        }
    };
}
