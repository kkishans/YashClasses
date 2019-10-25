package com.example.yashclasses.ui.std_manage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.yashclasses.R;

public class StdDialog extends AppCompatDialogFragment {
    EditText etFees,etStd;
    Spinner spinnermedium;
    private StdDialogListener stdDialogListener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_add_std_layout,null);
        etFees = view.findViewById(R.id.txtfees);
        etStd = view.findViewById(R.id.txtstd);
        spinnermedium = view.findViewById(R.id.spinnerMedium);

        builder.setView(view)
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(),"Process Canceled",Toast.LENGTH_LONG).show();
                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String StdName = etStd.getText().toString();
                        String medium = spinnermedium.getSelectedItem().toString();
                        String fees = etFees.getText().toString();
                        if(StdName.equals("") || StdName.equals(null) || medium.equals("") || medium.equals(null) ||fees.equals("") || fees.equals(null)){
                            new AlertDialog.Builder(getActivity()).setTitle("Invalid Data Insertion.")
                                    .setMessage("There are all field required. \nmay you have insert invalid data. \nPlease try again. ")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    }).show();
                        }else {
                            double fees2  = Double.parseDouble(fees);
                            stdDialogListener.applyTexts(StdName,fees2,medium);
                        }

                    }
                });




        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
       try {
           stdDialogListener = (StdDialogListener) context;
       }catch (ClassCastException e)
       {
          // e.getStackTrace();
           throw new ClassCastException(context.toString()+"must implement stddialogeListener");
       }


    }

    public interface StdDialogListener{
        void  applyTexts(String StdName, double fees, String medium);
    }
}
