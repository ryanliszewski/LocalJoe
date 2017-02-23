package com.singhliszewski.eventbrowser.controller;

import android.net.Uri;
import android.util.ArrayMap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.singhliszewski.eventbrowser.LocationService;
import com.singhliszewski.eventbrowser.app.App;
import com.singhliszewski.eventbrowser.model.Business;
import com.singhliszewski.eventbrowser.request.JsonRequest;
import com.singhliszewski.eventbrowser.volley.VolleySingleton;

import java.util.List;
import java.util.Map;

/**
 * Created by Ryanliszewski on 12/13/16.
 */

public class JsonController {
    private final int TAG = 100;

    private static final String BASE_URL = "https://api.yelp.com/v3";
    private static final String SEARCH_URL = "/businesses/search";
    private static final String accessToken = "kakIgcNFfk038GiLIxW4ZpqcjsY37bx6JfKlt8VZQQ75UcGi21Ab07GLmgF27HhkAJAPnE7WXq8Ou7EMcJy7Bp0gemDH14BjY6ZWCZw-V3xN7DEZ49IFFzVle0hPWHYx";


    private OnResponseListener responseListener;
    LocationService mLocationService = new LocationService();


    // This keeps returning null.
    // "&latitude=" + Uri.encode(mLocationService.getLatitude()) + "&longitude" + Uri.encode(mLocationService.getLongitude())

    /**
     *
     * @param responseListener  {@link OnResponseListener}
     */
    public JsonController(OnResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    /**
     * Adds request to volley request queue
     * @param term for search
     */
    public void sendRequest(String term) {

        // Request Method
        int method = Request.Method.GET;

        // Url with GET parameters
        String url = BASE_URL + SEARCH_URL + "?term=" + Uri.encode(term) +
                "&location=" + Uri.encode("San Francisco, CA")
                + "&categories=" + "coffeeroasteries";

        // Create new request using JsonRequest
        JsonRequest request = new JsonRequest(method, url,
                new Response.Listener<List<Business>>() {
                    @Override
                    public void onResponse(List<Business> businesses) {
                        responseListener.onSuccess(businesses);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseListener.onFailure(error.getMessage());
                    }
                }

        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new ArrayMap<String, String>();
                String auth = "bearer " + accessToken ;
                headers.put("Authorization", auth);
                return headers;
            }
        };

        // Add tag to request
        request.setTag(TAG);

        // Get RequestQueue from VolleySingleton
        VolleySingleton.getInstance(App.getContext()).addToRequestQueue
                (request);
    }

    /**
     * <p>Cancels all request pending in request queue,</p>
     * <p> There is no way to control the request already processed</p>
     */
    public void cancelAllRequests() {
        VolleySingleton.getInstance(App.getContext()).cancelAllRequests(TAG);
    }

    /**
     *  Interface to communicate between {@link android.app.Activity} and {@link JsonRequest}
     *  <p>Object available in {@link JsonRequest} and implemented in {@link com.example.csc413_volley_template.MainActivity}</p>
     */
    public interface OnResponseListener {
        void onSuccess(List<Business> b);
        void onFailure(String errorMessage);
    }
}
