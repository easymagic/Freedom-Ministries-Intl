package com.r2soft.freedoministriesinternational.packages.html;

import android.widget.TextView;

public class HtmlService implements HtmlPort {

    HtmlPort htmlPort = null;

    public HtmlService(HtmlPort htmlPort){
        this.htmlPort = htmlPort;
    }

    @Override
    public void setText(TextView textView, String html) {
        htmlPort.setText(textView,html);
    }
}
