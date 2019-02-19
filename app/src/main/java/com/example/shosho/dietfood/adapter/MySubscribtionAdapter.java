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
import com.example.shosho.dietfood.model.MySubscribtionData;
import com.example.shosho.dietfood.view.OnClickOrderDetailsView;

import java.util.List;

public class MySubscribtionAdapter extends RecyclerView.Adapter<MySubscribtionAdapter.ViewHolder> {
    private Context context;
    private List<MySubscribtionData> mySubscribtionDataList ;

    //OnClickOrderDetailsView onClickOrderDetailsView;


    public MySubscribtionAdapter(Context context, List<MySubscribtionData> mySubscribtionDataList) {
        this.context = context;
        this.mySubscribtionDataList = mySubscribtionDataList;
    }

    @Override
    public MySubscribtionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.row_my_subscribtion,parent,false);
        return new MySubscribtionAdapter.ViewHolder(view);
    }
   /* public void onClick(OnClickOrderDetailsView onClickOrderDetailsView)
    {
        this.onClickOrderDetailsView=onClickOrderDetailsView;
    }*/

    @Override
    public void onBindViewHolder(@NonNull final MySubscribtionAdapter.ViewHolder holder, final int position) {
        holder.status.setText(mySubscribtionDataList.get( position ).getStatus());
        Typeface customFontMedium = Typeface.createFromAsset( context.getAssets(), "Fonts/SST Arabic Medium.ttf" );
        holder.status.setTypeface( customFontMedium );

        holder.day.setText(mySubscribtionDataList.get( position ).getDay());
        holder.day.setTypeface( customFontMedium );

        holder.date.setText(mySubscribtionDataList.get( position ).getDue());
        holder.date.setTypeface( customFontMedium );



       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOrderDetailsView.showOrderDetails(myOrdersDataList.get( position ));
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mySubscribtionDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView status;
        private TextView day;
        private TextView date;

        public ViewHolder(View itemView) {
            super( itemView );

            status=itemView.findViewById( R.id.row_my_subscribtion_status);
            day=itemView.findViewById( R.id.row_my_subscribtion_day);
            date=itemView.findViewById( R.id.row_my_subscribtion_date );


        }
    }
}
