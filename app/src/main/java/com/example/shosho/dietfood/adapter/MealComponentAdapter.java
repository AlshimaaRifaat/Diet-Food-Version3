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
import com.example.shosho.dietfood.model.MealComponentData;

import java.util.List;

public class MealComponentAdapter extends RecyclerView.Adapter<MealComponentAdapter.ViewHolder> {
    private Context context;
    private List<MealComponentData> mealComponentDataList ;

    public MealComponentAdapter(Context context, List<MealComponentData> mealComponentDataList) {
        this.context = context;
        this.mealComponentDataList = mealComponentDataList;
    }

    @Override
    public MealComponentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.row_meal_component,parent,false);
        return new MealComponentAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MealComponentAdapter.ViewHolder holder, final int position) {
        holder.name.setText("مكونات الوجبة"+"\n"+"الاسم: "+mealComponentDataList.get( position ).getName());
        Typeface customFontLight = Typeface.createFromAsset( context.getAssets(), "Fonts/SST Arabic Light.ttf" );

        holder.name.setTypeface( customFontLight );

        holder.quantity.setText(" الكمية: "+mealComponentDataList.get( position ).getQty());

        holder.quantity.setTypeface( customFontLight );





    }

    @Override
    public int getItemCount() {
        return mealComponentDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView quantity;

        public ViewHolder(View itemView) {
            super( itemView );

            name=itemView.findViewById( R.id.row_meal_component_name );
            quantity=itemView.findViewById( R.id.row_meal_component_quantity );



        }
    }
}

