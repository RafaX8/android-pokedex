package com.rafael.mardom.app.presentation.error

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafael.mardom.databinding.FragmentErrorBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class ErrorFragment @Inject constructor() : Fragment() {

    private var binding: FragmentErrorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentErrorBinding.inflate(inflater)
        return binding?.root
    }

    protected fun setErrorTitle(title: String) {
        binding?.apply {
            labelTitleError.text = title
        }
    }

    protected fun setErrorDescription(description: String) {
        binding?.apply {
            labelDescError.text = description
        }
    }
}