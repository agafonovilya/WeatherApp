package ru.agafonovilya.weatherapp.ui.format

import java.text.SimpleDateFormat
import java.util.*

    fun formatUnixUtcHhMm(unixUTC: Int): String {
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val unixMilliSeconds = unixUTC.toLong() * 1000
        val time = Date(unixMilliSeconds)
        return simpleDateFormat.format(time)
    }

    fun formatUnixUtcDdMmmm(unixUTC: Int): String{
        val simpleDateFormat = SimpleDateFormat("dd MMMM")
        val unixMilliSeconds = unixUTC.toLong() * 1000
        val date = Date(unixMilliSeconds)
        return simpleDateFormat.format(date)
    }

    fun formatTemperatureKelToCel(temp: Double): String{
        return String.format("%+.0f", temp - 273.15)
    }
