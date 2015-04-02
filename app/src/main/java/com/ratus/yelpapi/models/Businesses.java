package com.ratus.yelpapi.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rbhavsar on 4/1/2015.
 */
public class Businesses {

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    //List out the attributes
    private String businessName;
    private String rating;
    private String mobile;

    // Deserialize the JSON
    // create method to convert business.fromJson({..}") => <business>


    public static Businesses fromJSON(JSONObject jsonObject) {
        Businesses business = new Businesses();

        // Extract the values from the json, store them

        try {

            business.businessName = jsonObject.getString("name");
            business.rating = jsonObject.getString("rating_img_url");
            business.mobile = jsonObject.getString("mobile_url");

        } catch (JSONException e ){
            e.printStackTrace();

        }

        // Return the business object
        return business;

    }

    // Pass Json array and ourtout us list of businesss
    public static ArrayList<Businesses> fromJSONArray(JSONArray jsonArray) {

        ArrayList<Businesses> businesss = new ArrayList<>();

        // Iterrate the json array and create businesss
        for (int i=0; i< jsonArray.length() ; i++){

            try {
                JSONObject businessJson = jsonArray.getJSONObject(i);

                Businesses business = Businesses.fromJSON(businessJson);

                if (business != null ){
                    businesss.add(business);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }

        }

        // return the finished list
        return businesss;

    }


}
