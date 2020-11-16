package com.example.dodoplier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class OrganizationInfo extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_info);

        String orgName = getIntent().getStringExtra("orgName");
        String score = getIntent().getStringExtra("score");
        String tagline = getIntent().getStringExtra("tagline");

        String causeName = "";

        JSONObject cause = null;
        try {
            cause = new JSONObject(getIntent().getStringExtra("cause"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (cause.has("causeName")) {
            try {
                causeName = cause.getString("causeName");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        url = getIntent().getStringExtra("link");

        TextView title = (TextView) findViewById(R.id.orgPageTitle);
        title.setText(orgName);

        TextView scoreTV = (TextView) findViewById(R.id.orgPageTagline3);
        scoreTV.setText(score);

        TextView tl = (TextView) findViewById(R.id.orgPageTagline4);
        tl.setText("\"" + tagline + "\"");

        TextView c = (TextView) findViewById(R.id.orgPageCause);
        c.setText(causeName);

        Log.d("score", score);
        Log.d("url", url);
    }

    public void personClicked(View view) {
        Intent goToProfile = new Intent(this, Profile.class);
        startActivity(goToProfile);
    }

    public void viewBtnClicked(View view) {
        goToUrl(url);
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

}