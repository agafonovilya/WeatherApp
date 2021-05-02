package ru.agafonovilya.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main_screen.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.agafonovilya.weatherapp.App
import ru.agafonovilya.weatherapp.R
import ru.agafonovilya.weatherapp.mvp.model.entity.OneCallRequest
import ru.agafonovilya.weatherapp.mvp.presenter.MainScreenPresenter
import ru.agafonovilya.weatherapp.mvp.view.IMainScreenView
import ru.agafonovilya.weatherapp.ui.IBackButtonListener
import ru.agafonovilya.weatherapp.ui.adapter.CurrentAdapter
import ru.agafonovilya.weatherapp.ui.adapter.HourlyRvAdapter
import ru.agafonovilya.weatherapp.ui.adapter.DailyRvAdapter

class MainScreenFragment: MvpAppCompatFragment(), IMainScreenView, IBackButtonListener {

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    val presenter: MainScreenPresenter by moxyPresenter {
        MainScreenPresenter().apply { App.instance.appComponent.inject(this) }
    }

    private var currentAdapter: CurrentAdapter? = null
    private var hourlyAdapter: HourlyRvAdapter? = null
    private var dailyAdapter: DailyRvAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View = View.inflate(context, R.layout.fragment_main_screen, null)

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun backPressed() = presenter.backPressed()

    override fun init() {
        rv_hourly.layoutManager = LinearLayoutManager(context)
        hourlyAdapter = HourlyRvAdapter(presenter.hourlyListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
        rv_hourly.adapter = hourlyAdapter

        rv_daily.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        dailyAdapter = DailyRvAdapter(presenter.dailyListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
        rv_daily.adapter = dailyAdapter

        currentAdapter = view?.let {
            CurrentAdapter(it).apply { App.instance.appComponent.inject(this) }
        }

        settings_button.setOnClickListener { presenter.settingsPressed() }
        location_btn.setOnClickListener{ presenter.locationPressed()}
        swipe_refresh.setOnRefreshListener { presenter.swipeRefreshLayout() }
    }

    override fun getDataFromSplashActivity() {
        val parcelableData = requireActivity().intent.getParcelableExtra<OneCallRequest>("OneCallRequest")
        presenter.checkDataFromSplashActivity(parcelableData)
    }

    override fun updateCurrentView(temperature: Double, cityName: String, description: String, url: String) {
        currentAdapter?.update(temperature, cityName, description, url)
    }

    override fun updateHourlyList() {
        hourlyAdapter?.notifyDataSetChanged()
    }

    override fun updateDailyList() {
        dailyAdapter?.notifyDataSetChanged()
    }

    override fun setRefreshIcon(icon: Boolean) {
        swipe_refresh.isRefreshing = icon
    }

}