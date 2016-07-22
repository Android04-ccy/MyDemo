package com.example.administrator.mydemo.DB;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mydemo.R;

import java.util.ArrayList;

public class TellistActivity extends AppCompatActivity {
        ListView listview;

    ArrayList<TelnumberInfo> mlist ;
         TextView textView;
         TellistAdapter madpater;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tellist);
        listview= (ListView) findViewById(R.id.lv_tellist);
        textView= (TextView) findViewById(R.id.toolbar_tv);

        final Intent intent=this.getIntent();

          textView.setText(intent.getExtras().getString("name"));

        for (int i = 0; i <10 ; i++) {

                    if((int)intent.getExtras().get("idx")==i){

                        try {
                            mlist= DBReader.readTeldbTable(i);
                               madpater=  new TellistAdapter(this,mlist);
                            listview.setAdapter(madpater);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                             TelnumberInfo telnumberInfo= (TelnumberInfo) madpater.getItem(position);

                           String number=telnumberInfo.number.trim();

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));



                startActivity(intent);
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();



    }
}
