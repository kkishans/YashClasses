package com.example.yashclasses.ui.std_manage;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yashclasses.DatabaseHelper;
import com.example.yashclasses.R;
import com.example.yashclasses.ui.home.HomeFragment;

import java.util.ArrayList;

public class StdManageFragment extends Fragment implements View.OnClickListener , StdDialog.StdDialogListener {

    private StdManageViewModel stdManageViewModel;
    Button btnaddStd;
    View root;
    Button btnsave,btncancel;
    EditText etStd,etFees;
    Spinner spinnermedium;
    StdAdapter stdAdapter;
    RecyclerView recyclerView;
    DatabaseHelper mydb;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        stdManageViewModel =   ViewModelProviders.of(this).get(StdManageViewModel.class);

        root = inflater.inflate(R.layout.fragment_std_manage, container, false);
        mydb = new DatabaseHelper(getContext());
        //final TextView textView = root.findViewById(R.id.txtStdManage);
        btnaddStd = root.findViewById(R.id.btnAddStd);
        recyclerView = root.findViewById(R.id.stdRecyclerview);
        btnaddStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStd();
            }
        });
//        stdManageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        populateStdList();
        return root;
    }

    public void populateStdList() {
        recyclerView = root.findViewById(R.id.stdRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        Cursor c = mydb.getAllStd();
        c.moveToFirst();
        int l = c.getCount();
       StdAdapter  stdAdapter = new StdAdapter(getContext(),c,l);
        recyclerView.setAdapter(stdAdapter);

    }

    public void RefreshFragment() {
        Fragment StdFragment = new StdManageFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment,StdFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etStd = (EditText) view.findViewById(R.id.txtstd);
        etFees = (EditText) view.findViewById(R.id.txtfees);
        spinnermedium = (Spinner) view.findViewById(R.id.spinnerMedium);
    }

    public void addStd() {
        StdDialog stdDialog = new StdDialog();
        stdDialog.show(getFragmentManager()," Std Manage");
        RefreshFragment();
    }
    @Override
    public void onClick(View view) {
        populateStdList();
    }

    @Override
    public void applyTexts(String StdName, double fees, String medium) {
        Toast.makeText(getContext(),"Std : "+StdName+"-"+medium+"\nfees : "+fees+"\nData inserted",Toast.LENGTH_LONG).show();
        RefreshFragment();
    }
}