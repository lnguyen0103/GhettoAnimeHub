package models;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Anime {
   // String posterImage;
    //String originalPoster;

    String canonicalTitle;
    String synopsis;
    String iD;
    JSONObject titles;

    public Anime(JSONObject jsonObject) throws JSONException {
       // posterImage = jsonObject.getString("posterImage");
        //originalPoster = jsonObject.getString("original");
        canonicalTitle = jsonObject.getJSONObject("attributes").getString("canonicalTitle");

        //synopsis= jsonObject.getString("synopsis");
        iD= jsonObject.getString("id");

    }
    public static List<Anime> fromJsonArray(JSONArray animeJsonArray) throws JSONException {
        List<Anime> animes= new ArrayList<>();
        for(int i=0; i < animeJsonArray.length(); i++){
            animes.add(new Anime(animeJsonArray.getJSONObject(i)));
        }
        return animes;

    }

    public String getPosterImage() {
        return String.format("https://media.kitsu.io/anime/poster_images/", iD, "/original.jpg?1597604210");
    }

    public String getCanonicalTitle() {
        return canonicalTitle;
    }

    public String getSynopsis() {
        return synopsis;
    }
}
