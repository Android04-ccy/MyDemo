package com.example.administrator.mydemo.exe;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/7/19.
 */
public class PhoneInfo {
       //手机应用信息类
    public   String  packageName;
    public    String  versionName;
       public Drawable icon;
    public  String name;

    public  PhoneInfo( String  pN,String  vN,Drawable ic,String na){
        packageName=pN;
        versionName=vN;
        icon=ic;
        name=na;

    }


}
