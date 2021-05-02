package ru.agafonovilya.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_daily.view.*
import ru.agafonovilya.weatherapp.R
import ru.agafonovilya.weatherapp.mvp.model.image.IImageLoader
import ru.agafonovilya.weatherapp.mvp.presenter.list.IDailyListPresenter
import ru.agafonovilya.weatherapp.mvp.view.list.IDailyItemView
import ru.agafonovilya.weatherapp.ui.format.formatUnixUtcDdMmmm
import ru.agafonovilya.weatherapp.ui.format.formatTemperatureKelToCel
import javax.inject.Inject

class DailyRvAdapter(val presenter: IDailyListPresenter):
        RecyclerView.Adapter<DailyRvAdapter.ViewHolder>() {

    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_daily, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(override val containerView: View):
            RecyclerView.ViewHolder(containerView), LayoutContainer, IDailyItemView {

        override var pos = -1
        override var unixUTC: Int = -1

        override fun setDate(date: Int) = with(containerView){
            unixUTC = date
            weekCard_date.text = formatUnixUtcDdMmmm(date)
        }

        override fun setDayTemperature(dayTemperature: Double) = with(containerView){
            weekCard_dayTemperature.text = formatTemperatureKelToCel(dayTemperature)
        }

        override fun setNightTemperature(nightTemperature: Double) = with(containerView) {
            weekCard_nightTemperature.text = formatTemperatureKelToCel(nightTemperature)
        }

        override fun loadIcon(url: String) = with(containerView){
            imageLoader.loadInto(url, weekCard_icon)
        }
    }
}