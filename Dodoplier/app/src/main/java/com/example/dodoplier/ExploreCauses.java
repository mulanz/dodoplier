package com.example.dodoplier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExploreCauses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_causes);
    }

    public void causeClicked(View view) {
        int categoryID;
        switch (view.getId()) {
            case R.id.animals:
                categoryID = 1;
            case R.id.arts: 
        }

        Intent goToOrgsExplorer = new Intent(this, ExploreOrganizations.class);
        startActivity(goToOrgsExplorer);
    }
}