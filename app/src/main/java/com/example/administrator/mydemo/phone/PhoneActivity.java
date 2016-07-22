package com.example.administrator.mydemo.phone;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.mydemo.R;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PhoneActivity extends AppCompatActivity {
   TextView tv_battery;
    ProgressBar  pb_battery;
    ProgressBar  pb_loading;
    int  maxBattery;
    int  currentBattery;
    int  temperatureBattery;
      TextView info_tv;
            //图签数组
       int[]  draw={R.drawable.icon_device,R.drawable.icon_ram,R.drawable.icon_cpu,R.drawable.icon_c
       ,R.drawable.icon_jd};
         //文本数组1
       String[]  str1=new String[draw.length];
        //文本数组2
       String[] str2=new String[draw.length];
      ListView mlistview;
      ArrayList<InfoPhone> mlist;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

       info_tv= (TextView) findViewById(R.id.info_tv);
        mlistview= (ListView) findViewById(R.id.info_lv);
        tv_battery = (TextView) findViewById(R.id.tv_battery);
        pb_battery = (ProgressBar) findViewById(R.id.pb_battery);
        pb_loading = (ProgressBar) findViewById(R.id.progressBar);


        mlist=new ArrayList<>();


         //设备系统基带版本 **
        String version = Build.getRadioVersion();
           str1[4]="基带版本: "+version;
        //设备系统版本号 **
        String release = Build.VERSION.RELEASE;
            str2[0]="系统版本: "+release;

        //设备CPU类型名称
        String[] supportedAbis1 = Build.SUPPORTED_ABIS;
        String string =new String();
        for (int i = 0; i < supportedAbis1.length; i++) {

            string=string+supportedAbis1[i];
        }

        str1[2]="CPU名称:" +string;

        String model = Build.MODEL;
          str1[0]="设备名称: "+model;
           memory();
          if (getIsRooted()){
              str2[4]="是否ROOT: 是";
          }else {
              str2[4]="是否ROOT: 否";
          }

        str1[3]="屏幕分辨率: "+display();
       // str1[3]="屏幕分辨率: "+getDisplayMetrics();

        str2[2]="CPU数量: "+getsumCPU();
        str2[3]="相机分辨率:"+ getCamera();

        //电量监视实例化对象
        BatteryReceiver b = new BatteryReceiver();

        IntentFilter filter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
          //注册接收
          registerReceiver(b,filter);

          //将数据传入集合当中
        for (int i = 0; i < draw.length; i++) {

            InfoPhone info=new InfoPhone(str1[i],str2[i],draw[i]);
               mlist.add(info);
        }

        mlistview.setAdapter(new InfoAdapter(this,mlist));


    }



    public void buildshow() {
        //设备名称 **
        String brand = Build.BRAND;

        //指令集   **
      //  String[] supportedAbis = Build.SUPPORTED_ABIS;



        //当前版本号
        String codename = Build.VERSION.CODENAME;
        //版本字符串 **
        String release = Build.VERSION.RELEASE;


        String  string=new String();
//        for (int i = 0; i < supportedAbis.length; i++) {
//
//            string=string+supportedAbis[i];
//
//        }



    }

    public String display() {
        //获取屏幕分辨率
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width=dm.widthPixels;
        float hetght=dm.heightPixels;

        Log.d(" DisplayMetrics", ""+width)  ;
        Log.d(" Display", ""+hetght)  ;
        Log.d(" Display", ""+dm.density)  ;
        return width+"*"+hetght;



    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void memory(){

        ActivityManager am= (ActivityManager) getSystemService(ACTIVITY_SERVICE);
         ActivityManager.MemoryInfo info=new ActivityManager.MemoryInfo();
         am.getMemoryInfo(info);
        long availMem = info.availMem;
        long totalMem = info.totalMem;
             Log.d("全部运行内存: ",totalMem+"");
             Log.d("剩余运行内存: ", availMem+"");
        String totalSize = Formatter.formatFileSize(getApplicationContext(),totalMem );
        String availableSize = Formatter.formatFileSize(getApplicationContext(),availMem);
          str1[1]="全部运行内存: "+totalSize;
          str2[1]= "剩余运行内存:"+availableSize;

    }

    /**
     * 判断是否ROOT
     * @return
     */
    public  boolean getIsRooted(){
     boolean isRoot=false;

         isRoot=!(!new File("system/bin/su").exists()&&!new File("system/bin/su").exists());

         Log.d("isRoot",""+isRoot);



  return  isRoot;
 }

    public String  getDisplayMetrics(){
/*获取屏幕分辨率
     * @return
     */
      WindowManager windowManager = getWindowManager();
      Display display = windowManager.getDefaultDisplay();
      int screenWidth = screenWidth = display.getWidth();
      int screenHeight = screenHeight = display.getHeight();
       return screenWidth+"*"+ screenHeight;


  }

    public  String getCamera(){
      Camera camera= Camera.open();
      Camera.Parameters parameters = camera.getParameters();
      List<Camera.Size> sizes= parameters.getSupportedPictureSizes();
      Camera.Size size=null;

      for (Camera.Size s : sizes) {
          if (size==null){

               size=s;
          }else if (size.height*size.width<s.height*s.width){

              size=s;

          }
        Log.d("size.height",size.height+"");
        Log.d("size.width",size.width+"");

      }
      return size.height+"*"+size.width   ;
  }

    public void returnHome(View view) {
        finish();

    }

    class CpuFilter implements FileFilter {
        public boolean accept(File pathname){
            if (Pattern.matches("cpu[0-9]",pathname.getName())){

                return true;

            }
            return false;
        }

    }


    public  String getsumCPU(){
        //获取CPU数量
    try {
        File dir = new File("/sys/devices/system/cpu/");
        File[] files = dir.listFiles(new CpuFilter());
        return   ""+files.length;
    } catch (Exception e) {
        e.printStackTrace();
        return ""+  1;
    }

}

         //电量监视
    class BatteryReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED));
            Bundle build=intent.getExtras();
            maxBattery= (int) build.get(BatteryManager.EXTRA_SCALE);
            currentBattery= (int) build.get(BatteryManager.EXTRA_LEVEL);
            temperatureBattery = (int) build.get(BatteryManager.EXTRA_TEMPERATURE);

            Log.d("最大电量",maxBattery+"");
            Log.d("当前电量",currentBattery+"");
            Log.d("当前温度",temperatureBattery+"");

            pb_battery.setMax(maxBattery);
            pb_battery.setProgress(currentBattery);
            int current100 = currentBattery * 100 / maxBattery;
            tv_battery.setText(current100 + "%");//TextView 显示电量

        }
    }



}
