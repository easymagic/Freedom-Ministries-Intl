package com.r2soft.freedoministriesinternational.packages.contentmutators;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.r2soft.freedoministriesinternational.R;
import com.r2soft.freedoministriesinternational.packages.cms.CmsLoaderPort;
import com.r2soft.freedoministriesinternational.packages.html.HtmlAdapter;
import com.r2soft.freedoministriesinternational.packages.html.HtmlService;
import com.r2soft.freedoministriesinternational.packages.imageloader.PicImageLoaderAdapter;
import com.r2soft.freedoministriesinternational.packages.imageloader.PicImageLoaderService;
import com.r2soft.freedoministriesinternational.packages.template.TemplateAdapter;
import com.r2soft.freedoministriesinternational.packages.template.TemplatePort;
import com.r2soft.freedoministriesinternational.packages.template.TemplateService;

import org.json.JSONArray;
import org.json.JSONObject;

public class MinistriesMutator implements CmsLoaderPort {
    View act = null;

    JSONArray ministries = null;

    String baseUrl = "https://freedoministriesinternational.com/uploads/";


    public MinistriesMutator(View act){
        this.act = act;
    }


    @Override
    public void loadContent(JSONObject jsonObject) {

        ministries = jsonObject.optJSONObject("ministries").optJSONArray("list");

        loadMinistries();

    }


    private void loadMinistries(){
        TemplateService templateService = new TemplateService(new TemplateAdapter());
        templateService.loopLoad(act.findViewById(R.id.ministries), ministries, R.layout.inner_row, new TemplatePort.OnLoadTemplate() {
            @Override
            public void load(View view, JSONObject data) {

                ImageView imageView = view.findViewById(R.id.image);
                TextView textView = view.findViewById(R.id.content);
                TextView textViewTopic = view.findViewById(R.id.topic);


                PicImageLoaderAdapter picImageLoaderAdapter = new PicImageLoaderAdapter(act.getContext());
                PicImageLoaderService picImageLoaderService = new PicImageLoaderService(picImageLoaderAdapter);

                picImageLoaderService.loadInto(imageView,baseUrl + data.optString("image"));

                HtmlService htmlService = new HtmlService(new HtmlAdapter());

                htmlService.setText(textView,data.optString("content"));
                textViewTopic.setText(data.optString("topic"));

            }
        });
    }
}
