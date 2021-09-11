package com.example.databinding;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    MutableLiveData<Integer> one;
    MutableLiveData<Integer> two;
    int aBack, bBack;

    public MutableLiveData<Integer> getOne() {
        if (one == null) {
            one = new MutableLiveData<>();
            one.setValue(0);
        }
        return one;
    }

    public MutableLiveData<Integer> getTwo() {
        if (two == null) {
            two = new MutableLiveData<>();
            two.setValue(0);
        }
        return two;
    }

    public void changeCount (String v, int i) {
        if (v.equals("a")) {
            aBack = one.getValue();
            one.setValue(aBack + i);
        } else if (v.equals("b")) {
            bBack = two.getValue();
            two.setValue(bBack + i);
        }
    }

    public void reset () {
        one.setValue(0);
        two.setValue(0);
    }

    public void undo () {
        one.setValue(aBack);
        two.setValue(bBack);
    }
}
