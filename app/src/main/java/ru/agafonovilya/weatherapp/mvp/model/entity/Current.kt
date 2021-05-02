package ru.agafonovilya.weatherapp.mvp.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Current {
    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("temp")
    @Expose
    var temp: Double? = null

    @SerializedName("weather")
    @Expose
    var weather: List<CurrentWeather>? = null
}