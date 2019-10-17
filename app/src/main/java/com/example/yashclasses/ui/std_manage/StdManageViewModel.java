package com.example.yashclasses.ui.std_manage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StdManageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StdManageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Std List");
    }

    public LiveData<String> getText() {
        return mText;
    }
}