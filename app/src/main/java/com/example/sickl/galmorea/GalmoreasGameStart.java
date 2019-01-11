package com.example.sickl.galmorea;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalmoreasGameStart extends AppCompatActivity {

    @BindView(R.id.playerWaitAnim) ImageView player_wait;
    @BindView(R.id.strtgam) TextView startGame;
    @BindView(R.id.logout) TextView logout;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galmoreas_game_start);
        ButterKnife.bind(this);

        player_wait.setImageResource(R.drawable.player_wait);
        AnimationDrawable playerIdle = (AnimationDrawable) player_wait.getDrawable();
        playerIdle.start();

        auth = FirebaseAuth.getInstance();

        //Starts Game
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNewActivity = new Intent(v.getContext(), GalmoreasLevel_1_1.class);
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
                    startActivity(new Intent(GalmoreasGameStart.this, GalmoreasLoginPage.class));
                    finish();
                }
            }
        };

        //Logs user out of App
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }

    //Logout Method
    public void signOut() {
        auth.signOut();
        startActivity(new Intent(GalmoreasGameStart.this, GalmoreasLoginPage.class));
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
