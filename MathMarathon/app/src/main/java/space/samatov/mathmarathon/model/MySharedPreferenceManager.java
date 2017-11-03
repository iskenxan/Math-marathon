package space.samatov.mathmarathon.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by iskenxan on 10/30/17.
 */

public class MySharedPreferenceManager {

    public static final String PROFILE_PHOTO_URL="profile_photo_url";

    public static void saveString(String key, String value, Context context){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(key,value).commit();
    }


    public static String getString(String key,Context context){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        String result=preferences.getString(key,null);

        return result;
    }
}
