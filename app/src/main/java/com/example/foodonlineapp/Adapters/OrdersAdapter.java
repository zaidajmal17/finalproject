package com.example.foodonlineapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodonlineapp.Models.OrdersModel;
import com.example.foodonlineapp.R;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.viewholder> {
    ArrayList<OrdersModel> list;
    Context context;

    public OrdersAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final OrdersModel orders = list.get(position);
        holder.orderimage.setImageResource(orders.getOrderimage());
        holder.solditemname.setText(orders.getSolditemname());
        holder.ordernumber.setText(orders.getOrdernumber());
        holder.price.setText(orders.getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView orderimage;
        TextView solditemname, ordernumber, price;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            orderimage = itemView.findViewById(R.id.orderimg);
            solditemname = itemView.findViewById(R.id.orderitemname);
            ordernumber = itemView.findViewById(R.id.ordernumber);
            price= itemView.findViewById(R.id.orderprice);
        }
    }
}
