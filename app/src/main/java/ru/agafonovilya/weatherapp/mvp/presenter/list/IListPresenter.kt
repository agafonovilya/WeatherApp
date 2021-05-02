package ru.agafonovilya.weatherapp.mvp.presenter.list

import ru.agafonovilya.weatherapp.mvp.view.list.IItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}