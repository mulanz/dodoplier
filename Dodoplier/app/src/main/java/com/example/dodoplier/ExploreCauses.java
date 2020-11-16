package com.example.dodoplier;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExploreCauses extends AppCompatActivity {
    String catName;
    String app_id = "5e3ddec9";
    String api_key = "5032a030035589971487a4a8b4cd5822";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_causes);
    }

    public void causeClicked(View view) {
        int categoryID;
        int viewID = view.getId();
        switch (viewID) {
            case R.id.animals:
                categoryID = 1;
                catName = "animals";
                break;
            case R.id.arts:
                categoryID = 2;
                catName = "arts + culture";
                break;
            case R.id.community:
                categoryID = 3;
                catName = "community";
                break;
            case R.id.education:
                categoryID = 4;
                catName = "education";
                break;
            case R.id.environment:
                categoryID = 5;
                catName = "environment";
                break;
            case R.id.health:
                categoryID = 6;
                catName = "health";
                break;
            case R.id.human:
                categoryID = 7;
                catName = "human";
                break;
            case R.id.human_services:
                categoryID = 8;
                catName = "human services";
                break;
            default:
                categoryID = 1;
        }
        Log.d("category", Integer.toString(categoryID));
        Log.d("category name", catName);
        String url = createURL(categoryID);
        sendGetRequest(url);
    }

    private void sendGetRequest(String url) {
        RequestQueue queue = Volley.newRequestQueue(ExploreCauses.this);
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

    private void parseResponse(String response) throws JSONException {
        int[] random = new int[5];
        String[] orgNames = new String[5];
        String[] orgEIDs = new String[5];

        JSONArray jsonResponse = new JSONArray(response);
        int numCharities = jsonResponse.length();
        Log.d("num charities", Integer.toString(numCharities));

        for (int i = 0; i < 5; i ++) {
            random[i] = (int)(Math.random()*numCharities);
        }

        for (int i = 0; i < random.length; i ++) {
            JSONObject charityObject = jsonResponse.getJSONObject(random[i]);

            if (charityObject.has("charityName")) {
                orgNames[i] = charityObject.getString("charityName");
            }

            if (charityObject.has("ein")) {
                orgEIDs[i] = charityObject.getString("ein");
            }
        }

        Log.d("first org", orgNames[0]);
        Log.d("first EID", orgEIDs[0]);

        Intent goToOrgsExplorer = new Intent(this, ExploreOrganizations.class);
        goToOrgsExplorer.putExtra("orgNames", orgNames);
        goToOrgsExplorer.putExtra("orgEIDs", orgEIDs);
        goToOrgsExplorer.putExtra("categoryName", catName);
        startActivity(goToOrgsExplorer);
    }

    private String createURL(int categoryID) {
        System.out.print("entered");
        String base = "https://api.data.charitynavigator.org/v2/Organizations?";
        String id = "app_id=" + app_id;
        String key = "app_key=" + api_key;
        String url = base + id + "&" + key + "&" + "rated=true" + "&" + "categoryID=" + categoryID + "&" + "minRating=4";
        return url;
    }
}