package com.example.sickl.galmorea;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalmoreasLevel_1_3 extends AppCompatActivity {

    @BindView(R.id.player) ImageView player;
    @BindView(R.id.reset_btn) Button reset;
    @BindView(R.id.next_level_btn) Button nextLevel;
    EndDrgLsntr endDrgLsntr;
    StrtDrgLsntr strtDrgLsntr;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galmoreas_level_1_3);
        ButterKnife.bind(this);

        extractData();

        strtDrgLsntr = new StrtDrgLsntr();
        endDrgLsntr = new EndDrgLsntr();


        findViewById(R.id.right_arrow).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.up_arrow).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.down_arrow).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.left_arrow).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.arrow_box0).setOnDragListener(endDrgLsntr);
        findViewById(R.id.arrow_box1).setOnDragListener(endDrgLsntr);
        findViewById(R.id.arrow_box2).setOnDragListener(endDrgLsntr);
        findViewById(R.id.arrow_box3).setOnDragListener(endDrgLsntr);
        findViewById(R.id.arrow_box4).setOnDragListener(endDrgLsntr);

        Log.e("Score Total", Integer.toString(score));

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNewActivity = new Intent(v.getContext(), GalmoreasLevel_1_3.class);
                startActivity(startNewActivity);
            }
        });

    }

    public void nextLevel(View view) {

        if(score == 40) {

            Intent intent = GalmoreasLevel_1_3.makeIntent(GalmoreasLevel_1_3.this, score);
            startActivity(intent);

        }

    }

    private void extractData(){
        Intent intent = getIntent();

        score = intent.getIntExtra("Score", score);
    }


    public static Intent makeIntent(Context context, int score){
        Intent intent = new Intent(context, GalmoreasLevel_1_3.class);
        intent.putExtra("Score", score);

        return intent;
    }

    private class StrtDrgLsntr implements View.OnLongClickListener {


        @Override
        public boolean onLongClick(View v) {
            WithShadow withShadow = new WithShadow(v);

            if (v.getId() == R.id.right_arrow) {
                ClipData data = ClipData.newPlainText("right", "rightArrow");
                v.startDrag(data, withShadow, v, 0);

                Log.e("Output", data.toString());

            }


            if (v.getId() == R.id.down_arrow) {
                ClipData data = ClipData.newPlainText("down", "downArrow");
                v.startDrag(data, withShadow, v, 0);

                Log.e("Output", data.toString());

            }

            if (v.getId() == R.id.left_arrow) {
                ClipData data = ClipData.newPlainText("left", "leftArrow");
                v.startDrag(data, withShadow, v, 0);

                Log.e("Output", data.toString());

            }

            if (v.getId() == R.id.up_arrow) {
                ClipData data = ClipData.newPlainText("up", "upArrow");
                v.startDrag(data, withShadow, v, 0);

                Log.e("Output", data.toString());

            }


            return false;
        }
    }

    private class EndDrgLsntr implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            if (event.getAction() == event.ACTION_DROP) {
                v.setForeground(((Button) event.getLocalState()).getBackground());

                if (v.getId() == R.id.arrow_box0) {
                    ClipData data1 = event.getClipData();
                    String dataS = (String) data1.getItemAt(0).getText();

                    if (dataS.equals("downArrow")) {

                        moveAnimationJump();

                        Log.e("dataS output", data1.toString());

                        score = score + 5;

                        Log.e("Score Shoud Be 5   ", Integer.toString(score));

                    }
                }

                if (v.getId() == R.id.arrow_box1) {
                    ClipData data1 = event.getClipData();
                    String dataS = (String) data1.getItemAt(0).getText();

                    if (dataS.equals("downArrow")) {

                        moveAnimationJump2();

                        Log.e("dataS output", data1.toString());

                        score = score + 5;

                        Log.e("Score Shoud Be 5   ", Integer.toString(score));

                    }
                }

                if (v.getId() == R.id.arrow_box2) {
                    ClipData data1 = event.getClipData();
                    String dataS = (String) data1.getItemAt(0).getText();

                    if (dataS.equals("downArrow")) {

                       moveAnimationJump3();

                        Log.e("dataS output", data1.toString());

                        score = score + 5;

                    }
                }

                if (v.getId() == R.id.arrow_box3) {
                    ClipData data1 = event.getClipData();
                    String dataS = (String) data1.getItemAt(0).getText();

                    if (dataS.equals("rightArrow")) {

                        moveAnimationRight();

                        Log.e("dataS output", data1.toString());

                        score = score + 5;

                    }

                }

                if (v.getId() == R.id.arrow_box4) {
                    ClipData data1 = event.getClipData();
                    String dataS = (String) data1.getItemAt(0).getText();

                    if (dataS.equals("upArrow")) {

                        moveAnimationJumpUp();


                        Log.e("dataS output", data1.toString());

                        score = score + 5;

                        Log.e("Score Sould Be 20    ", Integer.toString(score));

                    }

                }


            }

            score = score + 0;

            return true;
        }

    }

    private class WithShadow extends View.DragShadowBuilder {
        public WithShadow(View v) {
            super(v);
        }

        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
            super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            super.onDrawShadow(canvas);
        }
    }

    public void moveAnimationJump() {

        Animation img = new TranslateAnimation(Animation.ABSOLUTE, 300, Animation.ABSOLUTE, 200);
        img.setDuration(5500);
        img.setFillAfter(true);
        player.startAnimation(img);

        player.setImageResource(R.drawable.player_jump);
        final AnimationDrawable playerIdle = (AnimationDrawable) player.getDrawable();
        playerIdle.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playerIdle.stop();
            }
        }, 5550);

    }
    public void moveAnimationJump2() {

        Animation img = new TranslateAnimation(300, 600, 250, 375);
        img.setDuration(5500);
        img.setFillAfter(true);
        player.startAnimation(img);

        player.setImageResource(R.drawable.player_jump);
        final AnimationDrawable playerIdle = (AnimationDrawable) player.getDrawable();
        playerIdle.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playerIdle.stop();
            }
        }, 5550);

    }

    public void moveAnimationJump3() {

        Animation img = new TranslateAnimation(600, 1000, 375, 560);
        img.setDuration(5500);
        img.setFillAfter(true);
        player.startAnimation(img);

        player.setImageResource(R.drawable.player_jump);
        final AnimationDrawable playerIdle = (AnimationDrawable) player.getDrawable();
        playerIdle.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playerIdle.stop();
            }
        }, 5550);

    }


    public void moveAnimationRight() {

        Animation img = new TranslateAnimation(1000, 1800, 585, 585);
        img.setDuration(3000);
        img.setFillAfter(true);
        player.startAnimation(img);

        player.setImageResource(R.drawable.player_move);
        final AnimationDrawable playerIdle = (AnimationDrawable) player.getDrawable();
        playerIdle.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playerIdle.stop();
            }
        }, 3050);

    }

    public void moveAnimationJumpUp() {

        Animation img = new TranslateAnimation(1800, 2200, 585, -20);
        img.setDuration(5500);
        img.setFillAfter(true);
        player.startAnimation(img);

        player.setImageResource(R.drawable.player_jump);
        final AnimationDrawable playerIdle = (AnimationDrawable) player.getDrawable();
        playerIdle.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playerIdle.stop();
            }
        }, 5550);

    }

}


