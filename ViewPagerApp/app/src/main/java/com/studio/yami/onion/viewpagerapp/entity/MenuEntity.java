package com.studio.yami.onion.viewpagerapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuEntity implements Parcelable {

    private String name, price, desc;
    private int img, color;

    public MenuEntity(String name, String price, String desc, int color, int img) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.color = color;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public int getColor() {
        return color;
    }

    public int getImg() {
        return img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.price);
        dest.writeString(this.desc);
        dest.writeInt(this.img);
        dest.writeInt(this.color);
    }

    protected MenuEntity(Parcel in) {
        this.name = in.readString();
        this.price = in.readString();
        this.desc = in.readString();
        this.img = in.readInt();
        this.color = in.readInt();
    }

    public static final Creator<MenuEntity> CREATOR = new Creator<MenuEntity>() {
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
