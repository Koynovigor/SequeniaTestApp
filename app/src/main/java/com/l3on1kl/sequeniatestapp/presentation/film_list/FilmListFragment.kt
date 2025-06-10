package com.l3on1kl.sequeniatestapp.presentation.film_list

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.l3on1kl.sequeniatestapp.R
import com.l3on1kl.sequeniatestapp.ui.teme.SequeniaTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmListFragment : Fragment(R.layout.fragment_film_list) {

    private val viewModel: FilmListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        @Suppress("DEPRECATION")
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        @Suppress("DEPRECATION")
        ViewCompat.getWindowInsetsController(requireActivity().window.decorView)
            ?.isAppearanceLightStatusBars = false
        @Suppress("DEPRECATION")
        requireActivity().window.navigationBarColor =
            ContextCompat.getColor(requireContext(), R.color.colorBackground)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setTitle(R.string.films_title)

        val composeView = view.findViewById<ComposeView>(R.id.composeContent)
        composeView.setContent {
            SequeniaTheme {
                FilmListContent(
                    viewModel = viewModel,
                    onFilmClick = { id ->
                        val action =
                            FilmListFragmentDirections.actionFilmListToFilmDetail(id)
                        findNavController().navigate(action)
                    }
                )
            }
        }
    }
}
