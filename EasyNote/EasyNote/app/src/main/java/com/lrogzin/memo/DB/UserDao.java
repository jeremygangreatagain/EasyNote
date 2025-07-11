package com.lrogzin.memo.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class UserDao {
    Context context;
    userDBHelper dbHelper;

    public UserDao(Context context) {
        this.context = context;
        dbHelper = new userDBHelper(context, "user.db", null, 1);
    }

    public  void insertUser(String username, String password){
        SQLiteDatabase sqLiteDatabase= dbHelper.getWritableDatabase();
        String sql="insert into user(username,password)values(?,?)";
        sqLiteDatabase.execSQL(sql,new String[]{username,password});
    }

    public  Cursor query(String username, String password){
        SQLiteDatabase sqLiteDatabase= dbHelper.getReadableDatabase();
        String sql="select*from user where username=?and password=?";
        return sqLiteDatabase.rawQuery(sql,new String[]{username,password});
    }

    public int updatePw(String username,String password,String newpassword){
        SQLiteDatabase sqLiteDatabase= dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password",newpassword);
        int i;
        i = sqLiteDatabase.update("user", cv, "username=? and password=?", new String[]{username,password});
        sqLiteDatabase.close();
        return i;
    }

}
