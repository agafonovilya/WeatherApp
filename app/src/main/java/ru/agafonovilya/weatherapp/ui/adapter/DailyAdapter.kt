package ru.agafonovilya.weatherapp.ui.adapter

import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_daily.view.*
import ru.agafonovilya.weatherapp.mvp.model.image.IImageLoader
import ru.agafonovilya.weatherapp.ui.format.formatTemperatureKelToCel
import ru.agafonovilya.weatherapp.ui.format.formatUnixUtcDdMmmm
import javax.inject.Inject

class DailyAdapter(val view: View) {
    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    fun update(unixUTC: Int, iconURL: String, description: String,
               morningTemp: Double, dayTemp: Double, eveningTemp: Double, nightTemp: Double,
               wind: Double, humidity: Int) {

        with(view) {
            tv_daily_date.text = formatUnixUtcDdMmmm(unixUTC)
            tv_daily_description.text = description
            tv_daily_morningTemp.text = formatTemperatureKelToCel(morningTemp)
            tv_daily_dayTemp.text = formatTemperatureKelToCel(dayTemp)
            tv_daily_eveningTemp.text = formatTemperatureKelToCel(eveningTemp)
            tv_daily_nightTemp.text = formatTemperatureKelToCel(nightTemp)
            tv_daily_wind.text = wind.toString()
            tv_daily_humidity.text = humidity.toString()
            imageLoader.loadInto(iconURL, iv_daily_weatherIcon)
        }
    }


}