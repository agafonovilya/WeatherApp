package ru.agafonovilya.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_daily.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.agafonovilya.weatherapp.App
import ru.agafonovilya.weatherapp.R
import ru.agafonovilya.weatherapp.di.daily.DailySubcomponent
import ru.agafonovilya.weatherapp.mvp.presenter.DailyPresenter
import ru.agafonovilya.weatherapp.mvp.view.IDailyScreenView
import ru.agafonovilya.weatherapp.ui.IBackButtonListener
import ru.agafonovilya.weatherapp.ui.adapter.DailyAdapter

class DailyFragment: MvpAppCompatFragment(), IDailyScreenView, IBackButtonListener {

    companion object{
        private var unixUTC: Int = -1

        fun newInstance(unixUTC: Int): DailyFragment {
            val dailyFragment = DailyFragment()
            Companion.unixUTC = unixUTC
            return dailyFragment
        }
    }

    var dailySubcomponent: DailySubcomponent? = null

    val presenter: DailyPresenter by moxyPresenter {
        dailySubcomponent = App.instance.initDailySubcomponent()
        DailyPresenter(unixUTC).apply { dailySubcomponent?.inject(this) }
    }

    private var dailyAdapter: DailyAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_daily, null)

    override fun init() {
        dailyAdapter = view?.let {
            DailyAdapter(it).apply { dailySubcomponent?.inject(this) }
        }

        btn_daily_backButton.setOnClickListener { presenter.backPressed() }
    }

    override fun updateWeatherView(unixUTC: Int, iconURL: String, description: String,
                                   morningTemp: Double, dayTemp: Double, eveningTemp: Double,
                                   nightTemp: Double, wind: Double, humidity: Int) {
        dailyAdapter?.update(unixUTC, iconURL, description, morningTemp, dayTemp, eveningTemp,
                            nightTemp, wind, humidity)
    }


    override fun release() {
        dailySubcomponent = null
        App.instance.releaseDailySubcomponent()
    }

    override fun backPressed() = presenter.backPressed()
}