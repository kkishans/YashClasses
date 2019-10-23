package com.example.yashclasses.ui.addstudent;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddStudentFragment extends Fragment  {

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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStudent();
            }
        });

        //Spinner

        ArrayAdapter adapter = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item, medium);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return root;
    }

    public void AddStudent(){

        boolean flag;
        String etName = edtName.getText().toString();
        String etStd = edtStd.getText().toString();
        String etContact = edtContact.getText().toString();
        String spMedium = spinner.getSelectedItem().toString();
        boolean isInserted =  myDb.insertData(etName,etStd,spMedium,etContact,null);

        if(etName.equals("")  ||etStd.equals("")  || spMedium.equals("") ||etContact.equals("")){
            new AlertDialog.Builder(getActivity()).setTitle("Invalid Data Insertion.")
                    .setMessage("There are all field required. \nmay you have insert invalid data. \nPlease try again. ")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();
        }
        else{
            if (isInserted == true)
                Toast.makeText(getContext(), "Data inserted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(), "Error occured time  of insertion data", Toast.LENGTH_SHORT).show();
        }
    }

}