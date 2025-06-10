package com.l3on1kl.sequeniatestapp.presentation.film_detail

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.MaterialToolbar
import com.l3on1kl.sequeniatestapp.R
import com.l3on1kl.sequeniatestapp.ui.teme.SequeniaTheme
import com.l3on1kl.sequeniatestapp.utils.collectWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmDetailFragment : Fragment(R.layout.fragment_film_detail) {

    private val args: FilmDetailFragmentArgs by navArgs()
    private val viewModel: FilmDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadFilm(args.filmId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        viewModel.uiState.collectWithLifecycle(viewLifecycleOwner) { state ->
            if (state is FilmDetailUiState.Success) {
                toolbar.title = state.film.name.ifBlank { state.film.localizedName }
            }
        }

        view.findViewById<ComposeView>(R.id.composeContent).setContent {
            SequeniaTheme {
                FilmDetailContent(viewModel = viewModel)
            }
        }
    }
}
