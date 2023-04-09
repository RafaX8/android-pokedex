package com.rafael.mardom.app.presentation.error

import android.content.Context
import androidx.navigation.Navigation
import com.rafael.mardom.MainActivity
import com.rafael.mardom.app.domain.ErrorApp
import com.rafael.mardom.NavGraphDirections
import com.rafael.mardom.R
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class AppErrorHandler @Inject constructor(@ActivityContext private val context: Context) {

    fun navigateToError(errorApp: ErrorApp) {
        val activity = (context as MainActivity)
        val navFragment = R.id.nav_host_fragment
        when (errorApp) {
            is ErrorApp.DataError ->
                Navigation.findNavController(activity, navFragment)
                    .navigate(NavGraphDirections.actionToDataError())
            is ErrorApp.NoInternetError ->
                Navigation.findNavController(activity, navFragment)
                    .navigate(NavGraphDirections.actionToDataError())
            is ErrorApp.TimeOutError ->
                Navigation.findNavController(activity, navFragment)
                    .navigate(NavGraphDirections.actionToDataError())
            else ->
                Navigation.findNavController(activity, navFragment)
                    .navigate(NavGraphDirections.actionToUnknownError())
        }
    }
}