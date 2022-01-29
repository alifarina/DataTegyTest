package com.example.datategytest.di.modules;

import android.app.Application;

import com.example.datategytest.application.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private BaseApplication initApplication;

    public AppModule(BaseApplication initApplication) {
        this.initApplication = initApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return initApplication;
    }
}
