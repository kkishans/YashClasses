package com.example.yashclasses.ui.allstudents;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllStudentsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AllStudentsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is All Student fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}