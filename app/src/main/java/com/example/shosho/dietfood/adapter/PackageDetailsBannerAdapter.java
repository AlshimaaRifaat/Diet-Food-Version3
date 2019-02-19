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
import com.example.shosho.dietfood.model.PackageDetailsImageData;
import com.example.shosho.dietfood.model.home.HomeBannerData;

import java.util.List;

public class PackageDetailsBannerAdapter extends  RecyclerView.Adapter<PackageDetailsBannerAdapter.ViewHolder> {
    Context context;
    List<PackageDetailsImageData> packageDetailsImageDataList;

    public PackageDetailsBannerAdapter(Context context, List<PackageDetailsImageData> packageDetailsImageDataList) {
        this.context = context;
        this.packageDetailsImageDataList = packageDetailsImageDataList;
    }

    @Override
    public PackageDetailsBannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate(R.layout.row_home_banner,parent,false);
        return new PackageDetailsBannerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageDetailsBannerAdapter.ViewHolder holder, int position) {
        Glide.with( context ).load( "http://WWW.dietfoodksa.com/site/public/upload/multi-image-package/"
                +packageDetailsImageDataList.get( position ).getImage() ).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return packageDetailsImageDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_home_banner_image);
        }
    }
}
