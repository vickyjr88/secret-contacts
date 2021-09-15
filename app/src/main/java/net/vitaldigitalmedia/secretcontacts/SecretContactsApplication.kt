package net.vitaldigitalmedia.secretcontacts

import android.app.Application
import net.vitaldigitalmedia.secretcontacts.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class SecretContactsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@SecretContactsApplication)
            modules(listOf(appModule))
        }

    }

}