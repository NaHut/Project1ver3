package com.example.q.project1ver3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class EnlargeImage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bitmap bmp;
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        byte[] byteArray = getIntent().getByteArrayExtra("picture_id");
        bmp = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        imageView.setImageBitmap(bmp);
    }
}