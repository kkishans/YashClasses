package com.example.yashclasses.ui.addstudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.yashclasses.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddStudentFragment extends Fragment {

    private AddStudentViewModel addStudentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addStudentViewModel =
                ViewModelProviders.of(this).get(AddStudentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_student, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        addStudentViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
//        FloatingActionButton fab = root.findViewById(R.id.fab);
//        fab.hide();
        return root;
    }
}