package com.studio.yami.onion.fragmentapp.viewmodel;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private int count = 0;

    public MainViewModel(){

    }

    String getCount() {
        return String.valueOf(count);
    }

    void setCount() {
        this.count++;
    }
}
