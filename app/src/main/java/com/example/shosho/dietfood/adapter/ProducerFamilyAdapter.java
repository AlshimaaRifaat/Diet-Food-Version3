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
import com.example.shosho.dietfood.model.ProducerFamilyData;
import com.example.shosho.dietfood.view.DetailsProducerFamilyView;

import java.util.List;

public class ProducerFamilyAdapter extends RecyclerView.Adapter<ProducerFamilyAdapter.ViewHolder> {
    private Context context;
    private List<ProducerFamilyData> producerFamilyDataList ;



    DetailsProducerFamilyView detailsProducerFamilyView;
    public ProducerFamilyAdapter(Context context, List<ProducerFamilyData> producerFamilyDataList) {
        this.context = context;
        this.producerFamilyDataList = producerFamilyDataList;
    }

    @Override
    public ProducerFamilyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.row_producer_family,parent,false);
        return new ProducerFamilyAdapter.ViewHolder(view);
    }
    public void onClick(DetailsProducerFamilyView detailsProducerFamilyView)
    {
        this.detailsProducerFamilyView=detailsProducerFamilyView;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProducerFamilyAdapter.ViewHolder holder, final int position) {
        holder.name.setText(producerFamilyDataList.get( position ).getName());
        Typeface customFontMedium = Typeface.createFromAsset( context.getAssets(), "Fonts/SST Arabic Medium.ttf" );
        Typeface customFontRegular = Typeface.createFromAsset( context.getAssets(), "Fonts/Cairo-Regular.ttf" );
        holder.name.setTypeface( customFontMedium );

        holder.price.setText(producerFamilyDataList.get( position ).getPrice()+" SR");
        holder.price.setTypeface( customFontRegular );


        Glide.with( context ).load( "http://dietfoodksa.com/site"+producerFamilyDataList.get( position ).getImage())
                .into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailsProducerFamilyView.showProducerFamilyDetails(producerFamilyDataList.get( position ));

                    /*DetailsProducerFamilyFragment detailsHomeProductFragment=new DetailsProducerFamilyFragment();
                    Bundle bundle=new Bundle( );
                    bundle.putString( "id",producerFamilyDataList.get(position).getId());
                    detailsHomeProductFragment.setArguments( bundle );
                    Toast.makeText(context, "idddd", Toast.LENGTH_SHORT).show();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new DetailsProducerFamilyFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_frame_container, myFragment).addToBackStack(null).commit();*/




            }
        });

    }

    @Override
    public int getItemCount() {
        return producerFamilyDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView name;
        private TextView price;


        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_prod_family_image );
            name=itemView.findViewById( R.id.row_prod_family_title );
            price=itemView.findViewById( R.id.row_prod_family_price );


        }
    }
}

