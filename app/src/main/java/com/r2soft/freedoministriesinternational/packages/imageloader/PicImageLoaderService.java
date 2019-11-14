package com.r2soft.freedoministriesinternational.packages.imageloader;

import android.widget.ImageView;

public class PicImageLoaderService implements PicImageLoaderPort {

    private PicImageLoaderPort picImageLoaderPort = null;

    public PicImageLoaderService(PicImageLoaderPort picImageLoaderPort){
        this.picImageLoaderPort = picImageLoaderPort;
    }

    @Override
    public void loadInto(ImageView imageView, String url) {
      picImageLoaderPort.loadInto(imageView,url);
    }
}
