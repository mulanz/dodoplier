package com.example.dodoplier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class OrganizationInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_info);

        //add function to back button
        ImageButton orgInfoBackBtn = (ImageButton) findViewById(R.id.orgInfoPageBackBtn);
        orgInfoBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start ExploreOrganizations activity
                Intent startIntent = new Intent(getApplicationContext(), ExploreOrganizations.class);
                startActivity(startIntent);
            }
        });
    }

}