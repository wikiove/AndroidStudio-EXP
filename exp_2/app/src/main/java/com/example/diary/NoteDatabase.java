package com.example.diary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteDatabase extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "notes";
    public static final String CONTENT = "content";
    public static final String ID = "_id";
    public static final String TIME = "time";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String MODE = "mode";

    public NoteDatabase(Context context) {
        super(context, "notes", null, 1);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        //新建表的语句
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "//设置ID为主键
                + CONTENT + " TEXT NOT NULL, "
                + TIME + " TEXT NOT NULL, "
                + TITLE + " TEXT NOT NULL, "
                + AUTHOR + " TEXT NOT NULL, "
                + MODE + " INTEGER DEFAULT 1)");
    }
    /**
     * 检测已有的版本
     *
     * @param db         指向的数据库
     * @param oldVersion 检测已有的版本
     * @param newVersion 新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}