package ru.agafonovilya.weatherapp.mvp.view.list

interface IHourlyItemView: IItemView {
    fun setTime(time: Int)
    fun setTemperature(temperature: Double)
    fun loadIcon(url: String)
}