package dev.fiedri.vibe.core.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.fiedri.vibe.R
import dev.fiedri.vibe.core.ui.composables.CardData
import dev.fiedri.vibe.core.ui.composables.CardGrid
import dev.fiedri.vibe.navigation.AlbumDetail
import dev.fiedri.vibe.navigation.ArtistDetail
import dev.fiedri.vibe.navigation.LocalNavigator

@Composable
fun ArtistsScreen() {
    val navigator = LocalNavigator.current
    val artists: List<CardData> = remember {
        List(50) { index ->
            CardData(
                id = index,
                name = "Artista $index",
                songsCount = index,
                image = R.drawable.default_artist
            )
        }
    }
    CardGrid(cards = artists,
        onItemClick = { card -> navigator?.navigate(ArtistDetail(card.name)) })
}
