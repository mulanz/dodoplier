package com.example.dodoplier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ExploreCauses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_causes);
    }

    public void causeClicked(View view) {
        Intent goToOrgsExplorer = new Intent(this, ExploreOrganizations.class);
        startActivity(goToOrgsExplorer);
    }
}