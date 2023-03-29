package com.rafael.mardom.features.pokedex.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafael.mardom.databinding.FragmentPokedexListBinding
import com.rafael.mardom.features.pokedex.presentation.adapter.PokedexListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexListFragment : Fragment() {

    private var binding: FragmentPokedexListBinding? = null
    private val pokedexListAdapter = PokedexListAdapter()
    private val viewModel by viewModels<PokedexListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokedexListBinding.inflate(inflater)
        setupView()
        return binding?.root
    }

    private fun setupView() {
        binding?.apply {
            viewModel.loadPokedex()
            pokedexList.apply {
                adapter = pokedexListAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
            pokedexListAdapter.setOnClickItem { pokemonId ->
                navigateToDetail(pokemonId)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    private fun setUpObservers() {
        val state = Observer<PokedexListViewModel.UiState> {
            if (it.error != null) {
                //TODO
            } else {
                if (it.isLoading) {
                    //TODO
                } else {
                    pokedexListAdapter.submitList(it.pokedex)
                }
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, state)
    }

    private fun navigateToDetail(pokemonId: Int) {
        findNavController().navigate(
            PokedexListFragmentDirections.actionToAdoptionsDetail(pokemonId)
        )
    }
}