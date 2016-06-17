package com.example.lucifer.morbis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.example.lucifer.morbis.API.API;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by lucifer on 3/23/2016.
 */
public class LoginActivity extends BaseActivity {

    private BootstrapEditText mEmailView;
    private BootstrapEditText mPasswordView;

    private SharedPreferences pref;
    public static final String ACCESS_TOKEN = "access_token";
    public static final String MYPREFERENCE = "preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mEmailView = (BootstrapEditText) findViewById(R.id.email);
        mPasswordView = (BootstrapEditText) findViewById(R.id.password);

        AsyncTask<Void, Void, String> at = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                pref = getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
                String accessToken = pref.getString(ACCESS_TOKEN, "");
                Log.d("Token Login", accessToken);
                return accessToken;
            }

            @Override
            protected void onPostExecute(String accessToken) {
                if (!accessToken.isEmpty()) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else
                    dialogLogin();
            }
        };
        at.execute((Void[]) null);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void dialogLogin() {
        BootstrapButton mEmailSignInButton = (BootstrapButton) findViewById(R.id.email_sign_in_button);

        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        String emailLogin = mEmailView.getText().toString();
        String passwordLogin = mPasswordView.getText().toString();

        final String url = API.LOGIN;
//        loading.setVisibility(View.VISIBLE);
        AsyncHttpClient client = new AsyncHttpClient(API.PORT);

        JSONObject oo = new JSONObject();
        try {
            oo.put("username", emailLogin);
            oo.put("password", passwordLogin);
        } catch (Exception ex) {

        }

        StringEntity entity = new StringEntity(oo.toString(), "UTF8");
        Log.e("ent login", oo.toString());

        client.post(this, url, entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                onLoginHttpSuccess(statusCode, headers, responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                loading.setVisibility(View.GONE);
                onLoginHttpFailure(statusCode, headers, responseBody);
                Log.e("fail", String.valueOf(statusCode));

            }
        });
    }

    private void onLoginHttpFailure(int statusCode, Header[] headers, byte[] responseBody) {
        String string = null;
        try {
            string = new String(responseBody, "UTF-8");
            Log.e("Hasil Login Failure", string);
            Toast.makeText(getApplicationContext(), "Username dan Password Salah atau Kosong", Toast.LENGTH_LONG).show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onLoginHttpSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        String string = null;
        try {
            string = new String(responseBody, "UTF-8");

            Log.e("Response", string);

            JSONObject  jsonObject = new JSONObject(string);

            SharedPreferences.Editor editor = pref.edit();
            editor.putString(ACCESS_TOKEN, jsonObject.getString("token"));
            editor.commit();

            Log.e("TOKEN", jsonObject.getString("token"));

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}