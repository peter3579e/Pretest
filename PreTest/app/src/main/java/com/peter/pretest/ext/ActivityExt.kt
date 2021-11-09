package com.peter.pretest.ext

import android.app.Activity
import com.peter.pretest.PretestApplication
import com.peter.pretest.factory.ViewModelFactory

fun Activity.getVmFactory(): ViewModelFactory {
    val repository = (applicationContext as PretestApplication).preTestRepository
    return ViewModelFactory(repository)
}