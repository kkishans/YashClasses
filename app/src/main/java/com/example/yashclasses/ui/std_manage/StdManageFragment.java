package com.example.yashclasses.ui.std_manage;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.yashclasses.HomeActivity;
import com.example.yashclasses.MainActivity;
import com.example.yashclasses.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StdManageFragment extends Fragment implements View.OnClickListener {

    private StdManageViewModel stdManageViewModel;
    Button btnaddStd;
    View root;
    Button btnsave,btncancel;
    EditText etStd,etFees;
    Spinner spinnermedium;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        stdManageViewModel =   ViewModelProviders.of(this).get(StdManageViewModel.class);

        root = inflater.inflate(R.layout.fragment_std_manage, container, false);

        final TextView textView = root.findViewById(R.id.txtStdManage);
        btnaddStd = root.findViewById(R.id.btnAddStd);

        btnaddStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStd();
            }
        });
        stdManageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
//        FloatingActionButton fab = root.findViewById(R.id.fab);
//        fab.hide();


        return root;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etStd = (EditText) view.findViewById(R.id.txtstd);
        etFees = (EditText) view.findViewById(R.id.txtfees);
        spinnermedium = (Spinner) view.findViewById(R.id.spinnerMedium);
    }

    public void addStd(){
        final HomeActivity homeActivity = new HomeActivity();
//        btnsave = root.findViewById(R.id.btnSaveStd);
//        btncancel =root.findViewById(R.id.btnCancle);
        etFees = root.findViewById(R.id.txtfees);
        etStd =  root.findViewById(R.id.txtstd);
        spinnermedium = root.findViewById(R.id.spinnerMedium);
        AlertDialog.Builder alert = new AlertDialog.Builder(root.getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_add_std_layout,null);
        alert.setView(view);
       // alert.setCancelable(false);

        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(root.getContext()," Std Addded.",Toast.LENGTH_LONG).show();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(root.getContext()," Std Not Addded.",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();

    }

    @Override
    public void onClick(View view) {
    }
}