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
 * Created by Administrator on 2016/7/15.
 */
public class TellistAdapter extends BaseAdapter {
    ArrayList<TelnumberInfo> mlist ;
     Context context;
    public TellistAdapter(Context c ,ArrayList<TelnumberInfo> list){
              mlist=list;
        context=c;

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

        ViewHodler mhodler;

        if (convertView==null){

            convertView= LayoutInflater.from(context).inflate(R.layout.activity_order_food,null);
            mhodler=new ViewHodler();
            mhodler.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
            mhodler.tv_num=(TextView) convertView.findViewById(R.id.tv_number);

            convertView.setTag(mhodler);
        }else {

            mhodler= (ViewHodler) convertView.getTag();

        }

             mhodler.tv_name.setText(mlist.get(position).name);
             mhodler.tv_num.setText(mlist.get(position).number);
         return convertView;
    }

    class ViewHodler{
        TextView   tv_name;
        TextView    tv_num;




    }

}
