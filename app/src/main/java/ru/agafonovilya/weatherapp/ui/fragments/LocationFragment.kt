package ru.agafonovilya.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_location.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.agafonovilya.weatherapp.App
import ru.agafonovilya.weatherapp.R
import ru.agafonovilya.weatherapp.di.location.LocationSubcomponent
import ru.agafonovilya.weatherapp.mvp.presenter.LocationPresenter
import ru.agafonovilya.weatherapp.mvp.view.ILocationScreenView
import ru.agafonovilya.weatherapp.ui.IBackButtonListener

class LocationFragment: MvpAppCompatFragment(), ILocationScreenView, IBackButtonListener {
    companion object{
        fun newInstance() = LocationFragment()
    }

    var locationSubcomponent: LocationSubcomponent? = null

    val presenter: LocationPresenter by moxyPresenter {
        locationSubcomponent = App.instance.initLocationSubcomponent()
        LocationPresenter().apply { locationSubcomponent?.inject(this) }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_location, null)

    override fun init() {
        backButton.setOnClickListener { presenter.backPressed() }
    }

    override fun initAutoCompeteTextView(citiesList: List<String>) {

        actv_location.threshold = 1 //Минимальное кол-во символов до начала показа подходящих вариантов

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, citiesList)
        actv_location.setAdapter(adapter)

        // Устанавливаем слушатель на прикосновение к строке ввода города, чтобы сразу открывался весь доступный список городов
        actv_location.setOnTouchListener { v, _ ->
            v.performClick()
            actv_location.showDropDown()
            false
        }

        // Устанавливаем слушатель на выбор города, с последующим переходом на основной экран
        actv_location.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            presenter.locationChanged(citiesList[position])
        }
    }

    override fun release() {
        locationSubcomponent = null
        App.instance.releaseLocationSubcomponent()
    }

    override fun backPressed() = presenter.backPressed()

}