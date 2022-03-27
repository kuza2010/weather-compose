package ru.adanil.weather

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.adanil.weather.dagger.AppModule
import ru.adanil.weather.ui.screens.splashscreen.SplashScreenViewModel

@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun splashScreenViewModel(): SplashScreenViewModel.Factory

}