package com.example.administrator.mydemo.start;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.mydemo.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/12.
 */
public class PagerViewDemo extends AppCompatActivity {
    public static final String SP_CONFIG = "sp_config";
    public static final String IS_FIRST_RUN = "is_first_run";
    public static final String TAG ="PagerViewDemo";
    ArrayList<View> mlist;
    ViewPager mviewpager;
    int[] pager={R.mipmap.adware_style_applist,R.mipmap.adware_style_banner,R.mipmap.adware_style_creditswall};
    Button bnt_jmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //生成SharedPrefernces文件文件名sp_config
        final SharedPreferences  sharedPreferences= getSharedPreferences(SP_CONFIG,MODE_PRIVATE);
        //读文件 is_first_run 对应的值是否为 true 如果没有 is_first_run的对应值 则赋值 true
        boolean isfirstRun = sharedPreferences.getBoolean(IS_FIRST_RUN,true);

        if (!isfirstRun) {
             // 不是第一
           startActivity(new Intent(this,Dail.class));
            finish();

        }    else {
            //编辑sharedpreferences
            SharedPreferences.Editor editor=sharedPreferences.edit();
            //编辑is_first_run对应值i
            editor.putBoolean(IS_FIRST_RUN,false);
            //保存
            editor.commit();
            boolean isfirstRun1 = sharedPreferences.getBoolean(IS_FIRST_RUN,true);

            setContentView(R.layout.pagerview);

            mviewpager= (ViewPager) findViewById(R.id.viewpager);
            bnt_jmp= (Button) findViewById(R.id.bnt_jmp);
            bnt_jmp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    Intent in=new Intent(PagerViewDemo.this,Dail.class);
                    startActivity(in);
                    finish();
                }
            });

            mlist=new ArrayList<View>();
            for (int i= 0; i <pager.length ; i++) {
                ImageView iv=new ImageView(this);
                iv.setImageResource(pager[i]);
                mlist.add(iv);

            }
            // 设置适配器
            mviewpager.setAdapter(new MyPagerAdapter(mlist));
            //添加滑动画面事件
            mviewpager.addOnPageChangeListener(listener);
            //设置动画效果
            mviewpager.setPageTransformer(true,new ZoomOutPageTransformer());
        }


     }
         //实例化 滑动事件
         ViewPager.OnPageChangeListener listener=new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                 /**
                  *    此方法是在页面滑动的时候调用
                  arg0:当前页面，及你点击滑动的页面
                  arg1:当前页面偏移的百分比
                  arg2:当前页面偏移的像素位置
                  */

             }

             @Override
             public void onPageSelected(int position) {
                 /**
                  *     此方法是页面跳转完成后得到调用
                  arg0:当前选中的页面的position(位置编号)
                  */


                 if (position==mlist.size()-1){
                   bnt_jmp.setVisibility(View.VISIBLE);
                 } else {

                     bnt_jmp.setVisibility(View.GONE)
                     ;
                 }


             }

             @Override
             public void onPageScrollStateChanged(int state) {
                 /**
                  *  此方法是在状态改变的时候调用
                  arg0==1的时候表示正在滑动
                  arg0==2的时候表示滑动完毕了
                  arg0==0的时候表示什么都没做
                  */
             }
         }   ;

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }


}
