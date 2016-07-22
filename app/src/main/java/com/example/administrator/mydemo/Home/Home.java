package com.example.administrator.mydemo.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.mydemo.DB.TelmsgActivity;
import com.example.administrator.mydemo.R;
import com.example.administrator.mydemo.exe.PhoneTest;
import com.example.administrator.mydemo.phone.PhoneActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity {


    GridView home_gv;
    int[]  draw={
            R.drawable.icon_rocket,R.drawable.icon_softmgr,R.drawable.icon_phonemgr,
            R.drawable.icon_telmgr,R.drawable.icon_filemgr,R.drawable.icon_sdclean
    };
    String[] str={"手机加速","软件管理","手机检测","通讯大全","文件管理","垃圾清理"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home_gv= (GridView) findViewById(R.id.home_gv);
        List<Map<String ,Object>>  items= new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("imageItem", draw[i]);//添加图像资源的ID
            item.put("textItem", str[i]);//按序号添加ItemText
            items.add(item);

        }
       // SimpleAdapter adapter=new SimpleAdapter(this,items,R.layout.home_grid,new String[]{"imageTtem","textItem"},new int[]{R.id.image_item, R.id.text_item});

           // home_gv.setAdapter(adapter);
        //  home_gv.setAdapter(new HomeAdapter(this,draw,str));
        home_gv.setAdapter(new HomeAdapter(this,draw,str));


        home_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =null;

                switch (position){


                    case 1:intent=new Intent(Home.this, PhoneTest.class);
                        startActivity(intent);

                        break;
                    case 2:  intent=new Intent(Home.this, PhoneActivity.class);
                        startActivity(intent);

                        break;
                    case 3:intent=new Intent(Home.this, TelmsgActivity.class);
                        startActivity(intent);

                        break;
                    case 4:
                        Toast.makeText(Home.this," 点击4",Toast.LENGTH_SHORT).show();
                        break;

                }


            }
        });


    }
    }