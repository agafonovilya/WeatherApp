package ru.agafonovilya.weatherapp.mvp.model.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.agafonovilya.weatherapp.mvp.model.entity.Current
import ru.agafonovilya.weatherapp.mvp.model.entity.Daily
import ru.agafonovilya.weatherapp.mvp.model.entity.Hourly

class OneCallRequest() : Parcelable{
    @SerializedName("current")
    @Expose
    var current: Current? = null

    @SerializedName("hourly")
    @Expose
    var hourly: ArrayList<Hourly>? = null

    @SerializedName("daily")
    @Expose
    var daily: ArrayList<Daily>? = null

    constructor(parcel: Parcel) : this() {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OneCallRequest> {
        override fun createFromParcel(parcel: Parcel): OneCallRequest {
            return OneCallRequest(parcel)
        }

        override fun newArray(size: Int): Array<OneCallRequest?> {
            return arrayOfNulls(size)
        }
    }
}