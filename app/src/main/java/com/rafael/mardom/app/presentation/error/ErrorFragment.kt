package com.rafael.mardom.app.presentation.error

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.rafael.mardom.NavGraphDirections
import com.rafael.mardom.databinding.FragmentErrorBinding
import com.rafael.mardom.features.pokedex.presentation.PokemonDetailFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
open class ErrorFragment @Inject constructor() : Fragment() {

    private var binding: FragmentErrorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentErrorBinding.inflate(inflater)
        setupView()
        return binding?.root
    }

    protected fun setupView(){
        binding?.apply {
            swipeRefreshLayout.apply {
                setOnRefreshListener {
                    findNavController().navigate(
                        ErrorFragmentDirections.actionToPokedex()
                    )
                }
            }
        }
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