package ru.agafonovilya.weatherapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.animation.LinearInterpolator
import kotlinx.android.synthetic.main.activity_splash.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.agafonovilya.weatherapp.App
import ru.agafonovilya.weatherapp.R
import ru.agafonovilya.weatherapp.mvp.model.savedpreferences.ISavedPreferences
import ru.agafonovilya.weatherapp.mvp.presenter.SplashPresenter
import ru.agafonovilya.weatherapp.mvp.view.ISplashView
import ru.agafonovilya.weatherapp.ui.sharedpreferences.SharedPrefValue
import javax.inject.Inject

class SplashActivity : MvpAppCompatActivity(), ISplashView {

    @Inject lateinit var sharedPreferences: ISavedPreferences

    val presenter: SplashPresenter by moxyPresenter {
        SplashPresenter().apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        when(sharedPreferences.getSavedTheme()) {
            SharedPrefValue.THEME_LIGHT -> setTheme(R.style.Theme_WeatherApp_Light)
            SharedPrefValue.THEME_NIGHT -> setTheme(R.style.Theme_WeatherApp_Night)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun startAnimation() {
        image_view.animate().rotationBy(750f)
            .setInterpolator(LinearInterpolator()).duration = 10000
    }

    override fun startActivity(parcelable: Parcelable) {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
            .apply { putExtra("OneCallRequest", parcelable) }
        startActivity(intent)
        finish()
    }

}