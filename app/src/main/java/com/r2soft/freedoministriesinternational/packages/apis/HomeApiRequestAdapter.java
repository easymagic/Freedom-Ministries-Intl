package com.r2soft.freedoministriesinternational.packages.apis;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.r2soft.freedoministriesinternational.packages.ajax.AjaxRequestPort;
import com.r2soft.freedoministriesinternational.packages.cms.CmsLoaderService;
import com.r2soft.freedoministriesinternational.packages.contentmutators.HomeMutator;
import com.r2soft.freedoministriesinternational.packages.registry.RegistryAdapter;
import com.r2soft.freedoministriesinternational.packages.registry.RegistryService;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeApiRequestAdapter implements AjaxRequestPort {

    View act = null;

    public HomeApiRequestAdapter(View act){
        this.act = act;
    }


    @Override
    public String getUrl() {
        return "https://freedoministriesinternational.com/api/home";
    }

    @Override
    public JSONObject getPayloadData() {
        return null;
    }

    @Override
    public void onSuccess(String response) {
        HomeMutator homeMutator = new HomeMutator(act);
        CmsLoaderService cmsLoaderService = new CmsLoaderService(homeMutator);
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
