package com.singhliszewski.eventbrowser.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ryanliszewski on 12/13/16.
 */

public class Business implements Serializable {
    private String id;
    private String name;
    private String phone;
    private String imageUrl;
    private String url;
    private String address;
    private Double rating;
    private UUID mUUID;

    private Business mBusiness;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRating() {
        return rating.toString();
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID uuid){
        this.mUUID = uuid;
    }

    /**
     *
     * @param jsonObject    {@link JSONObject} response, received in Volley success listener
     * @return  list of movies
     * @throws JSONException
     */
    public static List<Business> parseJson(JSONObject jsonObject) throws JSONException{
        List<Business> businesses = new ArrayList<>();
        // Check if the JSONObject has object with key "Search"
        if(jsonObject.has("businesses")){
            // Get JSONArray from JSONObject
            JSONArray jsonArray = jsonObject.getJSONArray("businesses");
            for(int i = 0; i < jsonArray.length(); i++){
                // Create new Movie object from each JSONObject in the JSONArray
                //if(jsonArray.getString(""))
                businesses.add(new Business(jsonArray.getJSONObject(i)));
            }
        }
        return businesses;
    }

    private Business(JSONObject jsonObject) throws JSONException {
        if(jsonObject.has("id")) this.setId(jsonObject.getString("id"));
        if(jsonObject.has("name")) this.setName(jsonObject.getString("name"));
        if(jsonObject.has("phone")) this.setPhone(jsonObject.getString("phone"));
        if(jsonObject.has("image_url")) this.setImageUrl(jsonObject.getString("image_url"));
        if(jsonObject.has("url")) this.setUrl(jsonObject.getString("url"));
        if(jsonObject.has("rating")) this.setRating(jsonObject.getDouble("rating"));
        if(jsonObject.has("address1")) this.setAddress(jsonObject.getString("address1"));
        this.setUUID(UUID.randomUUID());
    }
}
