package ru.agafonovilya.weatherapp.mvp.presenter

import moxy.MvpPresenter
import ru.agafonovilya.weatherapp.mvp.view.IMainView
import ru.agafonovilya.weatherapp.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter: MvpPresenter<IMainView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.MainScreen())
    }

    fun backClicked() {
        router.exit()
    }
}