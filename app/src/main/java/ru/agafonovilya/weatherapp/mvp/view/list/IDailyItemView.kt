package ru.agafonovilya.weatherapp.mvp.view.list

interface IDailyItemView: IItemView {
    var unixUTC: Int
    fun setDate(date: Int)
    fun setDayTemperature(dayTemperature: Double)
    fun setNightTemperature(nightTemperature: Double)
    fun loadIcon(url: String)
}