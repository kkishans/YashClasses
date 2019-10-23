package com.example.yashclasses.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yashclasses.DatabaseHelper;
import com.example.yashclasses.R;
import com.example.yashclasses.ui.std_manage.StdAdapter;

public class HomeFragment extends Fragment {
    Button btnSearch;
    private HomeViewModel homeViewModel;
    DatabaseHelper mydb;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                ViewGroup container, Bundle savedInstanceState) {
            homeViewModel =
                    ViewModelProviders.of(this).get(HomeViewModel.class);
            View root = inflater.inflate(R.layout.fragment_home, container, false);


            mydb = new DatabaseHelper(getContext());
            recyclerView = root.findViewById(R.id.StudentRecyclerView);

            populateStudentList();

            //Search
            btnSearch = root.findViewById(R.id.btnSearch);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getMessage();
                }
            });
        return root;
    }

    private void populateStudentList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Cursor c = mydb.getAllStd();
        c.moveToFirst();
        int l = c.getCount();
        StdAdapter  studentAdapter = new StdAdapter(getContext(),c,l);
        recyclerView.setAdapter(studentAdapter);
    }

    public void getMessage(){
        new AlertDialog.Builder(getActivity()).setTitle("Work Under Process.")
                .setMessage("Which funtion do you want to preform that is under process right now. \nPlease try after done. ")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }
}