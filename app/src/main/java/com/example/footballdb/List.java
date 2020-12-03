package com.example.footballdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    RecyclerView recyclerView;
    DataAdapter adapter;
    ArrayList<Model_football> DataArrayList; //kit add kan ke adapter
    ImageView tambah_data;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        dialog = new ProgressDialog(List.this);
        //addData();
        addDataOnline();

    }
    void addData() {
        //offline, isi data offline dulu
        DataArrayList = new ArrayList<>();
        Model_football data1 = new Model_football();
        data1.setStrTeam("Nama Club");
        data1.setStrTeamLogo("https:\\/\\/www.thesportsdb.com\\/images\\/media\\/team\\/logo\\/wtsxus1424375522.png");
        //data1.setIntLoved(false);
        data1.setStrDescriptionEN("Deskripsi Club disini");
        data1.setId(133604);
        data1.setIntFormedYear(1892);
        DataArrayList.add(data1);


        adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void test() {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(List.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //get data online

    }

    void addDataOnline(){
        //kasih loading
        dialog.setMessage("Sedang memproses data");
        dialog.show();
        // background process
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini
                        DataArrayList = new ArrayList<>();
                        Model_football modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("teams");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new Model_football();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                Log.d("json object",String.valueOf(jsonArray.getJSONObject(i).getString("strStadium")));
                                modelku.setId(jsonObject.getInt("idTeam"));
                                modelku.setStrTeam(jsonObject.getString("strTeam"));
                                modelku.setStrDescriptionEN(jsonObject.getString("strDescriptionEN"));
                                modelku.setIntFormedYear(jsonObject.getInt("intFormedYear"));
                                modelku.setStrTeamBadge(jsonObject.getString("strTeamBadge"));
                                //modelku.setIntLoved(jsonObject.getString("intLoved"));
                                modelku.setIntLoved(2);
                                modelku.setStrStadium(jsonObject.getString("strStadium"));
                                Log.d("json",modelku.getStrTeam());
                                DataArrayList.add(modelku);
                            }
                            //untuk handle click
                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    Model_football football = DataArrayList.get(position);
                                    Intent intent = new Intent(getApplicationContext(), DetailMovie.class);
                                    intent.putExtra("id", football.idTeam);
                                    intent.putExtra("judul", football.strTeam);
                                    intent.putExtra("date", football.intFormedYear);
                                    intent.putExtra("deskripsi", football.strDescriptionEN);
                                    intent.putExtra("path", football.strTeamLogo);
                                    startActivity(intent);
                                    Toast.makeText(List.this, "" + position, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(List.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                    }
                });
    }

}