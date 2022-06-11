package com.example.bankaccount_task

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//first step to use dependency injection
//dagger is compile time so when compile must now which dependency[class variable on constructor] inject to which classes
@HiltAndroidApp
class BasicApplication:Application() {

}