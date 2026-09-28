package dev.fiedri.vibe.features

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.fiedri.vibe.core.ui.screen.DetailHeader
import dev.fiedri.vibe.core.ui.screen.DetailResources
import dev.fiedri.vibe.core.ui.screen.DetailsScreen
import dev.fiedri.vibe.core.ui.screen.EntityType
import dev.fiedri.vibe.core.ui.screen.SongCardData
import dev.fiedri.vibe.navigation.LocalNavigator

@Composable
fun PlaylistDetailsScreen(id: Int) {
    val navigator = LocalNavigator.current
    val songs: List<SongCardData> = remember(id) {
        List(50) { index ->
            SongCardData(
                id = "id ${index + 1}",
                duration = "3:00",
                title = "Cancion ${index + 1}",
                artist = "artista"
            )
        }
    }
    DetailsScreen(
        header = DetailHeader(name = "Mi playlist"),
        type = EntityType.PLAYLIST,
        resources = DetailResources(songs),
        onBack = { navigator?.goBack() },
        onPlay = {},
        onShuffle = {}
    )
}
