package com.example.dodoplier;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExploreOrganizations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_organizations);
    }

    public void orgClicked(View view) {
        Intent goToOrgPage = new Intent(this, OrganizationInfo.class);
        startActivity(goToOrgPage);
    }

}