package com.example.ultai.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<String> text;

    public NewsViewModel() {
        text = new MutableLiveData<>();
        text.setValue("Просто блеск");
    }

    public LiveData<String> getText() {
        return text;
    }
}
