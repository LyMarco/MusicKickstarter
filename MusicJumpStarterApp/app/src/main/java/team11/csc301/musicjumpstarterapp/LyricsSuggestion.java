package team11.csc301.musicjumpstarterapp;

import android.content.Context;
import android.text.Editable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nvara on 2/28/2018.
 */

class DataWrapper {
    public SuggestionResponse data;

    public static DataWrapper fromJson(String s) {
        return new Gson().fromJson(s, DataWrapper.class);
    }
    public String toString() {
        return new Gson().toJson(this);
    }
}
class SuggestionResponse {
    public ArrayList<String> rhymes;
    public String defenition;
    public List<String> examples;
    public List<String> synonyms;
    public List<String> antonyms;
}
class Translation {
    public String translatedText;
}

public class LyricsSuggestion {



    public static void GetSuggestions(final Lyrics context, final String word) {//(final Context context, final String word) {
        Log.v("GetSuggestions", "GetSuggestions is Called");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        // 10.0.2.2 is the computer's localhost from emulator, cannot just put "localhost"
        String url ="http://csc301-team-11-lyrics.azurewebsites.net/suggestions/" + word.toLowerCase();

        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject  json) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                        Log.v("GetSuggestions", "GetSuggestions Response SUCCESS!");
                        //Log.v("GetSuggestions", response);
                        Gson gson = new GsonBuilder().create();
                        SuggestionResponse responseObject = gson.fromJson(json.toString(), SuggestionResponse.class);
                        context.onSuggestionReceived(responseObject.rhymes);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mTextView.setText("That didn't work!");
                        Log.v("GetSuggestions", "GetSuggestions Response ERROR!");
                    }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

        //return "Test Suggestions, Needs to be replaced";
    }
}
