package com.foivos.mycryptoapp.util

import androidx.navigation.NavController
import org.junit.Assert
import timber.log.Timber

fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    val actualRoute = currentBackStackEntry?.destination?.route
    Assert.assertEquals(expectedRouteName, actualRoute)
}