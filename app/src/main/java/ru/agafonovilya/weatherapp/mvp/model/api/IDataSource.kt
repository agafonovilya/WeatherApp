package ru.agafonovilya.weatherapp.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.agafonovilya.weatherapp.mvp.model.entity.OneCallRequest

interface IDataSource {

    //Load weather with One Call API Request
    @GET("data/2.5/onecall")
    fun loadWeather(@Query("lat") latitude: String,
                    @Query("lon") longitude: String,
                    @Query(" exclude") exclude: String,
                    @Query("appid") keyApi: String,
                    @Query("lang") lang: String) : Single<OneCallRequest>
}