package team11.csc301.musicjumpstarterapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by nvara on 2/28/2018.
 */

public class LyricsSuggestion {

    public static String GetSuggestions(final Context context, final String data) {
        Log.v("GetSuggestions", "GetSuggestions is Called");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        // 10.0.2.2 is the computer's localhost from emulator, cannot just put "localhost"
        String url ="http://10.0.2.2:3000/suggestions/tomato";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                        Log.v("GetSuggestions", "GetSuggestions Response SUCCESS!");
                        Log.v("GetSuggestions", response);
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

        return "Test Suggestions, Needs to be replaced";
    }
}
