package net.vitaldigitalmedia.secretcontacts

import androidx.multidex.MultiDexApplication
import net.vitaldigitalmedia.secretcontacts.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            modules(listOf(appModule))
        }

    }

}