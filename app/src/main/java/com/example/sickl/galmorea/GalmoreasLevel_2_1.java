package com.example.sickl.galmorea;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
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

public class GalmoreasLevel_2_1 extends AppCompatActivity {

    @BindView(R.id.player)
    ImageView player;
    @BindView(R.id.reset_btn)
    Button reset;
    @BindView(R.id.next_level_btn) Button nextLevel;
    EndDrgLsntr endDrgLsntr;
    StrtDrgLsntr strtDrgLsntr;
    int score = 0;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galmoreas_level_2_1);

        ButterKnife.bind(this);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.background_ambience);
        mediaPlayer.start();

        strtDrgLsntr = new StrtDrgLsntr();
        endDrgLsntr = new EndDrgLsntr();


        findViewById(R.id.right_arrow).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.arrow_box1).setOnDragListener(endDrgLsntr);
        findViewById(R.id.up_arrow).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.down_arrow).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.left_arrow).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.arrow_box2).setOnDragListener(endDrgLsntr);
        findViewById(R.id.arrow_box3).setOnDragListener(endDrgLsntr);
        findViewById(R.id.loop_arrow).setOnLongClickListener(strtDrgLsntr);

        Log.e("Score Should be 0    ", Integer.toString(score));

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNewActivity = new Intent(v.getContext(), GalmoreasLevel_2_1.class);
                startActivity(startNewActivity);
            }
        });



    }

    public void nextLevel(View view) {

        if(score == 55) {

            Intent intent = GalmoreasLevel_1_2.makeIntent(GalmoreasLevel_2_1.this, score);
            startActivity(intent);

        }

    }

    private void extractData(){
        Intent intent = getIntent();

        score = intent.getIntExtra("Score", score);
    }


    public static Intent makeIntent(Context context, int score){
        Intent intent = new Intent(context, GalmoreasLevel_2_1.class);
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

            if (v.getId() == R.id.right_arrow) {
                ClipData data = ClipData.newPlainText("right", "rightArrow");
                v.startDrag(data, withShadow, v, 0);

                Log.e("Output", data.toString());

            }

            if (v.getId() == R.id.up_arrow) {
                ClipData data = ClipData.newPlainText("up", "upArrow");
                v.startDrag(data, withShadow, v, 0);

                Log.e("Output", data.toString());

            }

            if (v.getId() == R.id.loop_arrow) {
                ClipData data = ClipData.newPlainText("loop", "loopArrow");
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

                if (v.getId() == R.id.arrow_box1) {
                    ClipData data1 = event.getClipData();
                    String dataS = (String) data1.getItemAt(0).getText();

                    if (dataS.equals("upArrow")) {

                        moveAnimationJumpUp();

                        Log.e("dataS output", data1.toString());

                        score = score + 5;

                        Log.e("Score Shoud Be 5   ", Integer.toString(score));

                    }
                }

                if (v.getId() == R.id.arrow_box2) {
                    ClipData data1 = event.getClipData();
                    String dataS = (String) data1.getItemAt(0).getText();

                    if (dataS.equals("loopArrow")) {

                        moveAnimationJump();
                        moveAnimationJump();
                        moveAnimationJump();

                        Log.e("dataS output", data1.toString());

                        score = score + 5;

                    }
                }

                if (v.getId() == R.id.arrow_box3) {
                    ClipData data1 = event.getClipData();
                    String dataS = (String) data1.getItemAt(0).getText();

                    if (dataS.equals("rightArrow")) {

                        moveAnimationRightlong();

                        Log.e("dataS output", data1.toString());

                        score = score + 5;

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

        Animation img = new TranslateAnimation(500, 700, Animation.ABSOLUTE, 480);
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

    public void moveAnimationRightlong() {

        Animation img = new TranslateAnimation(700, 1650, 550, 550);
        img.setDuration(4000);
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
        }, 4050);

    }

    public void moveAnimationJumpUp() {

        Animation img = new TranslateAnimation(1650, 1900, 550, 50);
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
