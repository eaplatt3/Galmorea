package com.example.sickl.galmorea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalmoreasRegPage extends AppCompatActivity {

    @BindView(R.id.btn_submit) Button regBtn;
    @BindView(R.id.firstname_reg) EditText firstNameReg;
    @BindView(R.id.lastname_reg) EditText lastNameReg;
    @BindView(R.id.dob_reg) EditText dobReg;
    @BindView(R.id.email_reg) EditText emailReg;
    @BindView(R.id.password_reg) EditText passwordReg;
    @BindView(R.id.parentbox) RadioButton parentBox;
    @BindView(R.id.childbox) RadioButton childBox;
    List<Person> itemList;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galmoreas_reg_page);
        ButterKnife.bind(this);

        itemList = new ArrayList<>();

        mRef = FirebaseDatabase.getInstance().getReference("Accounts");

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFirstName = firstNameReg.getText().toString();
                String strLastName = lastNameReg.getText().toString();
                String strDob = dobReg.getText().toString();
                String strEmail = emailReg.getText().toString();
                String strPassword = passwordReg.getText().toString();
                String strParentAccount = parentBox.getText().toString();
                String strChildAccount = childBox.getText().toString();

                if (childBox.isChecked()) {

                    mRef.push().setValue(new Person(strFirstName, strLastName, strDob, strEmail,
                            strPassword, strChildAccount));
                }

                if(parentBox.isChecked()) {
                    mRef.push().setValue(new Person(strFirstName, strLastName, strDob, strEmail,
                            strPassword, strParentAccount));
                }

                else
                    Toast.makeText(getApplicationContext(), "No Account Type has been" +
                            "selected", Toast.LENGTH_SHORT).show();


            }


        });



    }


}
