package ru.agafonovilya.weatherapp.ui.adapter

import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_main_screen.view.*
import ru.agafonovilya.weatherapp.mvp.model.image.IImageLoader
import ru.agafonovilya.weatherapp.ui.format.formatTemperatureKelToCel
import javax.inject.Inject

class CurrentAdapter(val view: View) {
    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    fun update(temperature: Double?, cityName: String?, description: String?, url: String?){
        with(view){
            mainScreen_currentTemperature.text = temperature?.let { formatTemperatureKelToCel(it) }
            mainScreen_cityName.text = cityName
            mainScreen_currentWeatherDescription.text = description
            url?.let { imageLoader.loadInto(it, mainScreen_currentWeatherIcon) }
        }
    }

}