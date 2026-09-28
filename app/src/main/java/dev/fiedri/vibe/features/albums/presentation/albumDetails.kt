package dev.fiedri.vibe.features.albums.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.fiedri.vibe.core.ui.composables.DetailHeader
import dev.fiedri.vibe.core.ui.composables.DetailResources
import dev.fiedri.vibe.core.ui.composables.DetailsScreen
import dev.fiedri.vibe.core.ui.composables.EntityType
import dev.fiedri.vibe.core.ui.screen.SongCardData
import dev.fiedri.vibe.core.ui.LocalNavigator

@Composable
fun AlbumDetailsScreen(id: Int) {
    val navigator = LocalNavigator.current
    val songs: List<SongCardData> = remember(id) {
        List(50) { index ->
            SongCardData(
                id = "id ${index + 1}",
                duration = "3:00",
                title = "Titulo ${index + 1}",
                artist = "artista"
            )
        }
    }
    DetailsScreen(
        header = DetailHeader(name = "Titulo del album"),
        type = EntityType.ALBUM,
        resources = DetailResources(songs),
        onBack = { navigator?.goBack() },
        onPlay = {},
        onShuffle = {}
    )
}
