package ru.agafonovilya.weatherapp.mvp.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HourlyWeather {
    @SerializedName("icon")
    @Expose
    var icon: String? = null
}