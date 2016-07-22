package com.example.administrator.mydemo.exe;

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
 * Created by Administrator on 2016/7/19.
 */
public class PhoneAdapter extends BaseAdapter {

    ArrayList<PhoneInfo> marrayList;
    Context mcontext;

    public PhoneAdapter(Context context,ArrayList<PhoneInfo> arrayList){
        marrayList=arrayList;
        mcontext = context;

    }


    @Override
    public int getCount() {
        return marrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return marrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mholder;
      if (convertView==null){
          convertView= LayoutInflater.from(mcontext).inflate(R.layout.phone_view,null);
           mholder=new ViewHolder();
           mholder.icon= (ImageView) convertView.findViewById(R.id.phone_iv);
          mholder.tv_pn= (TextView) convertView.findViewById(R.id.phone_pn);
          mholder.tv_vn= (TextView) convertView.findViewById(R.id.phone_vn);
          convertView.setTag(mholder);
      }else {
          mholder= (ViewHolder) convertView.getTag();

      }


        mholder.icon.setImageDrawable(marrayList.get(position).icon);
        mholder.tv_pn.setText(marrayList.get(position).name);
        mholder.tv_vn.setText(marrayList.get(position).versionName);




        return convertView;
    }

    class  ViewHolder{
     public    ImageView icon;
     public    TextView  tv_pn;
     public    TextView  tv_vn;



    }


}
