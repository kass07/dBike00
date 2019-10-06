package com.bycode.dbike.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RotasViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public RotasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is rotas compartilhadas fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
