package com.example.lucifer.morbis;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by lucifer on 3/29/2016.
 */
public class DetailHospital extends BaseActivity {
    String hospital_name, hospital_address, pk;

    TextView name;
    TextView address;
    ImageView imgHospital;

    LinearLayout kamar;
    LinearLayout dokter;
    LinearLayout ambulance;
    LinearLayout nurses;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_hospital);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        hospital_name = intent.getStringExtra("hospital_name");
        hospital_address = intent.getStringExtra("hospital_address");
        pk = intent.getStringExtra("pk");

        name = (TextView) findViewById(R.id.hospital_name);
        name.setText(hospital_name);
        address = (TextView) findViewById(R.id.hospital_address);
        address.setText(hospital_address);

        kamar = (LinearLayout)findViewById(R.id.info_kamar);
        dokter = (LinearLayout)findViewById(R.id.info_dokter);
        ambulance = (LinearLayout)findViewById(R.id.info_ambulance);
        nurses = (LinearLayout)findViewById(R.id.info_nurses);

        kamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), InfoKamar.class);
                intent1.putExtra("pk", pk);
                intent1.putExtra("hospital_name", hospital_name);
                intent1.putExtra("hospital_address", hospital_address);
                startActivity(intent1);
            }
        });

        dokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), InfoDokter.class);
                intent1.putExtra("pk", pk);
                intent1.putExtra("hospital_name", hospital_name);
                intent1.putExtra("hospital_address", hospital_address);
                startActivity(intent1);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }
}
