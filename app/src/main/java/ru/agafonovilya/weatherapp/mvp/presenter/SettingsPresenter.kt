package ru.agafonovilya.weatherapp.mvp.presenter

import moxy.MvpPresenter
import ru.agafonovilya.weatherapp.mvp.model.savedpreferences.ISavedPreferences
import ru.agafonovilya.weatherapp.mvp.view.ISettingsScreenView
import ru.agafonovilya.weatherapp.ui.sharedpreferences.SharedPrefValue
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SettingsPresenter: MvpPresenter<ISettingsScreenView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var sharedPreferences: ISavedPreferences

    override fun attachView(view: ISettingsScreenView?) {
        super.attachView(view)
        when(sharedPreferences.getSavedTheme()) {
            SharedPrefValue.THEME_LIGHT -> viewState.checkLight()
            SharedPrefValue.THEME_NIGHT -> viewState.checkNight()
        }
        viewState.init()
        viewState.animateView()
    }

    fun lightThemeSelected() {
        if (sharedPreferences.getSavedTheme() == SharedPrefValue.THEME_LIGHT) {
            viewState.checkLight()
        } else {
            sharedPreferences.saveLightTheme()
            viewState.recreateActivity()
        }
    }

    fun nightThemeSelected() {
        if (sharedPreferences.getSavedTheme() == SharedPrefValue.THEME_NIGHT) {
            viewState.checkNight()
        } else {
            sharedPreferences.saveNightTheme()
            viewState.recreateActivity()
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}