//////////////////////////
//     Galmoreas        //
//   Earl Platt III     //
//     © 2018-2019      //
//////////////////////////

package com.example.sickl.galmorea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalmoreasPasswordReset extends AppCompatActivity {

    @BindView(R.id.email_box)
    EditText emailBox;
    @BindView(R.id.rst_btn)
    Button reSetBtn;
    @BindView(R.id.bck_button)
    TextView bckBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galmoreas_password_reset);
        ButterKnife.bind(this);

        //Returns User to Login Page When Clicking Back Button
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GalmoreasPasswordReset.this, GalmoreasLoginPage.class));
                finish();
            }
        });
    }
}
