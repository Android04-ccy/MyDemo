package com.example.administrator.mydemo.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mydemo.R;

/**
 * Created by Administrator on 2016/7/18.
 */
public class HomeAdapter extends BaseAdapter {
   Context context;
    int[] dtaw;
    String[] strings;
      public HomeAdapter(Context c, int[] d,String[] string){
          strings=string;
          context=c;
          dtaw=d;

      }


    @Override
    public int getCount() {
        return dtaw.length;
    }

    @Override
    public Object getItem(int position) {
        return dtaw[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        TextView textView;
        ViewHodler mhodler;
              if (convertView==null){
                    convertView= LayoutInflater.from(context).inflate(R.layout.home_grid,null);
                   mhodler=new ViewHodler();
                  mhodler.imageView= (ImageView) convertView.findViewById(R.id.image_item);
                  mhodler.textView= (TextView) convertView.findViewById(R.id.text_item);
                   convertView.setTag(mhodler);
              }else {
                  mhodler= (ViewHodler) convertView.getTag();
                }
                 mhodler.imageView.setImageResource(dtaw[position]);
                  mhodler.textView.setText(strings[position]);


        return convertView;
    }

    class  ViewHodler{
       public ImageView imageView;
       public TextView textView;


    }

}
