package com.r2soft.freedoministriesinternational.packages.apis;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.r2soft.freedoministriesinternational.packages.ajax.AjaxRequestPort;
import com.r2soft.freedoministriesinternational.packages.cms.CmsLoaderService;
import com.r2soft.freedoministriesinternational.packages.contentmutators.SermonMutator;
import com.r2soft.freedoministriesinternational.packages.registry.RegistryAdapter;
import com.r2soft.freedoministriesinternational.packages.registry.RegistryService;

import org.json.JSONException;
import org.json.JSONObject;

public class SermonsApiRequestAdapter implements AjaxRequestPort {

    View act = null;

    public SermonsApiRequestAdapter(View act){
        this.act = act;
    }
//sermons
    @Override
    public String getUrl() {
        return "https://freedoministriesinternational.com/api/sermons";
    }

    @Override
    public JSONObject getPayloadData() {
        return null;
    }

    @Override
    public void onSuccess(String response) {
        SermonMutator sermonMutator = new SermonMutator(act);
        CmsLoaderService cmsLoaderService = new CmsLoaderService(sermonMutator);

        try {
            cmsLoaderService.loadContent(new JSONObject(response));
        } catch (JSONException e) {
            cmsLoaderService.loadContent(null);
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void saveResponse(String key, String response) {
        RegistryService registryService = new RegistryService(new RegistryAdapter());
        registryService.setKey(key,response);
    }

    @Override
    public String getResponse(String key) {
        RegistryService registryService = new RegistryService(new RegistryAdapter());
        return registryService.getKey(key);
    }

    @Override
    public boolean hasResponse(String key) {
        RegistryService registryService = new RegistryService(new RegistryAdapter());
        return registryService.hasKey(key);
    }

}
