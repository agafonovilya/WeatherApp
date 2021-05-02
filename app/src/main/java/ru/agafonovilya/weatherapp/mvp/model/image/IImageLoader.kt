package ru.agafonovilya.weatherapp.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}