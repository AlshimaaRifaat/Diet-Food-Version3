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
import com.example.shosho.dietfood.model.home.HomeProductData;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.view.DetailsHomeProductView;

import java.util.List;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ViewHolder> {
private Context context;
private List<HomeProductData> homeProductDataList ;

DetailsHomeProductView detailsHomeProductView;

    public HomeProductAdapter(Context context, List<HomeProductData> homeProductDataList) {
        this.context = context;
        this.homeProductDataList = homeProductDataList;
    }

    @Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.row_home_product,parent,false);
        return new ViewHolder(view);
        }
public void onClick(DetailsHomeProductView detailsHomeProductView)
{
    this.detailsHomeProductView=detailsHomeProductView;
}

@Override
public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.name.setText(homeProductDataList.get( position ).getName());
        Typeface customFontMedium = Typeface.createFromAsset( context.getAssets(), "Fonts/SST Arabic Medium.ttf" );

        holder.name.setTypeface( customFontMedium );

        holder.price.setText(homeProductDataList.get( position ).getPrice()+" SR");

        holder.price.setTypeface( customFontMedium );

    Glide.with( context ).load( "http://dietfoodksa.com/site"+homeProductDataList.get( position ).getImage())
            .into(holder.imageView);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            detailsHomeProductView.showHomeProductDetails(homeProductDataList.get( position ));
        }
    });
        }

@Override
public int getItemCount() {
        return homeProductDataList.size();
        }


public class ViewHolder extends RecyclerView.ViewHolder{
    private ImageView imageView;
    private TextView name;
    private TextView price;

    public ViewHolder(View itemView) {
        super( itemView );
        imageView=itemView.findViewById( R.id.row_home_product_image );
        name=itemView.findViewById( R.id.row_home_product_title );
        price=itemView.findViewById( R.id.row_home_product_price );


    }
}
}
