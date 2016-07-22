package com.example.administrator.mydemo.DB;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/7/13.
 */
public class AssetDBManager {

    public static final String TAG="AssetDBManager";

    public static void  copyAssetFileToFile(Context context, String path, File toFile){

          InputStream inputStream=null;
          BufferedInputStream bufferedInputStream=null;
          BufferedOutputStream bufferedOutputStream=null;
          try {
              inputStream=context.getAssets().open(path);
              Log.d(TAG, "copyAssetFileToFile: 开始复制");
              bufferedInputStream=new BufferedInputStream(inputStream);
              bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(toFile,false));
              int len;

              byte[] bytes=new byte[2*1024];
              while ((len=bufferedInputStream.read(bytes))!=-1){
                   bufferedOutputStream.write(bytes,0,len);
                  Log.d(TAG, "copyAssetFileToFile: "+len);
              }
               bufferedOutputStream.flush();
          } catch (IOException e) {
              e.printStackTrace();
          } finally {
              try {
                  bufferedInputStream.close();
                  bufferedOutputStream.close();
                  inputStream.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }

          }

      }

}
