package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.CustomerData;
import com.example.myapplication.R;

import java.util.List;


public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.ViewHolder> {

    private List<CustomerData> customerList;
    private Context context;

    public CustomersAdapter(Context context, List<CustomerData> customerList) {
        this.context=context;
        this.customerList=customerList;
    }


    @NonNull
    @Override
    public CustomersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_customersdata_fetch, parent, false);
        return new CustomersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomersAdapter.ViewHolder holder, int position) {
        CustomerData customersList = customerList.get(position);
        holder.id.setText(customersList.getId()+"");
        holder.user_id.setText(customersList.getUser_id()+"");
        holder.company_id.setText(customersList.getCompany_id()+"");
        holder.company_name.setText(customersList.getCompany_name());
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id,user_id,company_id,company_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            user_id = itemView.findViewById(R.id.userId);
            company_id = itemView.findViewById(R.id.companyID);
            company_name = itemView.findViewById(R.id.companyName);

        }
    }
}