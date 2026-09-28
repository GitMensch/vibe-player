package dev.fiedri.vibe.core.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.fiedri.vibe.R
import dev.fiedri.vibe.core.ui.composables.CardData
import dev.fiedri.vibe.core.ui.composables.CardGrid
import dev.fiedri.vibe.navigation.AlbumDetail
import dev.fiedri.vibe.navigation.LocalNavigator

@Composable
fun AlbumsScreen() {
    val navigator = LocalNavigator.current
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
    CardGrid(
        cards = albums,
        onItemClick = { card -> navigator?.navigate(AlbumDetail(card.id)) }
    )
}
