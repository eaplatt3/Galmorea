package com.example.sickl.galmorea;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalmoreasLevel_1_2 extends AppCompatActivity {

    @BindView(R.id.player) ImageView player;
    @BindView(R.id.reset_btn) Button reset;
    @BindView(R.id.next_level_btn) Button nextLevel;
    @BindView(R.id.exit_btn) Button exit;
    EndDrgLsntr endDrgLsntr;
    StrtDrgLsntr strtDrgLsntr;
    int score;
    private MediaPlayer mediaPlayer;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galmoreas_level_1_2);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.background_ambience);
        mediaPlayer.start();

        extractData();

        strtDrgLsntr = new StrtDrgLsntr();
        endDrgLsntr = new EndDrgLsntr();


        findViewById(R.id.right_arrow).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.arrow_box1).setOnDragListener(endDrgLsntr);
        findViewById(R.id.up_arrow).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.down_arrow).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.left_arrow).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.arrow_box2).setOnDragListener(endDrgLsntr);
        findViewById(R.id.arrow_box3).setOnDragListener(endDrgLsntr);
        findViewById(R.id.arrow_box4).setOnDragListener(endDrgLsntr);

        Log.e("Score Total", Integer.toString(score));

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNewActivity = new Intent(v.getContext(), GalmoreasLevel_1_2.class);
                startActivity(startNewActivity);
            }
        });

        //Checks Users State
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(GalmoreasLevel_1_2.this, GalmoreasLoginPage.class));
                    finish();
                }
            }
        };

        //Logs user out of App
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }

    public void nextLevel(View view) {

        if(score == 40) {

            Intent intent = GalmoreasLevel_1_3.makeIntent(GalmoreasLevel_1_2.this, score);
            startActivity(intent);

        }

    }

    private void extractData(){
        Intent intent = getIntent();

        score = intent.getIntExtra("Score", score);
    }


    public static Intent makeIntent(Context context, int score){
        Intent intent = new Intent(context, GalmoreasLevel_1_2.class);
        intent.putExtra("Score", score);

        return intent;
    }

    private class StrtDrgLsntr implements View.OnLongClickListener {


        @Override
        public boolean onLongClick(View v) {
            WithShadow withShadow = new WithShadow(v);

            if (v.getId() == R.id.up_arrow) {
                ClipData data = ClipData.newPlainText("up", "upArrow");
                v.startDrag(data, withShadow, v, 0);

                Log.e("Output", data.toString());

            }


            if (v.getId() == R.id.down_arrow) {
                ClipData data = ClipData.newPlainText("down", "downArrow");
                v.startDrag(data, withShadow, v, 0);

                Log.e("Output", data.toString());

            }

            if (v.getId() == R.id.up_arrow) {
                ClipData data = ClipData.newPlainText("up", "upArrow");
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

                    if (dataS.equals("downArrow")) {

                        moveAnimationJump();

                        Log.e("dataS output", data1.toString());

                        score = score + 5;

                    }
                }

                if (v.getId() == R.id.arrow_box3) {
                    ClipData data1 = event.getClipData();
                    String dataS = (String) data1.getItemAt(0).getText();

                    if (dataS.equals("upArrow")) {

                        moveAnimationJumpUp2();

                        Log.e("dataS output", data1.toString());

                        score = score + 5;

                    }

                }

                if (v.getId() == R.id.arrow_box4) {
                    ClipData data1 = event.getClipData();
                    String dataS = (String) data1.getItemAt(0).getText();

                    if (dataS.equals("upArrow")) {

                        moveAnimationJumpUp3();


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

        Animation img = new TranslateAnimation(600, 1000, -530, 0);
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


    public void moveAnimationJumpUp() {

        Animation img = new TranslateAnimation(Animation.ABSOLUTE, 600, Animation.ABSOLUTE, -545);
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

           public void moveAnimationJumpUp2() {

            Animation img = new TranslateAnimation(1000, 1400, 0, -175);
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

    public void moveAnimationJumpUp3() {

        Animation img = new TranslateAnimation(1400, 2200, -175, -400);
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

    //Logout Method
    public void signOut() {
        auth.signOut();
        startActivity(new Intent(GalmoreasLevel_1_2.this, GalmoreasLoginPage.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }


}
