package com.r2soft.freedoministriesinternational.ui.main.packages;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.r2soft.freedoministriesinternational.R;
import com.r2soft.freedoministriesinternational.packages.ajax.AjaxAdapter;
import com.r2soft.freedoministriesinternational.packages.ajax.AjaxService;
import com.r2soft.freedoministriesinternational.packages.apis.MinistriesApiRequestAdapter;

public class MinistriesView extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        AjaxService ajaxService = new AjaxService(new AjaxAdapter(getContext()));

        View view = inflater.inflate(R.layout.ministries_layout, container, false);

        ajaxService.get(new MinistriesApiRequestAdapter(view));

        return view;
    }
}
