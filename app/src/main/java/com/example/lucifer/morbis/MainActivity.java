package com.example.lucifer.morbis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends BaseActivity {

    RelativeLayout rekmed;
    RelativeLayout kondisi_terkini;
    RelativeLayout info_pmi;
    RelativeLayout info_tempat_kes;

    ImageView signout;

    private String token = "";

    private String acessToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rekmed = (RelativeLayout) findViewById(R.id.info_rekmed);
        kondisi_terkini = (RelativeLayout) findViewById(R.id.info_kondisi);
        info_pmi = (RelativeLayout) findViewById(R.id.info_pmi);
        info_tempat_kes = (RelativeLayout) findViewById(R.id.info_tempat_kes);

        rekmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Rekmed.class);
                startActivity(intent);
            }
        });

        kondisi_terkini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KondisiTerkini.class);
                startActivity(intent);
            }
        });

        /*info_pmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PMIInfo.class);
                startActivity(intent);
            }
        });*/

        info_tempat_kes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InfoTempatKesehatan.class);
                startActivity(intent);
            }
        });

        signout = (ImageView) toolbar.findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.MYPREFERENCE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

    }

}
