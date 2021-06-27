package com.example.store_retrive_image_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;

public class Database extends SQLiteOpenHelper {

    public Database (Context context)
    {
        super(context,"mydatabase.db",  null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table imagedata ( image blob);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists imagedata");
        onCreate(db);
    }

    public long storeimage(byte b[])
    {
        ContentValues cv= new ContentValues();
        cv.put("image",b);
        return getWritableDatabase().insert("imagedata",null,cv);
    }

    public Bitmap getImage()
    {
        Bitmap bp = null;
        Cursor c = getReadableDatabase().query("imagedata",null,null,null,null,null,null);
        if(c.moveToNext())
        {
            byte b[]=c.getBlob(0);
            ByteArrayInputStream bi= new ByteArrayInputStream(b);
            bp= BitmapFactory.decodeStream(bi);

        }
         return bp;

    }
}
