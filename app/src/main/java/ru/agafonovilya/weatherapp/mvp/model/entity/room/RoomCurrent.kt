package ru.agafonovilya.weatherapp.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomCurrent (
        @PrimaryKey val dt: Int?,
        val temp: Double?,
        val description: String?,
        val icon: String?) {
}