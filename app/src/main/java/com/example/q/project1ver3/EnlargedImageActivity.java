package com.example.q.project1ver3;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class EnlargedImageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enlarge_image);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnlargedImageActivity.this.finish();
            }
        });
        ImageView imageView = (ImageView)findViewById(R.id.imageView2);
        int id = getIntent().getIntExtra("picture_id", 0);
        imageView.setImageResource(id);
    }
}