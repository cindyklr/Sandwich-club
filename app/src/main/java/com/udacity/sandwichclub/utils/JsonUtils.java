package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = "JSON Exception : ";
    private static String mainName = null;
    private static List<String> alsoKnownAs;
    private static String placeOfOrigin;
    private static String description;
    private static String image;
    private static List<String> ingredients;

    // https://www.androidhive.info/2012/01/android-json-parsing-tutorial/
    // EarthQuake project From Udacity course Networking
    /**
     *
     * @return un Object Sandwich that has been built up from parsing the given JSON
     */
    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONObject name = jsonObj.getJSONObject("name");
            mainName = name.getString("mainName");
            alsoKnownAs = new ArrayList<>(addToList(name.getJSONArray("alsoKnownAs")));
            // https://stackoverflow.com/questions/13790726/the-difference-between-getstring-and-optstring-in-json/13790789#13790789
            placeOfOrigin = jsonObj.optString("placeOfOrigin");
            description = jsonObj.optString("description");
            image = jsonObj.optString("image");
            ingredients = new ArrayList<>(addToList(jsonObj.getJSONArray("ingredients")));

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());

        }
        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }

    /**
     *
     * @return a list of String from a JSONArray
     */
    private static List<String> addToList(JSONArray array) {
        List<String> listTemp = new ArrayList<>();
        if (array != null) {
            try {
                for (int i=0; i < array.length(); i++) {
                    listTemp.add(array.getString(i));
                }
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return listTemp;
    }

}
