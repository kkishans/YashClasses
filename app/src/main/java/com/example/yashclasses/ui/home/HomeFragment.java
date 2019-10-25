package com.example.yashclasses.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yashclasses.DatabaseHelper;
import com.example.yashclasses.R;
import com.example.yashclasses.ui.addstudent.AddStudentFragment;

public class HomeFragment extends Fragment {
    Button btnSearch,btnAddStudent;
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
            btnAddStudent = root.findViewById(R.id.btnHomeAddStduent);
            btnAddStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenAddStudentFragment();
                }
            });
            populateStudentList();

            //Search
            btnSearch = root.findViewById(R.id.btnCall);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getMessage();
                }
            });
        return root;
    }

    private void OpenAddStudentFragment() {
        Fragment addStudent = new AddStudentFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment,addStudent);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void populateStudentList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Cursor c = mydb.getAllStudent();
        c.moveToFirst();
        int l = c.getCount();
        StudentAdapter  studentAdapter = new StudentAdapter(getContext(),c,l);
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