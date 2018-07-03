package com.example.q.project1ver3;
import java.util.Random;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.function.ToDoubleBiFunction;

public class worldcupActivity extends Activity{

    ArrayList<Integer> mIdList8 = new ArrayList<>();
    ArrayList<Integer> mIdList4 = new ArrayList<>();
    ArrayList<Integer> mIdList2 = new ArrayList<>();

    private int mCnt = 0;

    private final Integer image_ids[] = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO sort elements in image_ids randomly
        for(int i = 0; i < 8; i++) {
            mIdList8.add(image_ids[i]);
        }

        setContentView(R.layout.worldcup);


        Button btnBack = (Button) findViewById(R.id.backtotab3);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                worldcupActivity.this.finish();
            }
        });

        final ImageView leftbtn = (ImageView) findViewById(R.id.profimg1);
        final ImageView rightbtn = (ImageView) findViewById(R.id.profimg2);
        leftbtn.setImageResource(mIdList8.get(0));
        rightbtn.setImageResource(mIdList8.get(1));

        leftbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (mCnt < 4) {
                    mIdList4.add(mIdList8.get(mCnt * 2));
                    mCnt++;
                    if (mCnt == 4)
                    {
                        leftbtn.setImageResource(mIdList4.get(0));
                        rightbtn.setImageResource(mIdList4.get(1));
                    }
                    else {
                        leftbtn.setImageResource(mIdList8.get(mCnt * 2));
                        rightbtn.setImageResource(mIdList8.get(mCnt * 2 + 1));
                    }
                }
                else if (mCnt == 6) {
                    Intent intent = new Intent(worldcupActivity.this, winnerActivity.class);
                    intent.putExtra("winner", mIdList2.get(0));
                    //worldcupActivity.this.startActivity(intent);
                    startActivity(intent);
                    worldcupActivity.this.finish();  }
                else if (mCnt < 6) {
                    mIdList2.add(mIdList4.get((mCnt - 4) * 2));
                    mCnt++;
                    if (mCnt == 6)
                    {
                        leftbtn.setImageResource(mIdList2.get(0));
                        rightbtn.setImageResource(mIdList2.get(1));
                    }
                    else {
                        leftbtn.setImageResource(mIdList4.get((mCnt - 4) * 2));
                        rightbtn.setImageResource(mIdList4.get((mCnt - 4) * 2 + 1));
                    }
                }
            }
        });
        // RIGHT BUTTON
        rightbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mCnt < 4) {
                    mIdList4.add(mIdList8.get(mCnt * 2 + 1));
                    mCnt++;
                    if (mCnt == 4)
                    {
                        leftbtn.setImageResource(mIdList4.get(0));
                        rightbtn.setImageResource(mIdList4.get(1));
                    }
                    else {
                        leftbtn.setImageResource(mIdList8.get(mCnt * 2));
                        rightbtn.setImageResource(mIdList8.get(mCnt * 2 + 1));
                    }
                }
                else if (mCnt < 6) {
                    mIdList2.add(mIdList4.get((mCnt - 4) * 2 + 1));
                    mCnt++;
                    if (mCnt == 6)
                    {
                        leftbtn.setImageResource(mIdList2.get(0));
                        rightbtn.setImageResource(mIdList2.get(1));
                    }
                    else {
                        leftbtn.setImageResource(mIdList4.get((mCnt - 4) * 2));
                        rightbtn.setImageResource(mIdList4.get((mCnt - 4) * 2 + 1));
                    }
                }
                else if (mCnt == 6) {
                    Intent intent = new Intent(worldcupActivity.this, winnerActivity.class);
                    intent.putExtra("winner", mIdList2.get(1));
                    worldcupActivity.this.startActivity(intent);
                    worldcupActivity.this.finish();
                }
            }
        });
    }
}