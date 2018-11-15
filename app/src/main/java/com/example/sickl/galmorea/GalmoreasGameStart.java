package com.example.sickl.galmorea;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalmoreasGameStart extends AppCompatActivity {

    @BindView(R.id.playerWaitAnim) ImageView player_wait;
    @BindView(R.id.strtgam) TextView startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galmoreas_game_start);
        ButterKnife.bind(this);

        player_wait.setImageResource(R.drawable.player_wait);
        AnimationDrawable playerIdle = (AnimationDrawable) player_wait.getDrawable();
        playerIdle.start();

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNewActivity = new Intent(v.getContext(), GalmoreasLevel_1_1.class);
                startActivity(startNewActivity);
            }
        });

    }
}
