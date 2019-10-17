package com.example.yashclasses.ui.std_manage;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yashclasses.DatabaseHelper;
import com.example.yashclasses.R;

public class StdAdapter extends RecyclerView.Adapter<StdAdapter.StdViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    public StdAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
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
        if(!mCursor.move(position)){
            return;
        }

        String std = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.STD_COL_1));
           String medium = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.STD_COL_2));
           double fees = mCursor.getDouble(mCursor.getColumnIndex(DatabaseHelper.STD_COL_3));
            Std obj = new Std(std,medium,fees);

        Toast.makeText(mContext,std+fees+medium,Toast.LENGTH_LONG).show();
           holder.txtStd.setText(obj.getStd());
           String temp = "Fees :" +obj.getFees();
           holder.txtFees.setText(temp);
           holder.txtMedium.setText(obj.getMedium());
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

    public class StdViewHolder extends RecyclerView.ViewHolder{

        public TextView txtStd,txtMedium,txtFees;
        public StdViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStd = itemView.findViewById(R.id.item_list_std);
            txtMedium = itemView.findViewById(R.id.item_list_medium);

            txtFees = itemView.findViewById(R.id.item_list_fees);

        }
    }

}
