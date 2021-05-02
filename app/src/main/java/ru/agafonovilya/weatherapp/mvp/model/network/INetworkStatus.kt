package ru.agafonovilya.weatherapp.mvp.model.network

import io.reactivex.rxjava3.core.Single

interface INetworkStatus{
    fun isOnlineSingle(): Single<Boolean>
}