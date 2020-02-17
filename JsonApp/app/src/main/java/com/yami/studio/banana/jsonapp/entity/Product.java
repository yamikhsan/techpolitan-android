package com.yami.studio.banana.jsonapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private int id, qty;
    private String name, slug, image;
    private Merchant merchant;
    private Category category;

    public Product(int id, int qty, String name, String slug, String image, Merchant merchant, Category category) {
        this.id = id;
        this.qty = qty;
        this.name = name;
        this.slug = slug;
        this.image = image;
        this.merchant = merchant;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public int getQty() {
        return qty;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getImage() {
        return image;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public Category getCategory() {
        return category;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.qty);
        dest.writeString(this.name);
        dest.writeString(this.slug);
        dest.writeString(this.image);
        dest.writeParcelable(this.merchant, flags);
        dest.writeParcelable(this.category, flags);
    }

    protected Product(Parcel in) {
        this.id = in.readInt();
        this.qty = in.readInt();
        this.name = in.readString();
        this.slug = in.readString();
        this.image = in.readString();
        this.merchant = in.readParcelable(Merchant.class.getClassLoader());
        this.category = in.readParcelable(Category.class.getClassLoader());
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
