package com.example.q.project1ver3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class winnerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner);
        Button btnBack = (Button) findViewById(R.id.exitgame);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                winnerActivity.this.finish();
            }
        });
        Button replay = (Button) findViewById(R.id.replaygame);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(winnerActivity.this, worldcupActivity.class);
                startActivity(intent);
                winnerActivity.this.finish();
            }
        });
        ImageView imageView = (ImageView)findViewById(R.id.showwinner);
        int id = getIntent().getIntExtra("winner", 0);
        imageView.setImageResource(id);
    }

}
