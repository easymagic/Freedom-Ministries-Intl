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

public class SermonMutator implements CmsLoaderPort {
    View act = null;
    String baseUrl = "https://freedoministriesinternational.com/uploads/";

    JSONArray sermons = null;
    JSONObject sermon = null;


    public SermonMutator(View act){
        this.act = act;
    }

    @Override
    public void loadContent(JSONObject jsonObject) {

       sermons = jsonObject.optJSONObject("sermons").optJSONArray("list");
       sermon = jsonObject.optJSONObject("sermon").optJSONObject("data");

       loadSermon();
       loadSermons();
    }


    private void loadSermon(){
    //sermon_of_the_day_topic
        TextView sermon_of_the_day_topic = act.findViewById(R.id.sermon_of_the_day_topic);
        TextView sermon_of_the_day_content = act.findViewById(R.id.sermon_of_the_day_content);
        ImageView sermon_of_the_day_image = act.findViewById(R.id.sermon_of_the_day_image);

        PicImageLoaderService picImageLoaderService = new PicImageLoaderService(new PicImageLoaderAdapter(act.getContext()));


        sermon_of_the_day_topic.setText(sermon.optString("topic"));

        picImageLoaderService.loadInto(sermon_of_the_day_image,baseUrl + sermon.optString("image"));

        HtmlService htmlService = new HtmlService(new HtmlAdapter());

        htmlService.setText(sermon_of_the_day_content,sermon.optString("content"));

    }

    private void loadSermons(){

        TemplateService templateService = new TemplateService(new TemplateAdapter());
        templateService.loopLoad(act.findViewById(R.id.sermons), sermons, R.layout.inner_row, new TemplatePort.OnLoadTemplate() {
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
