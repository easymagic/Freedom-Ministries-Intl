package com.r2soft.freedoministriesinternational.packages.template;

import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

public interface TemplatePort {

   View loadTemplate(int layoutResource);
   void loopLoad(View view, JSONArray jsonArray,int innerTemplate,OnLoadTemplate onLoadTemplate);


   interface OnLoadTemplate{
      void load(View view, JSONObject data);
   }

}

