package com.example.examen2;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class ApplicationPreferences {
    private static SharedPreferences mSharedPreferences;

    public static  void init(Context context){
        if(mSharedPreferences == null){
            mSharedPreferences = context.getSharedPreferences("MY_PREFES", Activity.MODE_PRIVATE);
        }
    }

    public static void saveName(String KEYNAME, String name){
        SharedPreferences.Editor prefersEditor = mSharedPreferences.edit();
        prefersEditor.putString(KEYNAME,name);
        prefersEditor.apply();
    }

    public static String loadName(String KEYNAME){
        return mSharedPreferences.getString(KEYNAME,"");
    }

}
