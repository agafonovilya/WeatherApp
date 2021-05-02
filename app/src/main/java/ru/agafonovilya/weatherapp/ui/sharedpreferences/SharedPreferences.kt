package ru.agafonovilya.weatherapp.ui.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import ru.agafonovilya.weatherapp.App
import ru.agafonovilya.weatherapp.mvp.model.savedpreferences.ISavedPreferences

enum class SharedPrefKeys(val key: String) {
    THEME_COLOR("themeColor"),
    CURRENT_CITY("currentCity"),
    CURRENT_LONGITUDE("currentLongitude"),
    CURRENT_LATITUDE("currentLatitude")
}

enum class SharedPrefValue(val value: String) {
    THEME_LIGHT("light"),
    THEME_NIGHT("night")
}

class SharedPreferences: ISavedPreferences {
    private val sharedPreferences: SharedPreferences = App.instance.applicationContext
            .getSharedPreferences("", Context.MODE_PRIVATE)


    override fun putPreferences(key: String, value: String) {
        val editor = sharedPreferences.edit()

        when(key){
            SharedPrefKeys.CURRENT_CITY.key ->
                editor.putString(SharedPrefKeys.CURRENT_CITY.key, value)

            SharedPrefKeys.CURRENT_LONGITUDE.key ->
                editor.putString(SharedPrefKeys.CURRENT_LONGITUDE.key, value)

            SharedPrefKeys.CURRENT_LATITUDE.key ->
                 editor.putString(SharedPrefKeys.CURRENT_LATITUDE.key, value)
        }
        editor.commit()
    }

    override fun getPreferences(key: String): String {
        return when (key) {
            SharedPrefKeys.CURRENT_CITY.key ->
                sharedPreferences.getString(SharedPrefKeys.CURRENT_CITY.key, "Saint-Petersburg") ?: " "
            SharedPrefKeys.CURRENT_LONGITUDE.key ->
                sharedPreferences.getString(SharedPrefKeys.CURRENT_LONGITUDE.key, "30.26") ?: " "
            SharedPrefKeys.CURRENT_LATITUDE.key ->
                sharedPreferences.getString(SharedPrefKeys.CURRENT_LATITUDE.key, "59.89") ?: " "
            else -> ""
        }
    }

    override fun saveLightTheme() {
        sharedPreferences.edit().putString(SharedPrefKeys.THEME_COLOR.key, "light").commit()
    }

    override fun saveNightTheme() {
        sharedPreferences.edit().putString(SharedPrefKeys.THEME_COLOR.key, "night").commit()
    }

    override fun getSavedTheme(): SharedPrefValue {
        val theme = sharedPreferences.getString(SharedPrefKeys.THEME_COLOR.key, "light") ?: "light"
        return when(theme) {
            SharedPrefValue.THEME_LIGHT.value ->  SharedPrefValue.THEME_LIGHT
            SharedPrefValue.THEME_NIGHT.value ->  SharedPrefValue.THEME_NIGHT
            else -> SharedPrefValue.THEME_LIGHT
        }
    }
}


