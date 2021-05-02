package ru.agafonovilya.weatherapp.ui.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_settings.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.agafonovilya.weatherapp.App
import ru.agafonovilya.weatherapp.R
import ru.agafonovilya.weatherapp.mvp.presenter.SettingsPresenter
import ru.agafonovilya.weatherapp.mvp.view.ISettingsScreenView
import ru.agafonovilya.weatherapp.ui.IBackButtonListener

class SettingsFragment: MvpAppCompatFragment(), ISettingsScreenView, IBackButtonListener {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    val presenter: SettingsPresenter by moxyPresenter {
        SettingsPresenter().apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_settings,null)

    override fun init() {
        fragment_settings_backButton.setOnClickListener {
            presenter.backPressed() }

        setting_fragment_chipGroup_theme.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.setting_fragment_chip_light -> {
                    presenter.lightThemeSelected()
                }
                R.id.setting_fragment_chip_night -> {
                    presenter.nightThemeSelected()
                }
            }
        }
    }

    override fun recreateActivity() {
        activity?.recreate()
    }

    override fun checkLight() {
        setting_fragment_chipGroup_theme.check(setting_fragment_chip_light.id)
    }

    override fun checkNight() {
        setting_fragment_chipGroup_theme.check(setting_fragment_chip_night.id)
    }

    override fun animateView() {
        with(ObjectAnimator
                .ofFloat(setting_fragment_chipGroup_theme,
                        "rotation",
                        0f,
                        360f)) {
            duration = 1000
            start()
        }
    }

    override fun backPressed() = presenter.backPressed()
}