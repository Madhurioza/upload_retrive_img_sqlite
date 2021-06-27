package com.example.store_retrive_image_sqlite;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    Database d;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        d=new Database(this);
        img=(ImageView)findViewById(R.id.pic);
    }
    public void addImage(View v)
    {
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.drawable.facebook);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte imageInByte[] =stream.toByteArray();
        long t= d.storeimage(imageInByte);
        if(t>0)
        {
            Toast.makeText(MainActivity.this,"Sucess",Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
        }
    }

    public void getImage(View v)
    {
        img.setImageBitmap(d.getImage());
    }
}
