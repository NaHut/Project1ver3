package com.example.q.project1ver3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;

public class worldcupActivity extends Activity implements Animation.AnimationListener{

    ArrayList<Integer> mIdList16 = new ArrayList<>();

    public TextView text;

    public ImageView leftbtn;
    public ImageView rightbtn;

    public Animation animFadeOut;

    private int mCnt = 0;

    private final Integer image_ids[] = {
            R.drawable.dahye,
            R.drawable.guhan,
            R.drawable.hosoung,
            R.drawable.howook,
            R.drawable.jaeyoung,
            R.drawable.seongwong,
            R.drawable.yoontak,
            R.drawable.yoontak2,
            R.drawable.guhan2,
            R.drawable.heongjun,
            R.drawable.hosoung2,
            R.drawable.soyoung,
            R.drawable.soyoung2,
            R.drawable.heongjun1,
            R.drawable.heongjun2,
            R.drawable.heongjun3,
    };

    private final int left = 0;
    private final int right = 1;
    private int whoWinner;

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
        leftbtn = (ImageView) findViewById(R.id.profimg1);
        rightbtn = (ImageView) findViewById(R.id.profimg2);
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
                fadeOutImage(rightbtn,left);
            }
        });
        // RIGHT BUTTON
        rightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               fadeOutImage(leftbtn,right);
            }
        });
    }
    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation

        // check for fade out animation
        if (animation == animFadeOut) {
            Toast.makeText(getApplicationContext(), "Animation Stopped",
                    Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }
    private void fadeOutImage(final ImageView img, int who)
    {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);
        whoWinner = who;

        fadeOut.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                if(whoWinner == left) {
                    rightbtn.setVisibility(View.GONE);
                }
                else{
                    leftbtn.setVisibility(View.GONE);
                }

            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {
                moveImage(whoWinner);
            }
        });

        img.startAnimation(fadeOut);
    }

    private void moveImage(int direction){
        Animation move = null;
        //Translate left to right
        if(direction == left) {
            move = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 0.6f,
                    Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 0);
            move.setDuration(3000);
            move.setFillAfter(true);
            move.setFillEnabled(true);

            leftbtn.startAnimation(move);
        }
        //Translate right to left
        else{
            move = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, -0.6f,
                    Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 0);
            move.setDuration(3000);
            move.setFillAfter(true);
            move.setFillEnabled(true);

            rightbtn.startAnimation(move);
        }
        move.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(whoWinner == left){
//                    leftbtn.clearAnimation();
                    mIdList16.remove(mCnt + 1);
                    mCnt = setmCnt(mCnt);
                    if (gotoWinner(mIdList16) == 1) {
                        leftbtn.setImageResource(mIdList16.get(mCnt));
                        rightbtn.setImageResource(mIdList16.get(mCnt + 1));
                    }
                    rightbtn.setVisibility(View.VISIBLE);
                    leftbtn.clearAnimation();

                }
                else{
//                    rightbtn.clearAnimation();
                    mIdList16.remove(mCnt);
                    mCnt = setmCnt(mCnt);
                    if (gotoWinner(mIdList16) == 1 ) {
                        leftbtn.setImageResource(mIdList16.get(mCnt));
                        rightbtn.setImageResource(mIdList16.get(mCnt + 1));
                    }
                    leftbtn.setVisibility(View.VISIBLE);
                    rightbtn.clearAnimation();
                }
            }
        });
    }
}