package com.leandroid.system.pr_econocom
import android.app.Application
import com.leandroid.system.pr_econocom.restaurant.di.restaurantModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            koin.loadModules(
                listOf(
                    restaurantModule,
                )

            )
        }
    }
}