package com.example.ultai.ultai;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UltaiViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public UltaiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ultai fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}