package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;
import com.example.myapplication.model.RealmDBModel;

import java.util.List;

public class APIDataFetchAdapter extends RecyclerView.Adapter<APIDataFetchAdapter.ViewHolder> {

    private List<RealmDBModel> realmDbData;
    private Context context;


    public APIDataFetchAdapter(List<RealmDBModel> realmDbData, Context context) {
        this.context = context;
        this.realmDbData = realmDbData;
    }

    @NonNull
    @Override
    public APIDataFetchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_apidata_fetch, parent, false);
        return new APIDataFetchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull APIDataFetchAdapter.ViewHolder holder, int position) {
        RealmDBModel realmDBModel = realmDbData.get(position);
        holder.role_name.setText(realmDBModel.getUser_Role());
    }

    @Override
    public int getItemCount() {
        return realmDbData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView role_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            role_name = itemView.findViewById(R.id.user_role);

        }
    }
}