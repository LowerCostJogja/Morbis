package com.example.lucifer.morbis;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.lucifer.morbis.adapter.RekmedAdapter;
import com.example.lucifer.morbis.model.RekmedItemDummy;

import java.util.ArrayList;

/**
 * Created by lucifer on 3/23/2016.
 */
public class Rekmed extends BaseActivity {

    private ArrayList<RekmedItemDummy> dummies;
    private RecyclerView recyclerView;
    private int scrollPos;
    private RekmedAdapter rekmedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rekmed);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        scrollPos = (savedInstanceState != null) ? savedInstanceState.getInt("Rekmed Scrolled") : 0;

        initDummies();

        recyclerView = (RecyclerView) findViewById(R.id.rv_rekmed);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rekmedAdapter = new RekmedAdapter(getApplicationContext(), dummies);
        recyclerView.setAdapter(rekmedAdapter);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.scrollToPosition(scrollPos);


    }

    private void initDummies() {
        dummies = new ArrayList<>();
        dummies.add(new RekmedItemDummy("20/01/2016", "Flue Sedang"));
        dummies.add(new RekmedItemDummy("23/01/2016", "Luka Bakar"));
        dummies.add(new RekmedItemDummy("24/01/2016", "Pemeriksaan luka bakar"));
        dummies.add(new RekmedItemDummy("26/01/2016", "Pemeriksaan luka bakar"));
        dummies.add(new RekmedItemDummy("20/01/2016", "Flue Sedang"));
        dummies.add(new RekmedItemDummy("23/01/2016", "Luka Bakar"));
        dummies.add(new RekmedItemDummy("24/01/2016", "Pemeriksaan luka bakar"));
        dummies.add(new RekmedItemDummy("26/01/2016", "Pemeriksaan luka bakar"));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                Rekmed.this.finish();
                break;
        }

        return true;
    }

}
