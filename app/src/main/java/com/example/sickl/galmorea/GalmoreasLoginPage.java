package com.example.sickl.galmorea;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalmoreasLoginPage extends AppCompatActivity {

    @BindView(R.id.login_box) EditText loginTxt;
    @BindView(R.id.password_box) EditText passTxt;
    @BindView(R.id.imageView) ImageView player_idle;
    @BindView(R.id.reg_link) TextView regLink;
    @BindView(R.id.loginBtn) Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galmoreas_login_page);
        ButterKnife.bind(this);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        player_idle.setImageResource(R.drawable.login_idle);
        AnimationDrawable playerIdle = (AnimationDrawable) player_idle.getDrawable();
        playerIdle.start();

        //Action Event to move from Login Page to Register Page
        regLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNewActivity = new Intent(v.getContext(), GalmoreasRegPage.class);
                startActivity(startNewActivity);
            }
        });



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startActivity = new Intent(v.getContext(), GalmoreasGameStart.class);
                startActivity(startActivity);

            }
        });

    }
}
