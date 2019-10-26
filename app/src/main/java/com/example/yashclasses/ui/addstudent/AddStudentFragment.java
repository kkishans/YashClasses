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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.yashclasses.DatabaseHelper;
import com.example.yashclasses.R;
import com.example.yashclasses.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddStudentFragment extends Fragment  {

    private AddStudentViewModel addStudentViewModel;

    DatabaseHelper myDb;

    EditText edtName,edtContact,etLoaction,etFees;
    Button btnAdd;
    Spinner edtStd;
    Spinner spinner;
    String[] std ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addStudentViewModel =
                ViewModelProviders.of(this).get(AddStudentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_student, container, false);

        myDb = new DatabaseHelper(getContext());
        std = myDb.getAllStdNames();


        etFees =root.findViewById(R.id.edtFees);
        etLoaction = root.findViewById(R.id.edtLocation);
        edtName = root.findViewById(R.id.edtName);
        edtStd = root.findViewById(R.id.edtStd);
        edtContact = root.findViewById(R.id.edtContact);
        spinner = root.findViewById(R.id.spinnerMedium);
        btnAdd =root.findViewById(R.id.btnAdd);
        std = myDb.getAllStdNames();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStudent();
            }
        });

        //Spinner

        ArrayAdapter adapter = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,std );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtStd.setAdapter(adapter);
        return root;
    }
    private void OpenHomeFragment() {
        Fragment home = new HomeFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment,home);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void AddStudent(){

        String etName = edtName.getText().toString();
        String etStd = edtStd.getSelectedItem().toString();
        String etContact = edtContact.getText().toString();
        String spMedium = spinner.getSelectedItem().toString();
        String loaction = etLoaction.getText().toString();
        float fees;
        if (etFees.getText().toString().equals("")){
            fees = 0;
        }else fees = Float.parseFloat(etFees.getText().toString());


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
            boolean isInserted =  myDb.insertData(etName,etStd,spMedium,etContact,null, loaction, fees);
            if (isInserted == true){
                Toast.makeText(getContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                OpenHomeFragment();
            }

            else
                Toast.makeText(getContext(), "Error occured time  of insertion data", Toast.LENGTH_SHORT).show();
        }
    }

}