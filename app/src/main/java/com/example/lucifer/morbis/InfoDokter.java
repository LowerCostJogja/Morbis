package com.example.lucifer.morbis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lucifer.morbis.API.API;
import com.example.lucifer.morbis.adapter.InfoDokterAdapter;
import com.example.lucifer.morbis.model.InfoDoctor.Citizen;
import com.example.lucifer.morbis.model.InfoDoctor.Doctor;
import com.example.lucifer.morbis.model.InfoDoctor.Hospital;
import com.example.lucifer.morbis.model.InfoDoctor.MainDoctor;
import com.example.lucifer.morbis.model.InfoDoctor.Specialization;
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
 * Created by lucifer on 4/25/2016.
 */
public class InfoDokter extends BaseActivity {

    private String token = "";
    String pk, hospital_address, hospital_name;

    TextView address_hosp, name_hosp;

    private static final String fileDoktor = "fileDoktor.txt";

    private ArrayList<MainDoctor> dataDoctor = new ArrayList<>();
    private InfoDokterAdapter dataDoctorAdapter;
    private RecyclerView recyclerView;
    private int scrollPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_dokter);

        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.MYPREFERENCE, Context.MODE_PRIVATE);
        if(!sharedPreferences.getString(LoginActivity.ACCESS_TOKEN, "").isEmpty())
            token = sharedPreferences.getString(LoginActivity.ACCESS_TOKEN, "");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        pk = intent.getStringExtra("pk");
        hospital_name = intent.getStringExtra("hospital_name");
        hospital_address = intent.getStringExtra("hospital_address");

        address_hosp = (TextView) findViewById(R.id.rs_address);
        name_hosp = (TextView) findViewById(R.id.nama_rs);

        address_hosp.setText(hospital_address);
        name_hosp.setText(hospital_name);

        loadDataDoctor();

//        loadFileDoctor();

        scrollPos = (savedInstanceState != null) ? savedInstanceState.getInt("Hospital Scrolled") : 0;

        Log.e("Doctor Data", String.valueOf(dataDoctor));

        recyclerView = (RecyclerView) findViewById(R.id.rvInfoDokter);
//        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        dataDoctorAdapter = new InfoDokterAdapter(getApplicationContext(), dataDoctor);
        recyclerView.setAdapter(dataDoctorAdapter);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.scrollToPosition(scrollPos);

    }

    private String loadFileDoctor() {
        String ret = "";
        try {
            InputStream inputStream = openFileInput(fileDoktor);

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

                Log.e("Response File Doctor", ret);

                try {
                    JSONArray jsonArray = new JSONArray(ret);
                    Log.e("Jumlah Data Dokter", String.valueOf(jsonArray.length()));

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Log.e("JSON OBJECT", object.toString());

                        JSONObject doctor = object.getJSONObject("doctor");
                        JSONObject hospital = object.getJSONObject("hospital");

                        JSONObject specialization = doctor.getJSONObject("specialization");
                        Specialization specialization1 = new Specialization();
                        specialization1.setPk(specialization.getInt("pk"));
                        specialization1.setName(specialization.getString("name"));
                        Log.e("Specialization", specialization.getString("name"));

                        JSONObject citizen = doctor.getJSONObject("citizen");
                        Citizen citizen1 = new Citizen();
                        citizen1.setPk(citizen.getInt("pk"));
                        citizen1.setName(citizen.getString("name"));
                        Log.e("Nama Dokter", citizen.getString("name"));
                        citizen1.setIdentity_number(citizen.getInt("identity_number"));
                        citizen1.setGender(citizen.getString("gender"));
                        citizen1.setPlace_of_birth(citizen.getString("place_of_birth"));
                        citizen1.setDate_of_birth(citizen.getString("date_of_birth"));
                        citizen1.setBlood_type("blood_type");

                        Hospital hospital1 = new Hospital();
                        hospital1.setPk(hospital.getInt("pk"));
                        hospital1.setName(hospital.getString("name"));
                        Log.e("Nama Hospital", hospital.getString("name"));
                        hospital1.setAddress(hospital.getString("address"));

                        Doctor doctor1 = new Doctor();
                        doctor1.setPk(doctor.getInt("pk"));
                        doctor1.setCitizen(citizen1);
                        Log.e("setCitizen", String.valueOf(citizen1));
                        doctor1.setSpecialization(specialization1);
                        Log.e("setSpecialization", String.valueOf(specialization1));

                        MainDoctor mainDoctor = new MainDoctor();
                        mainDoctor.setPk(object.getInt("pk"));
                        Log.e("mainDoctor.setPk", String.valueOf(object.getInt("pk")));
                        mainDoctor.setSourceID(object.getString("source_id"));
                        Log.e("mainDoctor.setSourceID", object.getString("source_id"));
                        mainDoctor.setDoctor(doctor1);
                        Log.e("mainDoctor.setDoctor", String.valueOf(doctor1));
                        mainDoctor.setHospital(hospital1);
                        Log.e("mainDoctor.setHospital", String.valueOf(hospital1));
                        dataDoctor.add(mainDoctor);

                        Log.e("mainDoctor", String.valueOf(mainDoctor));

                    }
//                    dataDoctorAdapter.notifyDataSetChanged();

                } catch (Exception e) {
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

    private void loadDataDoctor() {
        final String url = API.DOCTOR_INFO + pk + "/doctors/";
        AsyncHttpClient client = new AsyncHttpClient(API.PORT);
        client.addHeader("Authorization", "JWT " + token);

        Log.e("Token", token);

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = null;
                try {
                    string = new String(responseBody, "UTF-8");
                    Log.e("Hasil Info Doktor", string);

                    try {
                        JSONArray jsonArray = new JSONArray(string);
                        Log.e("Jumlah Data Dokter", String.valueOf(jsonArray.length()));

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Log.e("JSON OBJECT", object.toString());

                            JSONObject doctor = object.getJSONObject("doctor");
                            JSONObject hospital = object.getJSONObject("hospital");

                            JSONObject specialization = doctor.getJSONObject("specialization");
                            Specialization specialization1 = new Specialization();
                            specialization1.setPk(specialization.getInt("pk"));
                            specialization1.setName(specialization.getString("name"));
                            Log.e("Specialization", specialization.getString("name"));

                            JSONObject citizen = doctor.getJSONObject("citizen");
                            Citizen citizen1 = new Citizen();
                            citizen1.setPk(citizen.getInt("pk"));
                            citizen1.setName(citizen.getString("name"));
                            Log.e("Nama Dokter", citizen.getString("name"));
                            citizen1.setIdentity_number(citizen.getInt("identity_number"));
                            citizen1.setGender(citizen.getString("gender"));
                            citizen1.setPlace_of_birth(citizen.getString("place_of_birth"));
                            citizen1.setDate_of_birth(citizen.getString("date_of_birth"));
                            citizen1.setBlood_type("blood_type");

                            Hospital hospital1 = new Hospital();
                            hospital1.setPk(hospital.getInt("pk"));
                            hospital1.setName(hospital.getString("name"));
                            Log.e("Nama Hospital", hospital.getString("name"));
                            hospital1.setAddress(hospital.getString("address"));

                            Doctor doctor1 = new Doctor();
                            doctor1.setPk(doctor.getInt("pk"));
                            doctor1.setCitizen(citizen1);
                            Log.e("setCitizen", String.valueOf(citizen1));
                            doctor1.setSpecialization(specialization1);
                            Log.e("setSpecialization", String.valueOf(specialization1));

                            MainDoctor mainDoctor = new MainDoctor();
                            mainDoctor.setPk(object.getInt("pk"));
                            Log.e("mainDoctor.setPk", String.valueOf(object.getInt("pk")));
                            mainDoctor.setSourceID(object.getString("source_id"));
                            Log.e("mainDoctor.setSourceID", object.getString("source_id"));
                            mainDoctor.setDoctor(doctor1);
                            Log.e("mainDoctor.setDoctor", String.valueOf(doctor1));
                            mainDoctor.setHospital(hospital1);
                            Log.e("mainDoctor.setHospital", String.valueOf(hospital1));
                            dataDoctor.add(mainDoctor);

                            Log.e("mainDoctor", String.valueOf(mainDoctor));

                        }
                        dataDoctorAdapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    /*try {
                        JSONArray responseJsonObj = new JSONArray(string);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(fileDoktor, Context.MODE_PRIVATE));
                        outputStreamWriter.write(responseJsonObj.toString());
                        outputStreamWriter.close();

                        Log.e("JSONObject Doctor", String.valueOf(responseJsonObj));

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                InfoDokter.this.finish();
                break;
        }

        return true;
    }

}
