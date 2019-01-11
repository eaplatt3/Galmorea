package com.example.sickl.galmorea;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalmoreasLoginPage extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView login_idle;
    @BindView(R.id.reg_link)
    TextView reg_link;
    @BindView(R.id.login)
    EditText login;
    @BindView(R.id.password)
    EditText passwd;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.passwd_reset)
    TextView password_reset;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galmoreas_login_page);
        ButterKnife.bind(this);

        login_idle.setImageResource(R.drawable.login_idle);
        AnimationDrawable playerIdle = (AnimationDrawable) login_idle.getDrawable();
        playerIdle.start();

        //Get Authentication from Firebase
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(GalmoreasLoginPage.this, GalmoreasGameStart.class));
            finish();
        }

        //Get Authentication from Firebase
        auth = FirebaseAuth.getInstance();

        //Link to create new account
        reg_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GalmoreasLoginPage.this, GalmoreasRegPage.class));
            }
        });

        /* TO DO: Password Reset Activity
        /////////////////////////////////
        password_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GalmoreasLoginPage.this, GalmoreasPasswordReset.class));
            }
        });*/

        //Login & Authentication
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login.getText().toString();
                final String password = passwd.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Authenticate User
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(GalmoreasLoginPage.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        passwd.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(GalmoreasLoginPage.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(GalmoreasLoginPage.this, GalmoreasGameStart.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

            }
        });
    }
}