package com.example.shosho.dietfood.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.model.MyOrdersData;
import com.example.shosho.dietfood.model.OrderDetailsData;

import java.util.List;

public class MyOrdersDetailsAdapter  extends RecyclerView.Adapter<MyOrdersDetailsAdapter.ViewHolder> {
    private Context context;
    private List<OrderDetailsData> orderDetailsDataList ;

    //DetailsHomeProductView detailsHomeProductView;


    public MyOrdersDetailsAdapter(Context context, List<OrderDetailsData> orderDetailsDataList) {
        this.context = context;
        this.orderDetailsDataList = orderDetailsDataList;
    }

    @Override
    public MyOrdersDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.row_details_my_orders,parent,false);
        return new MyOrdersDetailsAdapter.ViewHolder(view);
    }
   /* public void onClick(DetailsHomeProductView detailsHomeProductView)
    {
        this.detailsHomeProductView=detailsHomeProductView;
    }*/

    @Override
    public void onBindViewHolder(@NonNull final MyOrdersDetailsAdapter.ViewHolder holder, final int position) {
        holder.name.setText("اسم الوجبة "+orderDetailsDataList.get( position ).getMealFoodsName());
        Typeface customFontMedium = Typeface.createFromAsset( context.getAssets(), "Fonts/SST Arabic Medium.ttf" );
        holder.name.setTypeface( customFontMedium );

        holder.price.setText("السعر "+orderDetailsDataList.get( position ).getMealFoodsPrice());
        holder.price.setTypeface( customFontMedium );

        holder.quantity.setText("الكمية "+orderDetailsDataList.get( position ).getQty());
        holder.quantity.setTypeface( customFontMedium );


    }

    @Override
    public int getItemCount() {
        return orderDetailsDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView price;
        private TextView quantity;


        public ViewHolder(View itemView) {
            super( itemView );

            name=itemView.findViewById( R.id.row_details_my_orders_name );
            price=itemView.findViewById( R.id.row_details_my_orders_price );
            quantity=itemView.findViewById( R.id.row_details_my_orders_quantity );


        }
    }
}
