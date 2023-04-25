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
import com.faltenreich.skeletonlayout.Skeleton
import com.rafael.mardom.R
import com.rafael.mardom.app.extensions.ColorTypePairing
import com.rafael.mardom.app.extensions.loadUrl
import com.rafael.mardom.app.presentation.error.AppErrorHandler
import com.rafael.mardom.databinding.FragmentPokemonDetailBinding
import com.rafael.mardom.features.pokedex.domain.GetPokemonByIdUseCase.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {
    private var skeleton: Skeleton? = null
    private var binding: FragmentPokemonDetailBinding? = null
    private val viewModel by viewModels<PokemonDetailViewModel>()
    private val args: PokemonDetailFragmentArgs by navArgs()

    @Inject
    lateinit var appErrorHandler: AppErrorHandler

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
                    navigateToPokedex()
                }
            }
            skeleton = skeletonDetail
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.loadAnimal(args.pokemonId)
    }

    private fun setupObservers() {
        val state = Observer<PokemonDetailViewModel.UiState> {
            if (it.isLoading) {
                skeleton?.showSkeleton()
            } else {
                skeleton?.showOriginal()
                if (it.error != null) {
                    appErrorHandler.navigateToError(it.error)
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
        val context = requireContext()
        val typeColorPair = ColorTypePairing.typeColorPair

        binding?.apply {
            pokemonDescription.text = "\"${model.description}\""
            pokemonHeight.text = model.height.toString()
            pokemonWeight.text = model.weight.toString()
            pokemonSprite.loadUrl(model.sprites.frontDefault)
            frontShiny.loadUrl(model.sprites.frontShiny)
            backDefault.loadUrl(model.sprites.backDefault)
            backShiny.loadUrl(model.sprites.backShiny)

            pokemonType1.apply {
                val type1 = model.types[0]
                text = type1
                typeColorPair[type1]?.let { colorId ->
                    background.setTint(context.getColor(colorId))
                }
            }
            pokemonType2.apply {
                if (model.types.size > 1) {
                    val type2 = model.types[1]
                    text = type2
                    typeColorPair[type2]?.let { colorId ->
                        background.setTint(context.getColor(colorId))
                    }
                    visibility = View.VISIBLE
                } else {
                    visibility = View.GONE
                }
            }

            toolbar.apply {
                title = "# ${model.id} - ${model.name.uppercase()}"
                updateFavIcon(model.isFavorite)
                favoriteAction.setOnClickListener {
                    if (model.isFavorite) {
                        unfavoriteSelected()
                    } else {
                        favoriteSelected()
                    }
                }
            }

            buildStatsChart(model.stats)

            beforeAction.setOnClickListener {
                navigateBefore(model.id)
            }
            nextAction.setOnClickListener {
                navigateNext(model.id)
            }
        }
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

    private fun favoriteSelected() {
        updateFavIcon(true)
        viewModel.addToFavorite(args.pokemonId)
    }

    private fun unfavoriteSelected() {
        updateFavIcon(false)
        viewModel.removeFromFavorite(args.pokemonId)
    }


    private fun buildStatsChart(stats: List<PokemonDetailStats>) {
        val animationDuration = 1000L

        val chartSet = mutableListOf<Pair<String, Float>>()

        var total = 0

        stats.forEach {
            total += it.base
            chartSet.add(
                "${it.base} - ${it.name.uppercase()}" to ((it.base).toFloat())
            )
        }
        chartSet.add("$total - STATS TOTAL" to 0F)

        chartSet.reverse()

        binding?.apply {
            statsChart.barRadius = 12F
            statsChart.spacing = 32F
            statsChart.animation.duration = animationDuration
            statsChart.animate(chartSet)
        }
    }

    private fun navigateToPokedex() {
        findNavController().navigate(
            PokemonDetailFragmentDirections.actionToPokedex()
        )
    }

    private fun navigateBefore(pokemonId: Int) {
        if (pokemonId > 1)
            findNavController().navigate(
                PokemonDetailFragmentDirections.actionToPokemonDetail(pokemonId - 1)
            )
    }

    private fun navigateNext(pokemonId: Int) {
        if (pokemonId < 151)
            findNavController().navigate(
                PokemonDetailFragmentDirections.actionToPokemonDetail(pokemonId + 1)
            )
    }
}