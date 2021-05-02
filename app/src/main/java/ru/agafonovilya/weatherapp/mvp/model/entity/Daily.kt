package ru.agafonovilya.weatherapp.mvp.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Daily {
    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("temp")
    @Expose
    var temp: Temp? = null

    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null

    @SerializedName("wind_speed")
    @Expose
    var wind: Double? = null

    @SerializedName("weather")
    @Expose
    var weather: List<DailyWeather>? = null
}