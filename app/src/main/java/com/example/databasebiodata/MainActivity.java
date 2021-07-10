package com.example.databasebiodata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<PersonalIdentity> identityList = new ArrayList<>();

    private PersonalIdentity identityHolder;

    private Button addButton;
    private DatabaseAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.add_button);
        // set add data button on click listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddData.class);
                startActivity(intent);
            }
        });

        // setting up the personal identity data RecyclerView
        mRecyclerView = findViewById(R.id.recyclerview_id);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new DatabaseAdapter(identityList);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        // get data from database
        getDataFromAPI();

    }




    private void getDataFromAPI(){
        // TODO: akses data dari database google spreadsheet
        // database url containing json file
        String url = "https://spreadsheets.google.com/feeds/list/1CHK1VJcfnwUZ2E3rGM8rYfvbxu_iSmWdz6MlfDErg0Y/od6/public/values?alt=json";
        // creating a new variable for our request queue
        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        // creating a variable for our JSON object request and passing our URL to it.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject feedObj = response.getJSONObject("feed");
                    JSONArray entryArray = feedObj.getJSONArray("entry");
                    for(int i = 0; i < entryArray.length(); i++){
                        JSONObject entryObj = entryArray.getJSONObject(i);
                        String name = entryObj.getJSONObject("gsx$nama").getString("$t");
                        String dateOfBirth = entryObj.getJSONObject("gsx$tanggallahir").getString("$t");
                        String address = entryObj.getJSONObject("gsx$alamat").getString("$t");
                        String phoneNumber = entryObj.getJSONObject("gsx$nomortelepon").getString("$t");
                        identityList.add(new PersonalIdentity(name, dateOfBirth, address, phoneNumber));

                        mAdapter.notifyItemInserted(identityList.size());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handline on error listener method.
                Toast.makeText(MainActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        // calling a request queue method
        // and passing our json object
        queue.add(jsonObjectRequest);
    }

}