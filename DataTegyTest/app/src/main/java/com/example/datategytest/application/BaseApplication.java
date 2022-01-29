package com.example.datategytest.application;

import android.app.Application;
import android.content.Context;

import com.example.datategytest.di.components.DaggerAppComponent;
import com.example.datategytest.di.modules.AppModule;
import com.example.datategytest.di.modules.ContextModule;
import com.example.datategytest.di.components.AppComponent;


public class BaseApplication extends Application {

    private AppComponent component;

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .contextModule(new ContextModule(this))
                .build();
    }

    public AppComponent component() {
        return component;
    }
}
