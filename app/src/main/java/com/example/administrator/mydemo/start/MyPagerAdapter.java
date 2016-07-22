package com.example.administrator.mydemo.start;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MyPagerAdapter extends PagerAdapter {
    ArrayList<View> mlist;

    public MyPagerAdapter(ArrayList<View> list){
        mlist=list;

    }

    @Override
    public int getCount() {
         //返回滑动页面数
        return mlist.size();

    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //指定位置页面给 container(容器)

        container.addView(mlist.get(position),0);

        return mlist.get(position);
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
         //判断 当前页面与位置页面
        return view==object;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //销毁页面
        container.removeView(mlist.get(position));
    }
}
