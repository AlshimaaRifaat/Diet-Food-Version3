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
import com.example.shosho.dietfood.model.CardData;
import com.example.shosho.dietfood.view.DetailsCardView;
import com.example.shosho.dietfood.view.OnClickMinCardView;
import com.example.shosho.dietfood.view.OnDeleteIconClickView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private Context context;
    public static List<CardData> cardDataList ;

    DetailsCardView detailsCardView;
    OnClickMinCardView onClickMinCardView;

    OnDeleteIconClickView onDeleteIconClickView;


    public CardAdapter(Context context, List<CardData> cardDataList) {
        this.context = context;
        this.cardDataList = cardDataList;

    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.row_card,parent,false);
        return new CardAdapter.ViewHolder(view);
    }
    public void onClick(DetailsCardView detailsCardView)
    {
        this.detailsCardView=detailsCardView;
    }
    public void onClick(OnClickMinCardView onClickMinCardView)
    {
        this.onClickMinCardView=onClickMinCardView;

    }
    public void onClick(OnDeleteIconClickView onDeleteIconClickView)
    {
        this.onDeleteIconClickView=onDeleteIconClickView;

    }

    @Override
    public void onBindViewHolder(@NonNull final CardAdapter.ViewHolder holder, final int position) {
        holder.name.setText(cardDataList.get( position ).getMealFoodName());
        Typeface customFontMedium = Typeface.createFromAsset( context.getAssets(), "Fonts/SST Arabic Medium.ttf" );
        Typeface customFontRegular = Typeface.createFromAsset( context.getAssets(), "Fonts/Cairo-Regular.ttf" );
        holder.name.setTypeface( customFontMedium );

        holder.price.setText(cardDataList.get( position ).getTotalPrice() +" SR");
        holder.price.setTypeface( customFontRegular );


        holder.quantity.setText( Integer.toString(Integer.parseInt( cardDataList.get( position ).getQty() )  ));
        Glide.with( context ).load( "http://dietfoodksa.com/site"+cardDataList.get( position ).getImage())
                .into(holder.imageView);

        holder.plusQuantity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  holder.quantity.setText(cardDataList.get( position ).getQty()+1  );
                detailsCardView.showCardDetails( cardDataList.get( position ),holder );
            }
        } );

        holder.minQuantity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* CardData cardData=new CardData();
                cardData.setQty( cardDataList.get( position ).getQty() );*/
                //  holder.quantity.setText(cardDataList.get( position ).getQty()+1  );
                onClickMinCardView.showOnClickMinCardResult( cardDataList.get( position ),holder );
            }
        } );
        holder.deleteItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                onDeleteIconClickView.showOnDeleteIconClickResult
                        (cardDataList.get(position).getMealFoodId(), position);
                cardDataList.remove(position);
                notifyDataSetChanged();

            }

        } );

      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailsProducerFamilyView.showProducerFamilyDetails(producerFamilyDataList.get( position ));

                    *//*DetailsProducerFamilyFragment detailsHomeProductFragment=new DetailsProducerFamilyFragment();
                    Bundle bundle=new Bundle( );
                    bundle.putString( "id",producerFamilyDataList.get(position).getId());
                    detailsHomeProductFragment.setArguments( bundle );
                    Toast.makeText(context, "idddd", Toast.LENGTH_SHORT).show();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new DetailsProducerFamilyFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_frame_container, myFragment).addToBackStack(null).commit();*//*




            }
        });
*/
    }

    @Override
    public int getItemCount() {
        return cardDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView name;
        public TextView price;
        public TextView quantity;
        private ImageView plusQuantity;
        private ImageView minQuantity;
        public ImageView deleteItem;
        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_card_image );
            name=itemView.findViewById( R.id.row_card_name );
            price=itemView.findViewById( R.id.row_card_price );
            quantity=itemView.findViewById( R.id.row_card_quantity );
            plusQuantity=itemView.findViewById( R.id.row_card_plus );
            minQuantity=itemView.findViewById( R.id.row_card_minus );
            deleteItem=itemView.findViewById( R.id.row_card_icon_delete );


        }
    }
}

