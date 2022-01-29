package com.example.datategytest.di.components;

import android.app.Application;
import android.content.Context;

import com.example.datategytest.application.BaseApplication;
import com.example.datategytest.di.modules.AppModule;
import com.example.datategytest.di.modules.ContextModule;
import com.example.datategytest.di.modules.MvpModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        ContextModule.class,

})
public interface AppComponent {

    void inject(BaseApplication initApplication);

    Context getContext();

    Application getApplication();


}
