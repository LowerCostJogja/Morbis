package com.example.lucifer.morbis;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getToolbar();
    }

    protected Toolbar getToolbar() {
        if(toolbar == null){
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if(toolbar != null){
                setSupportActionBar(toolbar);
            }
        }
        return toolbar;
    }

}
