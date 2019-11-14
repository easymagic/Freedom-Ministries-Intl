package com.r2soft.freedoministriesinternational.packages.cms;

import org.json.JSONObject;

public class CmsLoaderService implements CmsLoaderPort {

    private CmsLoaderPort cmsLoaderPort = null;

    public CmsLoaderService(CmsLoaderPort cmsLoaderPort){
        this.cmsLoaderPort = cmsLoaderPort;
    }

    @Override
    public void loadContent(JSONObject jsonObject) {
       cmsLoaderPort.loadContent(jsonObject);
    }

}
