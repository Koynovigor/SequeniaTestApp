package com.l3on1kl.sequeniatestapp.presentation.film_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.l3on1kl.sequeniatestapp.R
import com.l3on1kl.sequeniatestapp.domain.model.FilmEntity
import java.util.Locale

@Composable
fun FilmDetailContent(viewModel: FilmDetailViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        FilmDetailUiState.Loading -> Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) { CircularProgressIndicator() }

        is FilmDetailUiState.Error -> ErrorStub(onRetry = { /* TODO retry*/ })

        is FilmDetailUiState.Success -> FilmDetail(state.film)
    }
}

@Composable
private fun FilmDetail(film: FilmEntity) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(colorResource(R.color.colorBackground))
            .padding(16.dp)
    ) {
        if (!film.imageUrl.isNullOrBlank()) {
            AsyncImage(
                model = film.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.5f)
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_placeholder)
            )
        }

        Text(
            text = film.localizedName.ifBlank { film.name },
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(top = 24.dp)
        )

        GenresRow(
            genres = film.genres,
            year = film.year,
            modifier = Modifier.padding(top = 8.dp)
        )

        film.rating?.let { rating ->
            RatingRow(
                rating = rating,
                modifier = Modifier
                    .padding(
                        top = 10.dp
                    )
            )
        }

        Text(
            text = film.description ?: "",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(
                top = 14.dp
            )
        )
    }
}

@Composable
private fun RatingRow(
    rating: Float,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
    ) {
        Text(
            text = "%.1f".format(Locale.US, rating),
            style = MaterialTheme.typography.displayMedium,
            color = colorResource(R.color.colorPrimary),
            modifier = Modifier.alignByBaseline()
        )
        Text(
            text = stringResource(R.string.name_metrics),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = colorResource(R.color.colorPrimary),
            modifier = Modifier
                .padding(
                    start = 8.dp
                )
                .alignByBaseline()
        )
    }
}

@Composable
private fun GenresRow(
    genres: List<String>,
    year: Int,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
    ) {
        if (genres.isNotEmpty()) {
            Text(
                text = "${genres.joinToString()}, ",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(R.color.grayText)
            )
        }
        Text(
            text = "$year " + stringResource(R.string.year),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(R.color.grayText)
        )

    }
}

@Composable
private fun ErrorStub(onRetry: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Что-то пошло не так…")
        Spacer(Modifier.height(8.dp))
        Button(onClick = onRetry) { Text("Повторить") }
    }
}
