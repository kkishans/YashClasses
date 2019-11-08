package com.example.yashclasses.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.method.TextKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yashclasses.DatabaseHelper;
import com.example.yashclasses.PaymentHistory;
import com.example.yashclasses.R;
import com.example.yashclasses.ui.std_manage.StdAdapter;
import com.example.yashclasses.ui.std_manage.StdDialog;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    int len;


    public StudentAdapter(Context context, Cursor cursor,int l){
        mContext = context;
        mCursor = cursor;
        mCursor.moveToFirst();
        len = l;
    }


    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.custom_student_list,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {



        String Name = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_2));
        String Std = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_3));
        String Medium = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_4));
        float fees = mCursor.getFloat(mCursor.getColumnIndex(DatabaseHelper.COL_8));

        holder.Sname.setText(capitalize(Name));
        Std = Std +" : ";
        Medium = Medium + " - Medium";
        holder.Sstd.setText(Std);
        holder.Smedium.setText(Medium);
        holder.RemainFee.setText(" Remaining Fees : " + fees);

        if(!mCursor.moveToNext()) {
            return;
        }

    }

    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newcursor){
        if(mCursor != null){
            mCursor.close();
        }
        mCursor = newcursor;

        if(newcursor != null){
            notifyDataSetChanged();
        }
    }

    public  class StudentViewHolder extends  RecyclerView.ViewHolder{

        public TextView Sname,Sstd,Smedium,RemainFee;
        public Button btnCall,btnPay;
        String Sid;
        DatabaseHelper mydb = new DatabaseHelper(mContext);

        public StudentViewHolder(@NonNull final View itemView){
            super(itemView);

            Sname = itemView.findViewById(R.id.Custom_txtName);
            Sstd = itemView.findViewById(R.id.txtstd);
            Smedium = itemView.findViewById(R.id.Custom_txtMedium);
            RemainFee = itemView.findViewById(R.id.RemainningFees);

            btnCall = itemView.findViewById(R.id.btnCall);
            btnPay = itemView.findViewById(R.id.btnPayment);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCursor.moveToFirst();
                    mCursor.move(getAdapterPosition());
                    String sid = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_1));

                    Intent intent = new Intent(mContext, PaymentHistory.class);
                    intent.putExtra("Sid",sid);
                    intent.putExtra("Sname",Sname.getText().toString());
                    mContext.startActivity(intent);

                    //Toast.makeText(mContext, "Click event " + getAdapterPosition() + " " + sid , Toast.LENGTH_SHORT).show();
                }
            });

            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        mCursor.moveToFirst();
                        mCursor.move(getAdapterPosition());
                        String contact = mCursor.getString(mCursor.getColumnIndex("ContactNo"));

                        Intent intent = new Intent(Intent.ACTION_DIAL , Uri.parse("tel:" + contact));

                        mContext.startActivity(intent);

                        //Toast.makeText(mContext, "Call button", Toast.LENGTH_SHORT).show();
                }
            });

            btnPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(mContext, "Payment button", Toast.LENGTH_SHORT).show();
                    paymentDialog();
                }
            });

        }

        public void paymentDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_payment_layout,null);

            final EditText etAmount  = view.findViewById(R.id.etAmount);

            builder.setView(view)
                    .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(mContext,"Process Canceled",Toast.LENGTH_LONG).show();
                }
            })
                    .setPositiveButton("Pay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Float amount = Float.valueOf(etAmount.getText().toString()) ;
                            mCursor.moveToFirst();
                            mCursor.move(getAdapterPosition());
                            Sid = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_1));

                            if(amount.equals("")){
                                new AlertDialog.Builder(mContext).setTitle("Invalid Data Insertion.")
                                        .setMessage("There are all field required. \nmay you have insert invalid data. \nPlease try again. ")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        }).show();
                            }else {
                               if (mydb.insertPayment(amount,Sid)){
                                   if (mydb.updatePay(amount,Sid)){
                                       Toast.makeText(mContext, "Payment Done", Toast.LENGTH_SHORT).show();
                                   }
                               }
                               else Toast.makeText(mContext, "Some problme is there", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
            final AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }

    }
}
