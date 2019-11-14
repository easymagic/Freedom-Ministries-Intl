package com.r2soft.freedoministriesinternational.packages.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicImageLoaderAdapter implements PicImageLoaderPort {

    private Context context = null;

    public PicImageLoaderAdapter(Context context){
        this.context = context;
    }

    @Override
    public void loadInto(ImageView imageView,String url) {
        Picasso.with(context).load(url).fit().centerCrop().into(imageView);
    }

}
