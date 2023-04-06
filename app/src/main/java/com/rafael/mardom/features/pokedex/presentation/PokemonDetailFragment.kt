package com.rafael.mardom.features.pokedex.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rafael.mardom.app.extensions.loadUrl
import com.rafael.mardom.databinding.FragmentPokemonDetailBinding
import com.rafael.mardom.features.pokedex.domain.GetPokemonByIdUseCase.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {
    private var binding: FragmentPokemonDetailBinding? = null
    private val viewModel by viewModels<PokemonDetailViewModel>()
    private val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding.inflate(inflater)
        setupView()
        return binding?.root
    }

    private fun setupView() {
        binding?.apply {
            toolbar.apply {
                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.loadAnimal(args.pokemonId)
    }

    private fun setupObservers() {
        val state = Observer<PokemonDetailViewModel.UiState> {
            if (it.error != null) {
                // TODO
            } else {
                if (it.isLoading) {
                    // TODO
                } else {
                    it.pokemon?.let { model ->
                        bind(model)
                    }
                }
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, state)
    }

    private fun bind(model: PokemonDetail) {
        binding?.apply {
            pokemonDescription.text = model.description
            pokemonHeight.text = model.height.toString()
            pokemonWeight.text = model.weight.toString()
            pokemonSprite.loadUrl(model.sprites.front_default)
            pokemonType1.text = model.types[0]
            pokemonType2.text = if (model.types.size > 1) model.types[1] else ""

            toolbar.apply {
                title = "# ${model.id}  - ${model.name.uppercase()}"
                toolbarSprite.loadUrl(model.sprites.front_default)
            }

            buildStatsChart(model.stats)
        }
    }

    private fun buildStatsChart(stats: List<PokemonDetailStats>) {
        val animationDuration = 1000L

        val chartSet = mutableListOf<Pair<String, Float>>()

        stats.forEach{
            chartSet.add(
                "${it.base} - ${it.name.uppercase()}" to ((it.base).toFloat()))
        }

        chartSet.reverse()

        binding?.apply {
            statsChart.animation.duration = animationDuration
            statsChart.animate(chartSet)
        }
    }


}