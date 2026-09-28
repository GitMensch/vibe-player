package dev.fiedri.vibe.core.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.fiedri.vibe.R
import dev.fiedri.vibe.core.ui.theme.VibeTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape


data class SongCardData(
    val id: String,
    val duration: String,
    val title: String,
    val artist: String

)
@Composable
fun SongsScreen(){
    val songs: List<SongCardData> = remember {
        List(50) { index ->
            SongCardData(
                id = "id ${index + 1}",
                duration = "3:00",
                title = "Titulo ${index + 1}",
                artist = "artista"
            )
        }
    }
    var optionsFor by remember { mutableStateOf<SongCardData?>(null) }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
items(items = songs, key = { song -> song.id }){
    song -> SongCard(song, isPlayingThis = false, isSelected = false, onClick = {}, onLongClick = {}, onOptionsClick = { optionsFor = song })
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
    song: SongCardData,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongCard(
    song: SongCardData,
    isPlayingThis: Boolean = false,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit= {},
    onOptionsClick: () -> Unit = {}
) {

    /*val backgroundColor: Color = when {
        isSelected -> VibeTheme.colors.accent // o el color que uses para selección
        isPlayingThis -> VibeTheme.colors.card//.copy(alpha = 0.5f)
        else -> Color.Transparent
    }*/
    val backgroundColor: Color = when {
        isSelected -> VibeTheme.colors.accent
        isPlayingThis -> VibeTheme.colors.cards
        else -> Color.Transparent // O androidx.compose.ui.graphics.Color.Transparent
    } as Color

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(color = backgroundColor)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        IconButton(
            onClick = onClick,
            modifier = Modifier.size(30.dp)
        ) {
            Icon(
                imageVector = if (isPlayingThis) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlayingThis) "Pausar" else "Reproducir",
                tint = if (isPlayingThis) VibeTheme.colors.primary else VibeTheme.colors.foreground,
                modifier = Modifier.size(20.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = song.title,
                color = VibeTheme.colors.foreground,
                style = VibeTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = song.artist,
                color = VibeTheme.colors.mutedForeground,
                style = VibeTheme.typography.caption,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = song.duration, // Aquí puedes aplicar tu función formatearMS(song.duration) si es un Long/Int
                color = VibeTheme.colors.mutedForeground,
                style = VibeTheme.typography.caption
            )

            IconButton(
                onClick = onOptionsClick,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Opciones",
                    tint = VibeTheme.colors.mutedForeground,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
