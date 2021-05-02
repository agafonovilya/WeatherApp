package ru.agafonovilya.weatherapp.ui.activity

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.agafonovilya.weatherapp.R
import ru.agafonovilya.weatherapp.App
import ru.agafonovilya.weatherapp.mvp.model.savedpreferences.ISavedPreferences
import ru.agafonovilya.weatherapp.mvp.presenter.MainPresenter
import ru.agafonovilya.weatherapp.mvp.view.IMainView
import ru.agafonovilya.weatherapp.ui.IBackButtonListener
import ru.agafonovilya.weatherapp.ui.sharedpreferences.SharedPrefValue
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), IMainView {

    @Inject lateinit var navigatorHolder: NavigatorHolder
    @Inject lateinit var sharedPreferences: ISavedPreferences

    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    val presenter: MainPresenter by moxyPresenter {
        MainPresenter().apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        when(sharedPreferences.getSavedTheme()) {
            SharedPrefValue.THEME_LIGHT -> setTheme(R.style.Theme_WeatherApp_Light)
            SharedPrefValue.THEME_NIGHT -> setTheme(R.style.Theme_WeatherApp_Night)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is IBackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}