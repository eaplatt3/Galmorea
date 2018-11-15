package com.example.sickl.galmorea;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GalmoreasRegPage extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galmoreas_reg_page);


        preferences = getSharedPreferences(getString(R.string.storedAccount), Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void personReg(View view) {
    }
}
