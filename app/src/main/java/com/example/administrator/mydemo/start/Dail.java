package com.example.administrator.mydemo.start;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.mydemo.Home.Home;
import com.example.administrator.mydemo.R;

/**
 * Created by Administrator on 2016/7/12.
 */



public class Dail extends AppCompatActivity {
    LinearLayout reL;
    Animation at;
    Button bnt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dail);
            bnt= (Button) findViewById(R.id.bnt);
        reL= (LinearLayout) findViewById(R.id.reL);
           //帧动画 设置资源
          reL.setBackgroundResource(R.drawable.draw);
          // 将rel资源返回帧动画  AnimationDrawable实例化对象
         AnimationDrawable animationDrawable= (AnimationDrawable) reL.getBackground();
        // 帧动画开始
          animationDrawable.start();
        //设置动画监听
          Animation.AnimationListener animationListener=new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                  //动画开始时
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束时
                Intent in=new Intent(Dail.this,Home.class);
                startActivity(in);

                finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                 //动画重复时
            }
        } ;
        at = AnimationUtils.loadAnimation(this, R.anim.anim);
        at.setAnimationListener(animationListener);
        //帧动画无法这是监听 由普通动画代替监听
        bnt.setAnimation(at);

    }
}