package net.vitaldigitalmedia.secretcontacts.modules

import net.vitaldigitalmedia.secretcontacts.ui.ContactViewModel
import net.vitaldigitalmedia.secretcontacts.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    viewModel { MainViewModel(get()) }
    viewModel { ContactViewModel(get()) }
}