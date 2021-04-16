package com.example.ghettoanimehub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import models.Anime;
import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String ALL_ANIME_URL= "https://kitsu.io/api/edge/anime?page[limit]=5&page[offset]=0";
    public static final String TAG= "MainActivity";
    List<Anime> animes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(ALL_ANIME_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray data = jsonObject.getJSONArray("data");
                    Log.i(TAG, "data: " + data.toString());
                    animes = Anime.fromJsonArray(data);
                    Log.i(TAG, "Animes: " + animes.size());
                } catch (JSONException e) {
                    Log.e(TAG,"Hit json exception",e);
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");

            }
        });

    }
}