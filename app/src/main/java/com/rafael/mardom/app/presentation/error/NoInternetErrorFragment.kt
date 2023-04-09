package com.rafael.mardom.app.presentation.error

import android.os.Bundle
import android.view.View
import com.rafael.mardom.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoInternetErrorFragment : ErrorFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val errorDescription: String = getString(R.string.label_desc_no_internet_error)
        val errorTitle: String = getString(R.string.label_title_no_internet_error)

        setErrorDescription(errorDescription)
        setErrorTitle(errorTitle)
    }
}