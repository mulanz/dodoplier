package com.example.dodoplier;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExploreOrganizations extends AppCompatActivity {

    String orgName;
    String app_id = "5e3ddec9";
    String api_key = "5032a030035589971487a4a8b4cd5822";
    String[] orgNames = new String[5];
    String[] orgEIDs = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_organizations);

        orgNames = getIntent().getStringArrayExtra("orgNames");
        orgEIDs = getIntent().getStringArrayExtra("orgEIDs");

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
        String eid;
        int viewID = view.getId();
        switch (viewID) {
            case R.id.org1:
                eid = orgEIDs[0];
                orgName = orgNames[0];
                break;
            case R.id.org2:
                eid = orgEIDs[1];
                orgName = orgNames[1];
                break;
            case R.id.org3:
                eid = orgEIDs[2];
                orgName = orgNames[2];
                break;
            case R.id.org4:
                eid = orgEIDs[3];
                orgName = orgNames[3];
                break;
            default:
                eid = orgEIDs[4];
                orgName = orgNames[4];
        }
        String url = createURL(eid);
        sendGetRequest(url);
    }

    private String createURL(String eid) {
        System.out.print("entered");
        String base = "https://api.data.charitynavigator.org/v2/Organizations/";
        String id = "app_id=" + app_id;
        String key = "app_key=" + api_key;
        String url = base + eid + "?" + id + "&" + key + "&" + "ein=" + eid;

        return url;
    }

    private void parseResponse(String response) throws JSONException {

        JSONObject orgObject = new JSONObject(response);

        double score = 90.0;
        String tagline = "";
        String cause = "";
        String link = "";

        if (orgObject.has("score")) {
            score = orgObject.getDouble("score");
        }
        if (orgObject.has("tagLine")) {
            tagline = orgObject.getString("tagLine");
        }
        if (orgObject.has("cause")) {
            cause = orgObject.getString("cause");
        }
        if (orgObject.has("link")) {
            link = orgObject.getString("link");
        }

        Log.d("tagline", tagline);
        Log.d("cause", cause);
        Log.d("link", link);

        Intent goToOrgInfo = new Intent(this, OrganizationInfo.class);
        goToOrgInfo.putExtra("orgName", orgName);
        goToOrgInfo.putExtra("score", Double.toString(score));
        goToOrgInfo.putExtra("tagline", tagline);
        goToOrgInfo.putExtra("cause", cause);
        goToOrgInfo.putExtra("link", link);
        startActivity(goToOrgInfo);
    }

    private void sendGetRequest(String url) {
        RequestQueue queue = Volley.newRequestQueue(ExploreOrganizations.this);
        final StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    parseResponse(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

}