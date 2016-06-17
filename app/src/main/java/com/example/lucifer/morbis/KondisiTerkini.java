package com.example.lucifer.morbis;

import android.os.Bundle;
import android.view.MenuItem;

/**
 * Created by lucifer on 3/23/2016.
 */
public class KondisiTerkini extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kondisi_terkini);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                KondisiTerkini.this.finish();
                break;
        }

        return true;
    }


}
