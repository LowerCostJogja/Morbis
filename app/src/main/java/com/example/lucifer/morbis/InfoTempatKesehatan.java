package com.example.lucifer.morbis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by lucifer on 3/26/2016.
 */
public class InfoTempatKesehatan extends BaseActivity {

    RelativeLayout dokter_praktek;
    RelativeLayout apotek;
    RelativeLayout hospital;
    RelativeLayout klinik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informasi_tempat_kesehatan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dokter_praktek = (RelativeLayout) findViewById(R.id.dokter_praktek);
        apotek = (RelativeLayout) findViewById(R.id.apotek);
        hospital = (RelativeLayout) findViewById(R.id.hospital);
        klinik = (RelativeLayout) findViewById(R.id.clinic);

        /*dokter_praktek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DokterPraktek.class);
                startActivity(intent);
            }
        });

        apotek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Apotek.class);
                startActivity(intent);
            }
        });*/

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Hospital.class);
                startActivity(intent);
            }
        });

        /*klinik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Clinic.class);
                startActivity(intent);
            }
        });*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                InfoTempatKesehatan.this.finish();
                break;
        }

        return true;
    }
}
