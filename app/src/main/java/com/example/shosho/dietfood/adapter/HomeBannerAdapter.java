package com.example.shosho.dietfood.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.model.home.HomeBannerData;

import java.util.List;

public class HomeBannerAdapter extends  RecyclerView.Adapter<HomeBannerAdapter.ViewHolder> {
    Context context;
    List<HomeBannerData> homeBannerDataList;

    public HomeBannerAdapter(Context context, List<HomeBannerData> homeBannerDataList) {
        this.context = context;
        this.homeBannerDataList = homeBannerDataList;
    }

    @Override
    public HomeBannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate(R.layout.row_home_banner,parent,false);
        return new HomeBannerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeBannerAdapter.ViewHolder holder, int position) {
        Glide.with( context ).load( "http://dietfoodksa.com/site"
                +homeBannerDataList.get( position ).getImage() ).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return homeBannerDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_home_banner_image);
        }
    }
}