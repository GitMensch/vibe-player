package dev.fiedri.vibe.features.songs.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.fiedri.vibe.R
import dev.fiedri.vibe.core.ui.theme.VibeTheme
import androidx.compose.ui.graphics.RectangleShape
import dev.fiedri.vibe.core.ui.composables.SongCard
import dev.fiedri.vibe.core.ui.composables.SongCardUiState


@Composable
fun SongsScreen(){
    val songs: List<SongCardUiState> = remember {
        List(50) { index ->
            SongCardUiState(
                id = "id ${index + 1}",
                duration = "3:00",
                title = "Titulo ${index + 1}",
                artist = "artista",

            )
        }
    }
    var optionsFor by remember { mutableStateOf<SongCardUiState?>(null) }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
items(items = songs, key = { song -> song.id }){
    song -> SongCard(song, onClick = {}, onLongClick = {}, onOptionsClick = { optionsFor = song })
}
    }
    optionsFor?.let { song ->
        SongOptionsSheet(
            song = song,
            onDismissRequest = { optionsFor = null }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongOptionsSheet(
    song: SongCardUiState,
    onDismissRequest: () -> Unit,
    onInfo: () -> Unit = {},
    onPlayNext: () -> Unit = {},
    onAddToPlaylists: () -> Unit = {},
    onShare: () -> Unit = {},
    onDelete: () -> Unit = {}
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = rememberModalBottomSheetState(),
        shape = RectangleShape,
        containerColor = VibeTheme.colors.popover,
        contentColor = VibeTheme.colors.foreground,
        scrimColor = VibeTheme.colors.background.copy(alpha = 0.2f),
        dragHandle = null
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SongOptionRow(
                text = stringResource(R.string.songs_options_info),
                onClick = {
                    onDismissRequest()
                    onInfo()
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            )
            HorizontalDivider(color = VibeTheme.colors.border)
            SongOptionRow(
                text = stringResource(R.string.songs_options_next_in_queue),
                onClick = {
                    onDismissRequest()
                    onPlayNext()
                }
            )
            HorizontalDivider(color = VibeTheme.colors.border)
            SongOptionRow(
                text = stringResource(R.string.songs_options_add_to_playlists),
                onClick = {
                    onDismissRequest()
                    onAddToPlaylists()
                }
            )
            HorizontalDivider(color = VibeTheme.colors.border)
            SongOptionRow(
                text = stringResource(R.string.songs_options_share),
                onClick = {
                    onDismissRequest()
                    onShare()
                }
            )
            HorizontalDivider(color = VibeTheme.colors.border)
            SongOptionRow(
                text = stringResource(R.string.songs_options_delete),
                onClick = {
                    onDismissRequest()
                    onDelete()
                },
                destructive = true
            )
        }
    }
}

@Composable
private fun SongOptionRow(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    destructive: Boolean = false
) {
    val contentColor = if (destructive) {
        VibeTheme.colors.destructive
    } else {
        VibeTheme.colors.foreground
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (leadingIcon != null) {
            CompositionLocalProvider(LocalContentColor provides contentColor) {
                leadingIcon()
            }
        }
        Text(
            text = text,
            color = contentColor,
            style = VibeTheme.typography.bodyLarge
        )
    }
}