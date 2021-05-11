package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Products;

import java.util.List;

public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsAdapter.ViewHolder> {
    private List<Products> productsList;
    private Context context;

    public ProductDetailsAdapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_product_details_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailsAdapter.ViewHolder holder, int position) {
        Products products = productsList.get(position);
        holder.id.setText(products.getId() + "");
        holder.u_id.setText(products.getUser_id() + "");
        holder.c_id.setText(products.getCompany_id() + "");
        holder.p_type.setText(products.getProduct_type());
        holder.p_name.setText(products.getProduct_name());
        holder.unit.setText(products.getUnit());
        holder.cost.setText(products.getCost() + "");
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id, u_id, c_id, p_type, p_name, unit, cost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            u_id = itemView.findViewById(R.id.user_id);
            c_id = itemView.findViewById(R.id.company_id);
            p_type = itemView.findViewById(R.id.product_type);
            p_name = itemView.findViewById(R.id.product_name);
            unit = itemView.findViewById(R.id.unit);
            cost = itemView.findViewById(R.id.cost);

        }
    }
}
