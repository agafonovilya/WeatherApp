package ru.agafonovilya.weatherapp.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomDaily (
        @PrimaryKey var dt: Int?,
        var dayTemperature: Double?,
        var nightTemperature: Double?,
        var morningTemperature: Double?,
        var eveningTemperature: Double?,
        var humidity: Int?,
        var wind: Double?,
        var description: String?,
        var icon: String?
        )