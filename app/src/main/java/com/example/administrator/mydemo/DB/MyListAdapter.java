package com.example.administrator.mydemo.DB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.mydemo.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/13.
 */
public class MyListAdapter extends BaseAdapter {
    ArrayList<TelclassInfo> mlist;
         Context context;
           public MyListAdapter(Context context1, ArrayList<TelclassInfo>  list){
               context=context1;
                 mlist=list;
           }



    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView( final  int position, View convertView, ViewGroup parent) {

        ViewHodler mhodler;
              if(convertView==null) {
                  convertView = LayoutInflater.from(context).inflate(R.layout.activity_main2, null);
                  mhodler=new ViewHodler();
                  mhodler.tv= (TextView) convertView.findViewById(R.id.text_show);
                  convertView.setTag(mhodler);

              }else {
                  mhodler= (ViewHodler) convertView.getTag();

              }



        mhodler.tv.setText(mlist.get(position).name);
             return  convertView;
    }

    class ViewHodler{

        TextView tv;


    }












}