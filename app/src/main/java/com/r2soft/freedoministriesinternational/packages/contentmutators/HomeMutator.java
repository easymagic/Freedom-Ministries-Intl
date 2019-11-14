package com.r2soft.freedoministriesinternational.packages.contentmutators;

import android.media.Image;
import android.support.v4.app.FragmentActivity;
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
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeMutator implements CmsLoaderPort, ImageListener {

    private View act;
    private CarouselView carouselView;
    private JSONArray home_slides = null;
    private JSONObject welcome_message = null;
    private JSONObject today_sermons = null;
    private JSONArray ministries = null;
    private JSONObject quote_of_the_day = null;
    private JSONArray latest_news = null;

    String baseUrl = "https://freedoministriesinternational.com/uploads/";

    public  HomeMutator(View act){
         this.act = act;
         carouselView = this.act.findViewById(R.id.home_slides);
    }

    @Override
    public void loadContent(JSONObject jsonObject) {

        home_slides = jsonObject.optJSONObject("home_slides").optJSONArray("list");
        welcome_message = jsonObject.optJSONObject("welcome_message").optJSONObject("data");
        today_sermons = jsonObject.optJSONObject("today_sermons").optJSONObject("data");
        ministries = jsonObject.optJSONObject("ministries").optJSONArray("list");
        quote_of_the_day = jsonObject.optJSONObject("quote_of_the_day");
        latest_news = jsonObject.optJSONObject("latest_news").optJSONArray("list");

        carouselView.setImageListener(this);
        carouselView.setPageCount(home_slides.length());

        loadWelcomeMessageImage();
        loadWelcomeMessage();

        loadTodaysSermonsImage();
        loadTodaysSermonText();

        loadMinistries();
        loadQuoteOfTheDay();
        loadLatestNews();
    }

    private void loadLatestNews(){
       TemplateService templateService = new TemplateService(new TemplateAdapter());
       templateService.loopLoad(act.findViewById(R.id.latest_news), latest_news, R.layout.inner_row, new TemplatePort.OnLoadTemplate() {
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

    private void loadWelcomeMessageImage(){
        //welcome_message
        ImageView welcome_message_image = act.findViewById(R.id.welcome_message_image);
        PicImageLoaderAdapter picImageLoaderAdapter = new PicImageLoaderAdapter(act.getContext());
        PicImageLoaderService picImageLoaderService = new PicImageLoaderService(picImageLoaderAdapter);

        picImageLoaderService.loadInto(welcome_message_image,baseUrl + welcome_message.optString("image"));
    }

    private void loadWelcomeMessage(){
        //welcome_message
        HtmlAdapter htmlAdapter = new HtmlAdapter();
        HtmlService htmlService = new HtmlService(htmlAdapter);
        TextView welcome_message_text = act.findViewById(R.id.welcome_message_text);
//        welcome_message_text.setText(welcome_message.optString("content"));
        htmlService.setText(welcome_message_text,welcome_message.optString("content"));
    }

    private void loadTodaysSermonsImage(){
        //welcome_message
        ImageView today_sermon_image = act.findViewById(R.id.today_sermons_image);
        PicImageLoaderAdapter picImageLoaderAdapter = new PicImageLoaderAdapter(act.getContext());
        PicImageLoaderService picImageLoaderService = new PicImageLoaderService(picImageLoaderAdapter);

        picImageLoaderService.loadInto(today_sermon_image,baseUrl + today_sermons.optString("image"));
    }

    private void loadTodaysSermonText(){
        //welcome_message
        HtmlAdapter htmlAdapter = new HtmlAdapter();
        HtmlService htmlService = new HtmlService(htmlAdapter);
        TextView today_sermons_text = act.findViewById(R.id.today_sermons_text);
//        welcome_message_text.setText(welcome_message.optString("content"));
        htmlService.setText(today_sermons_text,today_sermons.optString("content"));

    }

    void loadMinistries(){
        TemplateAdapter templateAdapter = new TemplateAdapter();
        TemplateService templateService = new TemplateService(templateAdapter);
        templateService.loopLoad(act.findViewById(R.id.ministries), ministries, R.layout.inner_row, new TemplatePort.OnLoadTemplate() {
            @Override
            public void load(View view,JSONObject data) {

                ImageView imageView = view.findViewById(R.id.image);
                TextView textView = view.findViewById(R.id.content);

                 PicImageLoaderAdapter picImageLoaderAdapter = new PicImageLoaderAdapter(act.getContext());
                 PicImageLoaderService picImageLoaderService = new PicImageLoaderService(picImageLoaderAdapter);

                 picImageLoaderService.loadInto(imageView,baseUrl + data.optString("image"));

                 HtmlService htmlService = new HtmlService(new HtmlAdapter());

                 htmlService.setText(textView,data.optString("topic"));

            }
        });
    }

    private void loadQuoteOfTheDay(){
        HtmlAdapter htmlAdapter = new HtmlAdapter();
        HtmlService htmlService = new HtmlService(htmlAdapter);

        TextView quote_of_the_day_text = act.findViewById(R.id.quote_of_the_day);
        TextView quote_of_the_day_topic = act.findViewById(R.id.quote_of_the_day_topic);

        quote_of_the_day_topic.setText( "(" + quote_of_the_day.optJSONObject("data").optString("topic") + ")");

        htmlService.setText(quote_of_the_day_text,quote_of_the_day.optJSONObject("data").optString("content"));

    }


    @Override
    public void setImageForPosition(int position, ImageView imageView) {
        PicImageLoaderAdapter picImageLoaderAdapter = new PicImageLoaderAdapter(act.getContext());
        PicImageLoaderService picImageLoaderService = new PicImageLoaderService(picImageLoaderAdapter);

        try {
            picImageLoaderService.loadInto(imageView,baseUrl + home_slides.optJSONObject(position).getString("image"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
