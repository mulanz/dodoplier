package com.example.dodoplier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    boolean opened = false;
    public void impactClicked(View view){
        if (!opened) {
            TextView explanation = (TextView) findViewById(R.id.explanation);
            explanation.setVisibility(View.VISIBLE);
            TextView impact = (TextView) findViewById(R.id.impact);
            impact.setText("Total Impact: 2X (Match) ___▶");
            opened = true;
        } else{
            TextView explanation = (TextView) findViewById(R.id.explanation);
            explanation.setVisibility(View.GONE);
            TextView impact = (TextView) findViewById(R.id.impact);
            impact.setText("Total Impact: 2X (Match) ___▼");
            opened = false;
        }
    }

}