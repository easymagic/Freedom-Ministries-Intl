package com.r2soft.freedoministriesinternational.packages.ajax;

import org.json.JSONObject;

public interface AjaxRequestPort {


    String getUrl();
    JSONObject getPayloadData();
    void onSuccess(String response);
    void onError(String error);
    void saveResponse(String key,String response);
    String getResponse(String key);
    boolean hasResponse(String key);


}
