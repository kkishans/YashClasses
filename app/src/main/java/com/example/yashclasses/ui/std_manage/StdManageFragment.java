package com.example.yashclasses.ui.std_manage;

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

public class StdManageFragment extends Fragment {

    private StdManageViewModel stdManageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        stdManageViewModel =
                ViewModelProviders.of(this).get(StdManageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_all_students, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        stdManageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}