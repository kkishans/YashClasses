package com.example.yashclasses.ui.addstudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.yashclasses.DatabaseHelper;
import com.example.yashclasses.R;

public class AddStudentFragment extends Fragment implements View.OnClickListener {

    private AddStudentViewModel addStudentViewModel;

    DatabaseHelper myDb;

    EditText edtName,edtStd,edtContact;
    Button btnAdd;
    Spinner spinner;
    String[] medium = {"Gujarati","English"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addStudentViewModel =
                ViewModelProviders.of(this).get(AddStudentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_student, container, false);

        myDb = new DatabaseHelper(getContext());

        edtName = root.findViewById(R.id.edtName);
        edtStd = root.findViewById(R.id.edtStd);
        edtContact = root.findViewById(R.id.edtContact);
        spinner = root.findViewById(R.id.spinnerMedium);
        btnAdd =root.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);

        //Spinner

        ArrayAdapter adapter = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item, medium);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        return root;
    }

    @Override
    public void onClick(View view) {
        AddStudent();
    }


    public void AddStudent(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted =  myDb.insertData(edtName.toString(),edtStd.toString(),spinner.toString(),edtContact.toString(),null);
                if (isInserted == true)
                    Toast.makeText(getContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Error occured time  of insertion data", Toast.LENGTH_SHORT).show();
            }
        });
    }



}