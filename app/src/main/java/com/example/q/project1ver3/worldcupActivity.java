package com.example.q.project1ver3;
import java.util.Collections;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.function.ToDoubleBiFunction;

public class worldcupActivity extends Activity{

    ArrayList<Integer> mIdList16 = new ArrayList<>();
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
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img11,
            R.drawable.img12,
            R.drawable.img13,
            R.drawable.img14,
            R.drawable.img15,
            R.drawable.img16,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i = 0; i < 16;i++) {
            mIdList16.add(image_ids[i]);
        }

        Collections.shuffle(mIdList16);
        setContentView(R.layout.worldcup);


        Button btnBack = (Button) findViewById(R.id.backtotab3);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                worldcupActivity.this.finish();
            }
        });
        final TextView text = (TextView) findViewById(R.id.status);
        final ImageView leftbtn = (ImageView) findViewById(R.id.profimg1);
        final ImageView rightbtn = (ImageView) findViewById(R.id.profimg2);
        text.setText(String.format("Round 16 - %d out of 4 rounds", 1));
        leftbtn.setImageResource(mIdList16.get(0));
        rightbtn.setImageResource(mIdList16.get(1));

        leftbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (mCnt < 8) {
                    mIdList8.add(mIdList16.get(mCnt * 2));
                    mCnt++;
                    text.setText(String.format("Round 16 - %d out of 8 rounds", mCnt + 1));
                    if (mCnt == 8) {
                        text.setText("Round 8 - 1 out of 4 rounds");
                        leftbtn.setImageResource(mIdList8.get(0));
                        rightbtn.setImageResource(mIdList8.get(1));
                    }
                    else {
                        leftbtn.setImageResource(mIdList16.get(mCnt * 2));
                        rightbtn.setImageResource(mIdList16.get(mCnt * 2 + 1));
                    }
                }
                if (mCnt < 12) {
                    mIdList4.add(mIdList8.get((mCnt - 8) * 2));
                    mCnt++;
                    text.setText(String.format("Round 8 - %d out of 4 rounds", mCnt - 7));
                    if (mCnt == 12)
                    {
                        text.setText(String.format("Round 4 - %d out of 2 rounds", 1));
                        leftbtn.setImageResource(mIdList4.get(0));
                        rightbtn.setImageResource(mIdList4.get(1));
                    }
                    else {
                        leftbtn.setImageResource(mIdList8.get((mCnt - 7) * 2));
                        rightbtn.setImageResource(mIdList8.get((mCnt - 7) * 2 + 1));
                    }
                }
                else if (mCnt == 14) {
                    Intent intent = new Intent(worldcupActivity.this, winnerActivity.class);
                    intent.putExtra("winner", mIdList2.get(0));
                    //worldcupActivity.this.startActivity(intent);
                    startActivity(intent);
                    worldcupActivity.this.finish();  }
                else if (mCnt < 14) {
                    mIdList2.add(mIdList4.get((mCnt - 12) * 2));
                    mCnt++;
                    if (mCnt == 14)
                    {
                        text.setText("Round 2 - FINAL ROUND!");
                        leftbtn.setImageResource(mIdList2.get(0));
                        rightbtn.setImageResource(mIdList2.get(1));
                    }
                    else {
                        text.setText(String.format("Round 4 - %d out of 2 rounds", mCnt - 3 ));
                        leftbtn.setImageResource(mIdList4.get((mCnt - 12) * 2));
                        rightbtn.setImageResource(mIdList4.get((mCnt - 12) * 2 + 1));
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
                    text.setText(String.format("Round 8 - %d out of 4 rounds", mCnt + 1));
                    if (mCnt == 4)
                    {
                        text.setText(String.format("Round 4 - %d out of 2 rounds", 1));
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
                        text.setText("Round 2 - FINAL ROUND!");
                        leftbtn.setImageResource(mIdList2.get(0));
                        rightbtn.setImageResource(mIdList2.get(1));
                    }
                    else {
                        text.setText(String.format("Round 4 - %d out of 2 rounds", mCnt - 3 ));
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