package com.example.dodoplier;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ExploreOrganizations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_organizations);

        String[] orgNames = getIntent().getStringArrayExtra("orgNames");
        String[] orgEIDs = getIntent().getStringArrayExtra("orgEIDs");

        TextView displayLine = (TextView) findViewById(R.id.orgs_title);
        displayLine.setText("Sending love to " + getIntent().getStringExtra("categoryName") + ".");

        Button org1 = (Button) findViewById(R.id.org1);
        org1.setText(orgNames[0]);

        Button org2 = (Button) findViewById(R.id.org2);
        org2.setText(orgNames[1]);

        Button org3 = (Button) findViewById(R.id.org3);
        org3.setText(orgNames[2]);

        Button org4 = (Button) findViewById(R.id.org4);
        org4.setText(orgNames[3]);

        Button org5 = (Button) findViewById(R.id.org5);
        org5.setText(orgNames[4]);

    }

    public void orgClicked(View view) {
        Intent goToOrgPage = new Intent(this, OrganizationInfo.class);
        startActivity(goToOrgPage);
    }

}