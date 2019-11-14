package com.r2soft.freedoministriesinternational.ui.main.packages;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.r2soft.freedoministriesinternational.R;
import com.r2soft.freedoministriesinternational.packages.ajax.AjaxAdapter;
import com.r2soft.freedoministriesinternational.packages.ajax.AjaxService;
import com.r2soft.freedoministriesinternational.packages.apis.HomeApiRequestAdapter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class HomeView extends Fragment implements ImageListener {

    CarouselView carouselView = null;

    int[] sampleImages = {R.drawable.slide_1,R.drawable.slide_2};
    ImageListener imageListener = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.home_layout, container, false);

//        carouselView = view.findViewById(R.id.home_slides);

        AjaxAdapter ajaxAdapter = new AjaxAdapter(getContext());
        AjaxService ajaxService = new AjaxService(ajaxAdapter);

        ajaxService.get(new HomeApiRequestAdapter(view));


//        carouselView.setPageCount(sampleImages.length);
//        carouselView.setImageListener(this);
        return view;

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setImageForPosition(int position, ImageView imageView) {
//      imageView.setImageResource(sampleImages[position]);
    }

}
