package ru.adanil.weather.di.coroutine

import javax.inject.Qualifier

/*
Check this out: https://medium.com/androiddevelopers/create-an-application-coroutinescope-using-hilt-dd444e721528
 */

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainImmediateDispatcher
