package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.CustomerData;
import com.example.myapplication.model.Invoices;

import java.util.List;

public class InvoicesListAdapter extends RecyclerView.Adapter<InvoicesListAdapter.ViewHolder> {
    private List<Invoices> invoicesList;
    private Context context;

    public InvoicesListAdapter(Context context, List<Invoices> invoicesList) {
        this.context = context;
        this.invoicesList = invoicesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_invoices_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoicesListAdapter.ViewHolder holder, int position) {
        Invoices  invoiceList = invoicesList.get(position);
           holder.id.setText(invoiceList.getId()+"");
          holder.company_name.setText(invoiceList.getClientDetails().getCompany_name());
          holder.terms.setText(invoiceList.getInvoiceDetails().getTerms());

    }

    @Override
    public int getItemCount() {
        return invoicesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id, terms, company_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.idInv);
            company_name = itemView.findViewById(R.id.companyNameInv);
            terms = itemView.findViewById(R.id.termsInv);

        }
    }
}
