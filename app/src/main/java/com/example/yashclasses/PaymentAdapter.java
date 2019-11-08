package com.example.yashclasses;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {
    @NonNull

    private Context mContext;
    private Cursor mCursor;
    int len;


    public PaymentAdapter(Context context, Cursor cursor,int l){
        mContext = context;
        mCursor = cursor;
        mCursor.moveToFirst();
        len = l;
    }
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.custom_payment_list,parent,false);
        return new PaymentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        String Credited =  mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.PAY_COL_3));
        String PayTime = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.PAY_COL_4));

        Credited = "Credited :  " + Credited;
        holder.txtCredited.setText(Credited);
        holder.txtPaytime.setText(PayTime);

        if(!mCursor.moveToNext()) {
            return;
        }
    }



    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class  PaymentViewHolder extends RecyclerView.ViewHolder{

        TextView txtCredited,txtPaytime;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCredited =itemView.findViewById(R.id.txtCredited);
            txtPaytime = itemView.findViewById(R.id.txtPayTime);
        }

    }
}
