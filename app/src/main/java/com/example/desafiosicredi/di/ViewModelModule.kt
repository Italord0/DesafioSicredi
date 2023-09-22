package com.example.desafiosicredi.di

import com.example.desafiosicredi.nav.MyRouteNavigator
import com.example.desafiosicredi.nav.RouteNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindRouteNavigator() : RouteNavigator = MyRouteNavigator()
}