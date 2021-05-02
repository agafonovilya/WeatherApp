package ru.agafonovilya.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_hourly.view.*
import ru.agafonovilya.weatherapp.R
import ru.agafonovilya.weatherapp.mvp.model.image.IImageLoader
import ru.agafonovilya.weatherapp.mvp.presenter.list.IHourlyListPresenter
import ru.agafonovilya.weatherapp.mvp.view.list.IHourlyItemView
import ru.agafonovilya.weatherapp.ui.format.formatTemperatureKelToCel
import ru.agafonovilya.weatherapp.ui.format.formatUnixUtcHhMm

import javax.inject.Inject

class HourlyRvAdapter(val presenter: IHourlyListPresenter):
    RecyclerView.Adapter<HourlyRvAdapter.ViewHolder>() {

    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_hourly, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(override val containerView: View):
        RecyclerView.ViewHolder(containerView), LayoutContainer, IHourlyItemView {

        override var pos = -1

        override fun setTime(time: Int) = with(containerView) {
            hourlyCard_time.text = formatUnixUtcHhMm(time)
        }

        override fun setTemperature(temperature: Double) = with(containerView) {
            hourlyCard_temperature.text = formatTemperatureKelToCel(temperature)
        }

        override fun loadIcon(url: String) = with(containerView){
            imageLoader.loadInto(url, hourlyCard_icon)
        }

    }
}