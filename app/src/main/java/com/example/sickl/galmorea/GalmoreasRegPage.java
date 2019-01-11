package com.example.sickl.galmorea;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalmoreasRegPage extends AppCompatActivity {

    @BindView(R.id.btn_submit)
    Button regBtn;
    @BindView(R.id.firstname_reg)
    EditText firstNameReg;
    @BindView(R.id.lastname_reg)
    EditText lastNameReg;
    @BindView(R.id.dob_reg)
    EditText dobReg;
    @BindView(R.id.email_reg)
    EditText emailReg;
    @BindView(R.id.password_reg)
    EditText passwordReg;
    FirebaseAuth mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galmoreas_reg_page);
        ButterKnife.bind(this);

        mRef = FirebaseAuth.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailReg.getText().toString().trim();
                String password = passwordReg.getText().toString().trim();
                String firstName = firstNameReg.getText().toString().trim();
                String lastName = lastNameReg.getText().toString().trim();
                String dob = dobReg.getText().toString().trim();

                if(TextUtils.isEmpty(firstName)){
                    Toast.makeText(getApplicationContext(), "Enter First Name",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(lastName)){
                    Toast.makeText(getApplicationContext(), "Enter Last Name",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(dob)){
                    Toast.makeText(getApplicationContext(), "Enter Birthday",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter Email Address",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 8) {
                    Toast.makeText(getApplicationContext(),
                            "Password too short, enter minimum 8 characters!",
                                Toast.LENGTH_SHORT).show();
                    return;
                }

                //Creating User
                mRef.createUserWithEmailAndPassword(email, password).addOnCompleteListener(GalmoreasRegPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(GalmoreasRegPage.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(GalmoreasRegPage.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(GalmoreasRegPage.this, GalmoreasLoginPage.class));
                            finish();
                        }

                    }
                });

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
