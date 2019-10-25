package com.example.yashclasses.ui.home;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yashclasses.DatabaseHelper;
import com.example.yashclasses.R;
import com.example.yashclasses.ui.std_manage.StdAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        String Medium = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_8));

        holder.Sname.setText(Name);
        Std = "Std : " + Std;
        Medium = Medium + " - Medium";
        holder.Sstd.setText(Std);
        holder.Smedium.setText(Medium);

        if(!mCursor.moveToNext()) {
            return;
        }

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

        public TextView Sname,Sstd,Smedium;
        public Button btnCall,btnPay;

        public StudentViewHolder(@NonNull View itemView){
            super(itemView);

            Sname = itemView.findViewById(R.id.Custom_txtName);
            Sstd = itemView.findViewById(R.id.txtstd);
            Smedium = itemView.findViewById(R.id.Custom_txtMedium);

            btnCall = itemView.findViewById(R.id.btnCall);
            btnPay = itemView.findViewById(R.id.btnPayment);

            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        mCursor.moveToFirst();
                        String contact = mCursor.getString(mCursor.getColumnIndex("ContactNo"));

                        Intent intent = new Intent(Intent.ACTION_DIAL , Uri.parse("tel:" + contact));

                        mContext.startActivity(intent);

                        Toast.makeText(mContext, "Call button", Toast.LENGTH_SHORT).show();
                }
            });

            btnPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Payment button", Toast.LENGTH_SHORT).show();

                }
            });

        }


    }
}
