package com.example.administrator.mydemo.exe;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.mydemo.R;

import java.util.ArrayList;
import java.util.List;

public class PhoneTest extends AppCompatActivity {
     ListView mlistview;
    List<PackageInfo> mlist;
     static   ArrayList<PhoneInfo> arrayList;

    PhoneAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        mlistview= (ListView) findViewById(R.id.lv_phone);
        //实例化对象 ,获取 PackageManager
        final PackageManager packageManager=this.getPackageManager();
        //为零时 获取所有应用信息  传入集合中
           mlist= packageManager.getInstalledPackages(0);
         //实例化对象,如果不实例化 提示arrayList 空指针
        arrayList=new ArrayList<>();

               //循环遍历 mlist xinxi
         for (int i = 0; i < mlist.size(); i++) {
                //实例化 单个应用信息
             PackageInfo pak= mlist.get(i);
             //判断是否为非系统预装的应用程序
             if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM)<=0){
                 //获取包名
                String   packageName= pak.packageName.toString();
                 //获取应用名
                String  name=pak.applicationInfo.loadLabel(packageManager).toString();
                 //获取版本号
                String  versionName=pak.versionName;
                 //获取应用图标
               Drawable  ic=pak.applicationInfo.loadIcon(packageManager);
                     //将所有应用信息 实例给PhoneInfo
               PhoneInfo phoneifo=new PhoneInfo(packageName,versionName,ic,name);
               // PhoneInfo phoneifo=new PhoneInfo(packageName,versionName);
                   //添加到集合
                arrayList.add(phoneifo);
            }

        }

                  adapter=new PhoneAdapter(this,arrayList);
                 //设置适配器
                     mlistview.setAdapter(adapter);
              //设置监听
            mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
              //     showDelete(position);
                 Intent intent=new Intent(PhoneTest.this,PhoneDelete.class);
                               //传送点击信息
                           intent.putExtra("package",arrayList.get(position).packageName);
                            intent.putExtra("position",position);
                           startActivity(intent);
                               finish();



            }
             //  //用Dialog,提示卸载
            public void showDelete(final int position) {
                AlertDialog.Builder  dialog=new AlertDialog.Builder(PhoneTest.this);
                dialog.setTitle("卸载应用");
                dialog.setIcon(arrayList.get(position).icon);
                dialog.setCancelable(false);
                dialog.setMessage("是否卸载"+arrayList.get(position).name);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(Intent.ACTION_DELETE);
                        intent.setData(Uri.parse("package:"+arrayList.get(position).packageName));

                       startActivity(intent);

                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }




}
