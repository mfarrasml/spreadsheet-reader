package com.example.databasebiodata;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.ViewHolder>{
    private ArrayList<PersonalIdentity> mData;

    DatabaseAdapter(ArrayList<PersonalIdentity> data){
        this.mData = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView;
        TextView addressTextView;
        TextView dobTextView;
        TextView phoneTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_name_data);
            addressTextView = itemView.findViewById(R.id.text_address_data);
            dobTextView = itemView.findViewById(R.id.text_dob_data);
            phoneTextView = itemView.findViewById(R.id.text_phone_data);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        PersonalIdentity personalData = mData.get(position);
        holder.nameTextView.setText(personalData.fullName);
        holder.addressTextView.setText(personalData.address);
        holder.dobTextView.setText(personalData.dateOfBirth);
        holder.phoneTextView.setText(personalData.phoneNumber);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
