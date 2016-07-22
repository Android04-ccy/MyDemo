package com.example.administrator.mydemo.DB;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mydemo.R;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class TelmsgActivity extends AppCompatActivity {

    private ListView listview;
    private ArrayList<TelclassInfo> mlist = new ArrayList<TelclassInfo>();
    ;
    Button bnt;
    MyListAdapter madapter;
    ;
    Toolbar tel_tb;
    TextView tv_show;
    RelativeLayout relativeLayout;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!DBReader.isExistsTeldbFile()) {
            //不存commonnum.db在则创建

            AssetDBManager.copyAssetFileToFile(this, "commonnum.db", DBReader.telFile);
        }
        setContentView(R.layout.activity_telmsg);
        listview = (ListView) findViewById(R.id.listview);
        relativeLayout = (RelativeLayout) findViewById(R.id.tel_layout);
        tel_tb = (Toolbar) findViewById(R.id.tel_tb);
        tel_tb.setLogo(R.drawable.icon_telmgr);
        tel_tb.setTitle("电话博");
        tel_tb.setSubtitle("快捷通话");
        setSupportActionBar(tel_tb);
       // tel_tb.setNavigationIcon(R.drawable.ic_arrows_right);
        tel_tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_show = (TextView) findViewById(R.id.text_show);


        try {
            //导出 数据
            mlist = DBReader.readTeldbClasslist();
            madapter = new MyListAdapter(this, mlist);
            //设置适配器
            listview.setAdapter(madapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //添加ListView监听
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TelclassInfo classinfo = (TelclassInfo) madapter.getItem(position);


                Intent in = new Intent(TelmsgActivity.this, TellistActivity.class);
                in.putExtra("idx", ((TelclassInfo) madapter.getItem(position)).idx);

                in.putExtra("name", classinfo.name);
                startActivity(in);


            }
        });

    }




}



