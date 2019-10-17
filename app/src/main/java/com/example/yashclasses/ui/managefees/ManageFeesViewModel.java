package com.example.yashclasses.ui.managefees;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ManageFeesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ManageFeesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ManageFeesFragment fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}