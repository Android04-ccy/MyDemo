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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mydemo.R;

import java.util.List;

public class PhoneDelete extends AppCompatActivity {
    TextView mtv_name;
    ImageView miv_dele;
    TextView mtv_vn;
    List<PackageInfo> mlist;
        Button bnt_dele;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_delete);
        Intent intent=this.getIntent();
        //获取上一界面 点击的信息
        final int    position = (int) intent.getExtras().get("position");


            mtv_name= (TextView) findViewById(R.id.dele_name) ;
            miv_dele= (ImageView) findViewById(R.id.dele_iv);
            mtv_vn= (TextView) findViewById(R.id.dele_vn);
            bnt_dele= (Button) findViewById(R.id.bnt_dele);
        //获取点击的版本号
         mtv_vn.setText(PhoneTest.arrayList.get(position).versionName);
        //获取点击的应用名字
         mtv_name.setText(PhoneTest.arrayList.get(position).name);
          //获取图片
         miv_dele.setImageDrawable(PhoneTest.arrayList.get(position).icon);
         bnt_dele.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //卸载应用
                 showDelete(position);



             }
         });



        }

    private void delete(int position) {
        int  no=0;
        while (true) {
            PackageManager packageManager=this.getPackageManager();
            mlist=packageManager.getInstalledPackages(0);
            for (int i = 0; i < mlist.size(); i++) {
                PackageInfo pak = mlist.get(i);
                String packname = pak.packageName.toString();
                Log.d("packname",packname);
                Log.d("PhoneTest",PhoneTest.arrayList.get(position).packageName);

//                if (packname.equals(PhoneTest.arrayList.get(position).packageName)) {
//                    no = 1;
//
//                }

            }
//            if (no == 0) {
//
//                Intent intent1 = new Intent(this, PhoneTest.class);
//                startActivity(intent1);
//                finish();
//
//            }
    }
    }


    public void pack(int position) {
        PackageManager packageManager=this.getPackageManager();
        mlist=packageManager.getInstalledPackages(0);
        for (int i = 0; i < mlist.size(); i++) {
            PackageInfo pak=mlist.get(i);

            if (i==position){
                String   packageName= pak.packageName.toString();
                String  name=pak.applicationInfo.loadLabel(packageManager).toString();
                String  versionName=pak.versionName;
                Drawable ic=pak.applicationInfo.loadIcon(packageManager);



            }


        }
    }

    public void showDelete(final int position) {

        //用Dialog,提示卸载
        AlertDialog.Builder  dialog=new AlertDialog.Builder(this);
        dialog.setTitle("卸载应用");
        dialog.setIcon(PhoneTest.arrayList.get(position).icon);
        dialog.setCancelable(false);
        dialog.setMessage("是否卸载"+PhoneTest.arrayList.get(position).name);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:"+PhoneTest.arrayList.get(position).packageName));

                startActivity(intent);
                //delete(position);

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


    public void unloadFinish(View view) {

        Intent intent1 = new Intent(this, PhoneTest.class);
                startActivity(intent1);
                   finish();


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent1 = new Intent(this, PhoneTest.class);
            startActivity(intent1);
            finish();

        }



        return super.onKeyDown(keyCode, event);

    }
}
