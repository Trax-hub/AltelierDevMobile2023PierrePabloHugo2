package com.example.atelierdevmobile2023pierrepablohugo2.Model

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson

class User (val firstName: String, val lastName: String, val email: String, val address: String, val zipcode: Int, val city: String, val cardRef: Int) {
}

object UserStorage {

    private const val PREFS_NAME = "user_prefs"
    private const val KEY_USER = "user_key"

    fun saveUser(context: Context, user: User) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        editor.putString(KEY_USER, json)
        editor.apply()
    }

    fun getUser(context: Context): User? {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = prefs.getString(KEY_USER, null)
        return gson.fromJson(json, User::class.java)
    }

    fun deleteMyObject(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.remove(KEY_USER)
        editor.apply()
    }
}