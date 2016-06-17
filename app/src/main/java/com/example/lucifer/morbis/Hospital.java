package com.example.lucifer.morbis;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.lucifer.morbis.API.API;
import com.example.lucifer.morbis.adapter.HospitalAdapter;
import com.example.lucifer.morbis.model.HospitalModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by lucifer on 3/28/2016.
 */
public class Hospital extends BaseActivity {

    private ArrayList<HospitalModel> dataHospital = new ArrayList<>();
    private RecyclerView recyclerView;
    private int scrollPos;
    private HospitalAdapter hospitalAdapter;

    private static final String fileHospital = "fileHospital.txt";

    private String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital);

        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.MYPREFERENCE, Context.MODE_PRIVATE);
        if(!sharedPreferences.getString(LoginActivity.ACCESS_TOKEN, "").isEmpty())
            token = sharedPreferences.getString(LoginActivity.ACCESS_TOKEN, "");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadHospital();

        /*initDummies();*/

//        loadFileHospital();

        scrollPos = (savedInstanceState != null) ? savedInstanceState.getInt("Hospital Scrolled") : 0;

        Log.e("Hospital Data", String.valueOf(dataHospital));

        recyclerView = (RecyclerView) findViewById(R.id.rv_hospital);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        hospitalAdapter = new HospitalAdapter(getApplicationContext(), dataHospital);
        recyclerView.setAdapter(hospitalAdapter);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.scrollToPosition(scrollPos);

    }

    private String loadFileHospital() {
        String ret = "";
        try {
            InputStream inputStream = openFileInput(fileHospital);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();

                Log.e("Response Chat Index", ret);

                try {
                    JSONObject jsonObject = new JSONObject(ret);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    Log.e("JSON ARRAY", String.valueOf(jsonArray));

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Log.e("OBJECT", String.valueOf(object));

                        HospitalModel hospitalModel = new HospitalModel();
                        hospitalModel.setPk(object.getString("pk"));
                        Log.e("PK", String.valueOf(object.getInt("pk")));

                        hospitalModel.setName(object.getString("name"));
                        Log.e("Name", object.getString("name"));

                        hospitalModel.setAddress(object.getString("address"));
                        Log.e("Address", object.getString("address"));

                        dataHospital.add(hospitalModel);

                        Log.e("hospitalModel", String.valueOf(hospitalModel));

                    }

//                    hospitalAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (FileNotFoundException e) {
            Log.e("TAG", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("TAG", "Can not read file: " + e.toString());
        }

        return ret;
    }

    private void loadHospital() {
        final String url = API.HOSPITAL;
        AsyncHttpClient client = new AsyncHttpClient(API.PORT);
        client.addHeader("Authorization", "JWT " + token);

        Log.e("Token", token);

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = null;
                try {
                    string = new String(responseBody, "UTF-8");

                    Log.e("Hasil", string);

                    try {
                        JSONObject jsonObject = new JSONObject(string);
                        JSONArray jsonArray = jsonObject.getJSONArray("results");

                        Log.e("Result", String.valueOf(jsonObject.getJSONArray("results")));

                        Log.e("Jumlah Data", String.valueOf(jsonArray.length()));

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);

                            HospitalModel hospitalModel = new HospitalModel();
                            hospitalModel.setPk(object.getString("pk"));
                            Log.e("PK", String.valueOf(object.getInt("pk")));

                            hospitalModel.setName(object.getString("name"));
                            Log.e("Name", object.getString("name"));

                            hospitalModel.setAddress(object.getString("address"));
                            Log.e("Address", object.getString("address"));

                            dataHospital.add(hospitalModel);

                            Log.e("hospitalModel", String.valueOf(hospitalModel));

                        }

                        hospitalAdapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    /*try {
                        JSONObject responseJsonObj = new JSONObject(string);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(fileHospital, Context.MODE_PRIVATE));
                        outputStreamWriter.write(responseJsonObj.toString());
                        outputStreamWriter.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/

                    Log.e("Sukses ", String.valueOf(statusCode));

                }catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    /*private void initDummies() {
        dummies = new ArrayList<>();
        dummies.add(new HospitalItemDummy(R.drawable.muhammadiyah, "PKU Muhammadiyah Sruweng", "Jln. Jalur Lintas Kebumen Gombong No. 24"));
        dummies.add(new HospitalItemDummy(R.drawable.muhammadiyah, "PKU Muhammadiyah Sruweng", "Jln. Jalur Lintas Kebumen Gombong No. 24"));
        dummies.add(new HospitalItemDummy(R.drawable.muhammadiyah, "PKU Muhammadiyah Sruweng", "Jln. Jalur Lintas Kebumen Gombong No. 24"));
        dummies.add(new HospitalItemDummy(R.drawable.muhammadiyah, "PKU Muhammadiyah Sruweng", "Jln. Jalur Lintas Kebumen Gombong No. 24"));
        dummies.add(new HospitalItemDummy(R.drawable.muhammadiyah, "PKU Muhammadiyah Sruweng", "Jln. Jalur Lintas Kebumen Gombong No. 24"));
        dummies.add(new HospitalItemDummy(R.drawable.muhammadiyah, "PKU Muhammadiyah Sruweng", "Jln. Jalur Lintas Kebumen Gombong No. 24"));
        dummies.add(new HospitalItemDummy(R.drawable.muhammadiyah, "PKU Muhammadiyah Sruweng", "Jln. Jalur Lintas Kebumen Gombong No. 24"));
        dummies.add(new HospitalItemDummy(R.drawable.muhammadiyah, "PKU Muhammadiyah Sruweng", "Jln. Jalur Lintas Kebumen Gombong No. 24"));
        dummies.add(new HospitalItemDummy(R.drawable.muhammadiyah, "PKU Muhammadiyah Sruweng", "Jln. Jalur Lintas Kebumen Gombong No. 24"));
        dummies.add(new HospitalItemDummy(R.drawable.muhammadiyah, "PKU Muhammadiyah Sruweng", "Jln. Jalur Lintas Kebumen Gombong No. 24"));
    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                Hospital.this.finish();
                break;
        }

        return true;
    }
}
