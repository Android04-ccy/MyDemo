package com.example.administrator.mydemo.DB;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class DBReader {

    public static File telFile;

    public static final String TAG = "DBReader";

     static  {
        //默认位置
        String dbFileDir = "data/data/com.example.administrator.mydemo/";
        File fileDir = new File(dbFileDir);
        fileDir.mkdir();//文件目录的创建
        telFile = new File(dbFileDir, "commonnum.db");
       // AssetDBManager.copyAssetFileToFile(,dbFileDir,telFile);
        Log.d(TAG, "telFile dir path: " + dbFileDir);

    }

    //检测是否存在通讯大全DB文件
    public static boolean isExistsTeldbFile() {
        File toFile = DBReader.telFile;
        Log.d(TAG, "isExistsTeldbFile: "+toFile.exists());
        if (!toFile.exists() || toFile.length() <= 0) {

            return false;


        }
        return true;
    }
    /**  读取 telFile  这个数据库的 文件中的 classlist  这个表内的数据
     * @throws Exception */
    public static ArrayList<TelclassInfo> readTeldbClasslist() throws
            Exception {
        ArrayList<TelclassInfo> classListInfos = new
                ArrayList<TelclassInfo>();
//  打开 DB  文件
        SQLiteDatabase db = null;
//  执行查询的 SQL  语句 select * from classlist
        Cursor cursor = null;
        try {

            db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
            cursor = db.rawQuery("SELECT*FROM classlist", null);

            if (cursor.moveToFirst()) {
                do {
                    String name = cursor
                            .getString(cursor
                                    .getColumnIndex("name"));
//idx 为 为 classlist  表中电话的 ID ，根据 idx  值进行指定页面的跳转
                    int idx = cursor.getInt(cursor
                            .getColumnIndex("idx"));

                    TelclassInfo classListInfo = new TelclassInfo(
                            name, idx);
                    classListInfos.add(classListInfo);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
// TODO: handle exception
            throw e;
        }finally{
            try {
                cursor.close();
                db.close();
            } catch (Exception e2) {
// TODO: handle exception
                throw e2;
            }
            Log.d("DBRead", "read teldb classlist end [list size]: " +
                    classListInfos.size());
        }
        return classListInfos;
    }
    public static ArrayList<TelnumberInfo> readTeldbTable(int s) throws
            Exception {
        ArrayList<TelnumberInfo> numberInfos = new ArrayList<TelnumberInfo>();
//idx  为 classlist  表中电话的 ID ，根据 idx  值进行指定页面的跳转
        String sql = "SELECT*FROM table" + s;

        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
//  打开 DB  文件
            db = SQLiteDatabase
                    .openOrCreateDatabase(telFile, null);
//  执行查询的 SQL  语句 select * from table +idx
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor
                            .getString(cursor
                                    .getColumnIndex("name"));
                    String number = cursor
                            .getString(cursor
                                    .getColumnIndex("number"));
                    TelnumberInfo numberInfo = new TelnumberInfo(
                            name, number);
                    numberInfos.add(numberInfo);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
// TODO: handle exception
            throw e;
        }finally{
            try {
                cursor.close();
                db.close();
            } catch (Exception e2) {
// TODO: handle exception
                throw e2;
            }

        }
        return numberInfos;
    }
}
