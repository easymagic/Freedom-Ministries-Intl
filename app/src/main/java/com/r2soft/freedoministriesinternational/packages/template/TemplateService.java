package com.r2soft.freedoministriesinternational.packages.template;

import android.view.View;

import org.json.JSONArray;

public class TemplateService implements TemplatePort {

    private TemplatePort templatePort = null;

    public TemplateService(TemplatePort templatePort){
        this.templatePort = templatePort;
    }

    @Override
    public View loadTemplate(int layoutResource) {
        return templatePort.loadTemplate(layoutResource);
    }

    @Override
    public void loopLoad(View view, JSONArray jsonArray, int innerTemplate, OnLoadTemplate onLoadTemplate) {
        templatePort.loopLoad(view,jsonArray,innerTemplate,onLoadTemplate);
    }

}
