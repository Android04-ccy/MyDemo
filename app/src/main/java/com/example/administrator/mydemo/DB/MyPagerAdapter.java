package com.example.administrator.mydemo.DB;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MyPagerAdapter extends PagerAdapter {

    ArrayList<View> mlist;


    public MyPagerAdapter(ArrayList<View> list) {

        mlist= list;
    }



    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

         container.addView(mlist.get(position),0);

        return mlist.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mlist.get(position));

    }



}
