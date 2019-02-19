package com.example.shosho.dietfood.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.model.MyOrdersData;
import com.example.shosho.dietfood.model.home.HomeProductData;
import com.example.shosho.dietfood.view.DetailsHomeProductView;
import com.example.shosho.dietfood.view.OnClickOrderDetailsView;
import com.example.shosho.dietfood.view.OrderDetailsView;

import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {
    private Context context;
    private List<MyOrdersData> myOrdersDataList ;

    OnClickOrderDetailsView onClickOrderDetailsView;


    public MyOrdersAdapter(Context context, List<MyOrdersData> myOrdersDataList) {
        this.context = context;
        this.myOrdersDataList = myOrdersDataList;
    }

    @Override
    public MyOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.row_my_orders,parent,false);
        return new MyOrdersAdapter.ViewHolder(view);
    }
    public void onClick(OnClickOrderDetailsView onClickOrderDetailsView)
    {
        this.onClickOrderDetailsView=onClickOrderDetailsView;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyOrdersAdapter.ViewHolder holder, final int position) {
        holder.orderId.setText("رقم الطلب "+myOrdersDataList.get( position ).getOrderId());
        Typeface customFontMedium = Typeface.createFromAsset( context.getAssets(), "Fonts/SST Arabic Medium.ttf" );
        holder.orderId.setTypeface( customFontMedium );

        holder.phone.setText("الهاتف "+myOrdersDataList.get( position ).getPhone());
        holder.phone.setTypeface( customFontMedium );

        holder.price.setText("السعر "+myOrdersDataList.get( position ).getTotalPrice());
        holder.price.setTypeface( customFontMedium );

        holder.address.setText("العنوان "+myOrdersDataList.get( position ).getAddress());
        holder.address.setTypeface( customFontMedium );

        holder.orderDate.setText("تاريخ الطلب "+myOrdersDataList.get( position ).getDate());
        holder.orderDate.setTypeface( customFontMedium );

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOrderDetailsView.showOrderDetails(myOrdersDataList.get( position ));
            }
        });
    }

    @Override
    public int getItemCount() {
        return myOrdersDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView orderId;
        private TextView phone;
        private TextView price;
        private TextView address;
        private TextView orderDate;

        public ViewHolder(View itemView) {
            super( itemView );

            orderId=itemView.findViewById( R.id.row_my_orders_order_id );
            phone=itemView.findViewById( R.id.row_my_orders_phone );
            price=itemView.findViewById( R.id.row_my_orders_price );
            address=itemView.findViewById( R.id.row_my_orders_address );
            orderDate=itemView.findViewById(R.id.row_my_orders_order_date);

        }
    }
}
