package com.r2soft.freedoministriesinternational.packages.ajax;

public class AjaxService implements AjaxPort {

    private AjaxPort ajaxPort = null;

    public AjaxService(AjaxPort ajaxPort){
        this.ajaxPort = ajaxPort;
    }

    @Override
    public void get(AjaxRequestPort ajaxRequestPort) {
      this.ajaxPort.get(ajaxRequestPort);
    }

    @Override
    public void post(AjaxRequestPort ajaxRequestPort) {
       this.ajaxPort.post(ajaxRequestPort);
    }
}
