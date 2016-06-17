package com.example.lucifer.morbis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.lucifer.morbis.API.API;
import com.example.lucifer.morbis.adapter.InfoKamarAdapter;
import com.example.lucifer.morbis.model.InfoKamar.Departement;
import com.example.lucifer.morbis.model.InfoKamar.Department.*;
import com.example.lucifer.morbis.model.InfoKamar.Department.Hospital;
import com.example.lucifer.morbis.model.InfoKamar.MainRoom;
import com.example.lucifer.morbis.model.InfoKamar.Ward;
import com.example.lucifer.morbis.model.InfoKamar.WardClass;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by lucifer on 3/30/2016.
 */
public class InfoKamar extends BaseActivity {

    TableLayout tabel_kamar;

    private RecyclerView recyclerView;
    private ArrayList<MainRoom> dataRoom = new ArrayList<>();
    private InfoKamarAdapter adapterInfoKamar;

    private int scrollPos;

    String pk, hospital_address, hospital_name;

    TextView hosp_name, hosp_address;

    private String token = "";

    int l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_kamar);

        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.MYPREFERENCE, Context.MODE_PRIVATE);
        if(!sharedPreferences.getString(LoginActivity.ACCESS_TOKEN, "").isEmpty())
            token = sharedPreferences.getString(LoginActivity.ACCESS_TOKEN, "");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        pk = intent.getStringExtra("pk");
        hospital_name = intent.getStringExtra("hospital_name");
        hospital_address = intent.getStringExtra("hospital_address");

        hosp_name = (TextView) findViewById(R.id.hospital_name);
        hosp_address = (TextView) findViewById(R.id.hospital_address);

        hosp_name.setText(hospital_name);
        hosp_address.setText(hospital_address);

        loadKamar();

        scrollPos = (savedInstanceState != null) ? savedInstanceState.getInt("Hospital Scrolled") : 0;

        recyclerView = (RecyclerView) findViewById(R.id.rvInfoKamar);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        adapterInfoKamar = new InfoKamarAdapter(getApplicationContext(), dataRoom);
        recyclerView.setAdapter(adapterInfoKamar);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.scrollToPosition(scrollPos);

        tabel_kamar = (TableLayout) findViewById(R.id.country_table);

//        fillTabelKamar();

    }

    private void loadKamar() {
        final String url = API.HOSPITAL + pk + "/wards/";
        AsyncHttpClient client = new AsyncHttpClient(API.PORT);
        client.addHeader("Authorization", "JWT " + token);

        Log.e("Token", token);

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String string = null;
                try {
                    string = new String(responseBody, "UTF-8");

                    Log.e("Hasil Info Kamar", string);

                    try {
                        JSONArray jsonArray = new JSONArray(string);

                        Log.e("Jumlah Data", String.valueOf(jsonArray.length()));

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Log.e("JSON OBJECT", object.toString());

                            /*JSONObject ward = object.getJSONObject("ward");
                            JSONObject wardClass = ward.getJSONObject("ward_class");*/
                            JSONObject department = object.getJSONObject("department");
                            JSONObject department_category = department.getJSONObject("department_category");
                            JSONObject hospital = department.getJSONObject("hospital");

//                            JSONObject wardClass = object.getJSONObject("ward_class");

                            DepartmentCategory departmentCategory = new DepartmentCategory();
                            departmentCategory.setPk(department_category.getInt("pk"));
                            departmentCategory.setName(department_category.getString("name"));

                            Hospital hospitalDepartment = new Hospital();
                            hospitalDepartment.setPk(hospital.getInt("pk"));
                            hospitalDepartment.setName(hospital.getString("name"));
                            hospitalDepartment.setAddress(hospital.getString("address"));

                            Departement departement = new Departement();
                            departement.setPk(department.getInt("pk"));
                            departement.setName(department.getString("name"));
                            departement.setSourceID(department.getString("source_id"));
                            departement.setDepartmentCategory(departmentCategory);
                            departement.setHospital(hospitalDepartment);

                            /*WardClass wardClassWard = new WardClass();
                            wardClassWard.setPk(wardClass.getInt("pk"));
                            wardClassWard.setName(wardClass.getString("name"));

                            Ward wardMain = new Ward();
                            wardMain.setPk(ward.getInt("pk"));
                            wardMain.setName(ward.getString("name"));
                            wardMain.setDepartement(departement);
                            wardMain.setWardClass(wardClassWard);
                            wardMain.setWard_type(ward.getString("ward_type"));*/


                            MainRoom main = new MainRoom();
                            main.setPk(object.getInt("pk"));
                            main.setName(object.getString("name"));
                            main.setDepartement(departement);
//                            main.setWardClass(wardClassWard);
                            main.setWard_type(object.getString("ward_type"));
                            main.setTotal_empty(object.getString("total_empty"));
                            main.setTotal_filled(object.getString("total_filled"));
                            dataRoom.add(main);

                        }
                        adapterInfoKamar.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

    private void fillTabelKamar() {
        TableRow row;
        TextView t1, t2, t3, t4, t5;

        int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) 1, getResources().getDisplayMetrics());

        for (int current = 0; current < ValueRoom.number.length; current++) {
            row = new TableRow(this);

            t1 = new TextView(this);
            t1.setTextColor(getResources().getColor(R.color.colorPrimary));
            t2 = new TextView(this);
            t2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            t3 = new TextView(this);
            t3.setTextColor(getResources().getColor(R.color.colorPrimary));
            t4 = new TextView(this);
            t4.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            t5 = new TextView(this);
            t5.setTextColor(getResources().getColor(R.color.colorPrimary));

            t1.setText(ValueRoom.number[current]);
            t2.setText(ValueRoom.kamar[current]);
            t3.setText(ValueRoom.kelas[current]);

            t1.setTypeface(null, 1);
            t2.setTypeface(null, 1);
            t3.setTypeface(null, 1);
            t4.setTypeface(null, 1);
            t5.setTypeface(null, 1);

            t1.setTextSize(12);
            t2.setTextSize(12);
            t3.setTextSize(12);
            t4.setTextSize(12);
            t5.setTextSize(12);

            t1.setWidth(20 * dip);
            t2.setWidth(130 * dip);
            t3.setWidth(100 * dip);
            t4.setWidth(50 * dip);
            t5.setWidth(30 * dip);
            t1.setPadding(5 * dip, 0, 0, 0);
            row.addView(t1);
            row.addView(t2);
            row.addView(t3);
            row.addView(t4);
            row.addView(t5);

            tabel_kamar.addView(row, new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                InfoKamar.this.finish();
                break;
        }

        return true;
    }

}
