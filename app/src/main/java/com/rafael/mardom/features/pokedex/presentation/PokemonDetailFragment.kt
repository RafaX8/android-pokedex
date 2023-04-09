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
import com.rafael.mardom.R
import com.rafael.mardom.app.extensions.loadUrl
import com.rafael.mardom.app.presentation.error.AppErrorHandler
import com.rafael.mardom.databinding.FragmentPokemonDetailBinding
import com.rafael.mardom.features.pokedex.domain.GetPokemonByIdUseCase.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {
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
                appErrorHandler.navigateToError(it.error)
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
        val context = requireContext()
        val typeColorPair = mapOf<String, Int>(
            "normal" to context.getColor(R.color.normal),
            "fire" to context.getColor(R.color.fire),
            "water" to context.getColor(R.color.water),
            "electric" to context.getColor(R.color.electric),
            "grass" to context.getColor(R.color.grass),
            "ice" to context.getColor(R.color.ice),
            "fighting" to context.getColor(R.color.fighting),
            "poison" to context.getColor(R.color.poison),
            "ground" to context.getColor(R.color.ground),
            "flying" to context.getColor(R.color.flying),
            "psychic" to context.getColor(R.color.psychic),
            "bug" to context.getColor(R.color.bug),
            "rock" to context.getColor(R.color.rock),
            "ghost" to context.getColor(R.color.ghost),
            "dragon" to context.getColor(R.color.dragon),
            "dark" to context.getColor(R.color.dark),
            "steel" to context.getColor(R.color.steel),
            "fairy" to context.getColor(R.color.fairy),
        )

        binding?.apply {
            pokemonDescription.text = "\"${model.description}\""
            pokemonHeight.text = model.height.toString()
            pokemonWeight.text = model.weight.toString()
            pokemonSprite.loadUrl(model.sprites.front_default)
            frontShiny.loadUrl(model.sprites.front_shiny)
            backDefault.loadUrl(model.sprites.back_default)
            backShiny.loadUrl(model.sprites.back_shiny)

            pokemonType1.apply {
                val type1 = model.types[0]
                text = type1
                typeColorPair[type1]?.let { color ->
                    background.setTint(color)
                }
            }
            pokemonType2.apply {
                if (model.types.size > 1) {
                    val type2 = model.types[1]
                    text = type2
                    typeColorPair[type2]?.let { color ->
                        pokemonType2.background.setTint(color)
                    }
                    visibility = View.VISIBLE
                } else {
                    visibility = View.GONE
                }
            }

            toolbar.apply {
                title = "# ${model.id} - ${model.name.uppercase()}"
            }

            buildStatsChart(model.stats)
        }
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
}