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
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.rafael.mardom.R
import com.rafael.mardom.app.presentation.error.AppErrorHandler
import com.rafael.mardom.databinding.FragmentPokedexListBinding
import com.rafael.mardom.features.pokedex.presentation.adapter.PokedexListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokedexListFragment : Fragment() {
    private var skeleton: Skeleton? = null
    private var binding: FragmentPokedexListBinding? = null
    private val pokedexListAdapter = PokedexListAdapter()
    private val viewModel by viewModels<PokedexListViewModel>()

    @Inject
    lateinit var appErrorHandler: AppErrorHandler

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
            toolbar.apply {
                favoriteAction.setOnClickListener {
                    if (!(viewModel.currentUiState.areFavoritesLoaded)) {
                        showFavourites()
                    } else {
                        showUnfavourites()
                    }
                }
            }
            pokedexList.apply {
                adapter = pokedexListAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                skeleton = applySkeleton(R.layout.view_item_pokedex_pokemon, 9)
            }
            pokedexListAdapter.setOnClickItem { pokemonId ->
                navigateToDetail(pokemonId)
            }
            swipeRefreshLayout.apply {
                setOnRefreshListener {
                    viewModel.refresh()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.currentUiState.areFavoritesLoaded) {
            showFavourites()
        } else {
            showUnfavourites()
        }
    }

    private fun showFavourites() {
        viewModel.currentUiState.areFavoritesLoaded = true
        updateFavIcon(true)
        viewModel.loadFavoritesPokedex()
    }

    private fun showUnfavourites() {
        viewModel.currentUiState.areFavoritesLoaded = false
        updateFavIcon(false)
        viewModel.loadPokedex()
    }

    private fun updateFavIcon(isFavorite: Boolean) {
        binding?.apply {
            toolbar.apply {
                favoriteAction.setImageResource(
                    if (isFavorite) R.drawable.ic_favorite_fill else R.drawable.ic_favorite
                )
            }
        }
    }

    private fun setUpObservers() {
        val state = Observer<PokedexListViewModel.UiState> {
            if (it.isLoading) {
                skeleton?.showSkeleton()
            } else {
                skeleton?.showOriginal()
                if (it.error != null) {
                    appErrorHandler.navigateToError(it.error)
                } else {
                    pokedexListAdapter.submitList(it.pokedex)
                }
                binding?.swipeRefreshLayout?.isRefreshing = false
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, state)
    }

    private fun navigateToDetail(pokemonId: Int) {
        findNavController().navigate(
            PokedexListFragmentDirections.actionToPokemonDetail(pokemonId)
        )
    }
}