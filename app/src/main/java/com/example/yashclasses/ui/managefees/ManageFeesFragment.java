package com.example.yashclasses.ui.managefees;

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

public class ManageFeesFragment extends Fragment {

    private ManageFeesViewModel manageFeesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        manageFeesViewModel =
                ViewModelProviders.of(this).get(ManageFeesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_manage_fees, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        manageFeesViewModel.getText().observe(this, new Observer<String>() {
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