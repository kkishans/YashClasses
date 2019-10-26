package com.example.yashclasses.ui.std_manage;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yashclasses.DatabaseHelper;
import com.example.yashclasses.R;

import java.util.ArrayList;

public class StdAdapter extends RecyclerView.Adapter<StdAdapter.StdViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    int len;
    public StdAdapter(Context context, Cursor cursor,int l){
        mContext = context;
        mCursor = cursor;
        mCursor.moveToFirst();
        len = l;
    }

    @NonNull
    @Override
    public StdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.custom_std_item,parent,false);
        return new StdViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StdViewHolder holder, int position) {



            String std = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.STD_COL_1));
            String medium = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.STD_COL_2));
            double fees = mCursor.getDouble(mCursor.getColumnIndex(DatabaseHelper.STD_COL_3));

            holder.txtStd.setText(std);
            String temp = "Fees : "+fees;
            holder.txtFees.setText(temp);
            holder.txtMedium.setText(medium +" - Medium");

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

    public class StdViewHolder extends RecyclerView.ViewHolder implements StdDialog.StdDialogListener {

        public TextView txtStd,txtMedium,txtFees;


        public StdViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStd = itemView.findViewById(R.id.item_list_std);
            txtMedium = itemView.findViewById(R.id.item_list_medium);
            txtFees = itemView.findViewById(R.id.item_list_fees);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StdDialog stdDialog = new StdDialog();
                    //stdDialog.show(,"Std Update");
                    Toast.makeText(mContext, "Click std", Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public void applyTexts(String StdName, double fees, String medium) {

        }
    }

}
