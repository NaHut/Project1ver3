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

    public TextView text;
    private int mCnt = 0;

    private final Integer image_ids[] = {
            R.drawable.dahye,
            R.drawable.guhan,
            R.drawable.hosoung,
            R.drawable.howook,
            R.drawable.jaeyoung,
            R.drawable.seongwong,
            R.drawable.new2,
            R.drawable.yoontak2,
            R.drawable.guhan2,
            R.drawable.eunsong,
            R.drawable.hosoung2,
            R.drawable.soyoung,
            R.drawable.new1,
            R.drawable.heongjun1,
            R.drawable.heongjun4,
            R.drawable.eunsong2,
    };
    public int setmCnt(int mCnt) {
        int result;
        if (mIdList16.size() == 8) {
            text.setText("Round 8");
            return 0;
        }
        else if (mIdList16.size() == 4) {
            text.setText("Round 4");
            return 0;
        }
        else if (mIdList16.size() == 2) {
            text.setText("FINAL!!");
            return 0;
        }
        result = mCnt + 1;
        return result;
    }
    public int gotoWinner(ArrayList<Integer> arr) {
        if (arr.size() == 1) {
            Intent intent = new Intent(worldcupActivity.this, winnerActivity.class);
            intent.putExtra("winner", mIdList16.get(0));
            worldcupActivity.this.startActivity(intent);
            worldcupActivity.this.finish();
            return 0;
        }
        return 1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worldcup);

        text = (TextView) findViewById(R.id.status);
        final ImageView leftbtn = (ImageView) findViewById(R.id.profimg1);
        final ImageView rightbtn = (ImageView) findViewById(R.id.profimg2);
        text.setText("Round 16");

        for (int i = 0; i < 16; i++) {
            mIdList16.add(image_ids[i]);
        }

        //shuffle the element
        Collections.shuffle(mIdList16);
        //GoBack button
        Button btnBack = (Button) findViewById(R.id.backtotab3);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                worldcupActivity.this.finish();
            }
        });

        leftbtn.setImageResource(mIdList16.get(0));
        rightbtn.setImageResource(mIdList16.get(1));

      //  setImage(mIdList16, 0);
        leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIdList16.remove(mCnt + 1);
                mCnt = setmCnt(mCnt);
                if (gotoWinner(mIdList16) == 1) {
                    leftbtn.setImageResource(mIdList16.get(mCnt));
                    rightbtn.setImageResource(mIdList16.get(mCnt + 1));
                }
            }
        });
        // RIGHT BUTTON
        rightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIdList16.remove(mCnt);
                mCnt = setmCnt(mCnt);
                if (gotoWinner(mIdList16) == 1 ) {
                    leftbtn.setImageResource(mIdList16.get(mCnt));
                    rightbtn.setImageResource(mIdList16.get(mCnt + 1));
                }
            }
        });
    }
}