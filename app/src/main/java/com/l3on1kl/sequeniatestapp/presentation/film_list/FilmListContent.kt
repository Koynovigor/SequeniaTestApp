package com.l3on1kl.sequeniatestapp.presentation.film_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.l3on1kl.sequeniatestapp.R
import com.l3on1kl.sequeniatestapp.domain.model.FilmEntity

@Composable
fun FilmListContent(
    viewModel: FilmListViewModel,
    onFilmClick: (Int) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    val selectedGenre by viewModel.selectedGenre.collectAsState()
    val filtered by viewModel.filteredFilms.collectAsState()

    val spanCount = 2

    when (val state = uiState.value) {
        is FilmListUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is FilmListUiState.Content -> {
            val content = uiState.value as FilmListUiState.Content
            val genres = content.genres

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp)
                    .background(colorResource(R.color.colorBackground)),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Column {
                        Text(
                            text = stringResource(R.string.genres_title),
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(
                                top = 16.dp,
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 10.dp
                            )
                        )

                        genres.forEach { genre ->
                            GenreRow(
                                genre = genre,
                                selected = genre == selectedGenre,
                                onClick = { viewModel.onGenreSelected(genre.takeIf { it != selectedGenre }) }
                            )
                        }
                    }
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text(
                        text = stringResource(R.string.films_title),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                itemsIndexed(
                    items = filtered,
                    key = { _, film -> film.id }
                ) { index, film ->

                    val col = index % spanCount
                    val isFirst = col == 0
                    val isLast = col == spanCount - 1

                    val cardModifier = Modifier.padding(
                        start = if (isFirst) 16.dp else 0.dp,
                        end = if (isLast) 16.dp else 0.dp
                    )

                    FilmCard(
                        film,
                        modifier = cardModifier,
                        onClick = { onFilmClick(film.id) }
                    )
                }
            }
        }

        is FilmListUiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: ${state.message}")
            }
        }
    }
}

@Composable
fun FilmCard(
    film: FilmEntity,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.6f)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.colorBackground)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            AsyncImage(
                model = film.imageUrl,
                contentDescription = film.localizedName,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_placeholder),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .weight(1f)
            )
            Text(
                text = film.localizedName,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(48.dp)
            )
        }
    }
}

@Composable
private fun GenreRow(
    genre: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = if (selected) colorResource(R.color.colorPrimaryContainer)
        else colorResource(R.color.colorBackground)
    ) {
        Text(
            text = genre.replaceFirstChar(Char::uppercase),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = colorResource(R.color.colorOnPrimaryContainer)
        )
    }
}
