package com.r2soft.freedoministriesinternational.packages.template;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.r2soft.freedoministriesinternational.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;

public class TemplateAdapter implements TemplatePort {


    @Override
    public View loadTemplate(int layoutResource) {
        LayoutInflater layoutInflater = MainActivity.getInstance().getLayoutInflater();
        return layoutInflater.inflate(layoutResource,null);
    }

    @Override
    public void loopLoad(View view, JSONArray jsonArray, int innerTemplate, OnLoadTemplate onLoadTemplate) {
        TemplateAdapter templateAdapter = new TemplateAdapter();
        TemplateService templateService = new TemplateService(templateAdapter);
        for (int i = 0; i < jsonArray.length();i++){
            View view1 = templateService.loadTemplate(innerTemplate);
            try {
                onLoadTemplate.load(view1, jsonArray.getJSONObject(i));
                ((ViewGroup) view).addView(view1);
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }

}
