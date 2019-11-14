package com.r2soft.freedoministriesinternational.packages.ajax;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class AjaxAdapter implements AjaxPort {

//    private Context context = null;
    private RequestQueue requestQueue = null;

    public AjaxAdapter(Context context){
//        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(final AjaxRequestPort ajaxRequestPort) {
        if (ajaxRequestPort.hasResponse(ajaxRequestPort.getUrl())){ //handle caching here ...
            ajaxRequestPort.onSuccess(ajaxRequestPort.getResponse(ajaxRequestPort.getUrl()));
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ajaxRequestPort.getUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                   ajaxRequestPort.saveResponse(ajaxRequestPort.getUrl(),response);
                   ajaxRequestPort.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ajaxRequestPort.onError(error.getMessage());
            }
        });

        requestQueue.add(stringRequest);
    }

    @Override
    public void post(final AjaxRequestPort ajaxRequestPort) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ajaxRequestPort.getUrl(), ajaxRequestPort.getPayloadData(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               ajaxRequestPort.onSuccess(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ajaxRequestPort.onError(error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}
