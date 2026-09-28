package dev.fiedri.vibe.features.artists.presentation

import dev.fiedri.vibe.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.fiedri.vibe.core.ui.composables.CardData
import dev.fiedri.vibe.core.ui.composables.DetailHeader
import dev.fiedri.vibe.core.ui.composables.DetailResources
import dev.fiedri.vibe.core.ui.composables.DetailsScreen
import dev.fiedri.vibe.core.ui.composables.EntityType
import dev.fiedri.vibe.core.ui.screen.SongCardData
import dev.fiedri.vibe.core.ui.LocalNavigator

@Composable
fun ArtistDetailsScreen(name: String) {
    val navigator = LocalNavigator.current
    val songs: List<SongCardData> = remember(name) {
        List(50) { index ->
            SongCardData(
                id = "id ${index + 1}",
                duration = "3:00",
                title = "Cancion ${index + 1}",
                artist = "artista"
            )
        }
    }
    val albums: List<CardData> = remember {
        List(50) { index ->
            CardData(
                id = index,
                name = "Album $index",
                songsCount = index,
                image = R.drawable.default_cover
            )
        }
    }
    DetailsScreen(
        header = DetailHeader(name = "Nombre Artista", image = R.drawable.default_artist),
        type = EntityType.ARTISTS,
        resources = DetailResources(songs, albums),
        onBack = { navigator?.goBack() },
        onPlay = {},
        onShuffle = {}
    )
}