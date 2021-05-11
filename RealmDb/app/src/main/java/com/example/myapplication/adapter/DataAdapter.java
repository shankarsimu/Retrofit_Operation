package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.DataModel;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Context context;
    private List<DataModel> dataList;

    public DataAdapter(Context context, List<DataModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.user_details_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        holder.postid.setText(dataList.get(position).getPostid()+"");
        holder.id.setText(dataList.get(position).getId()+"");
        holder.name.setText(dataList.get(position).getName());
        holder.email.setText(dataList.get(position).getEmail());
        holder.body.setText(dataList.get(position).getBody());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView postid,id,name,email,body;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postid=itemView.findViewById(R.id.postid);
            id=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.emailId);
            body=itemView.findViewById(R.id.body);
        }
    }
}
