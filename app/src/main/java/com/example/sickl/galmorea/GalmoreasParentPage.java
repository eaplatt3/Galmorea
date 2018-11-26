package com.example.sickl.galmorea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;

public class GalmoreasParentPage extends AppCompatActivity {

    @BindView(R.id.childScore) TextView childScore;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galmoreas_parent_page);

        childScore.setText("Child Score is: " + score);


    }
}
