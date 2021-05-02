package ru.agafonovilya.weatherapp.di.module

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.agafonovilya.weatherapp.App
import ru.agafonovilya.weatherapp.mvp.model.cache.IImageCache
import ru.agafonovilya.weatherapp.mvp.model.cache.room.RoomImageCache
import ru.agafonovilya.weatherapp.mvp.model.entity.room.Database
import ru.agafonovilya.weatherapp.mvp.model.image.IImageLoader
import ru.agafonovilya.weatherapp.mvp.model.network.INetworkStatus
import ru.agafonovilya.weatherapp.ui.image.GlideImageLoader
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageModule {

    @Named("cacheDir")
    @Singleton
    @Provides
    fun cacheDir(app: App): File = app.cacheDir

    @Singleton
    @Provides
    fun imageCache(database: Database, @Named("cacheDir") cacheDir: File): IImageCache = RoomImageCache(database, cacheDir)

    @Provides
    fun imageLoader(cache: IImageCache, networkStatus: INetworkStatus): IImageLoader<ImageView> = GlideImageLoader(cache, networkStatus)

}