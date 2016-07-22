package com.example.administrator.mydemo.phone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mydemo.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/21.
 */
public class InfoAdapter extends BaseAdapter {
    Context context;
    ArrayList<InfoPhone>  mlist;
    public InfoAdapter(Context c,ArrayList<InfoPhone> list){
        context=c;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewhodler mhodler;
        if (convertView==null){

            convertView = LayoutInflater.from(context).inflate(R.layout.info_lv,null);
             mhodler=new Viewhodler();
            mhodler.iv= (ImageView) convertView.findViewById(R.id.info_iv);
            mhodler.tv1= (TextView) convertView.findViewById(R.id.info_tv1);
            mhodler.tv2= (TextView) convertView.findViewById(R.id.info_tv2);
            convertView.setTag(mhodler);

        }else {
         mhodler= (Viewhodler) convertView.getTag();

        }
          mhodler.iv.setImageResource(mlist.get(position).draw);
          mhodler.tv1.setText(mlist.get(position).tv1);
          mhodler.tv2.setText(mlist.get(position).tv2);

        return convertView;
    }

    class  Viewhodler{
     //view帮助类
        TextView tv1;
        TextView tv2;
        ImageView iv;

    }

}
