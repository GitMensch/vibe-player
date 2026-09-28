package dev.fiedri.vibe.core.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.fiedri.vibe.core.ui.theme.VibeTheme

data class SongCardUiState(
    val id: String,
    val duration: String,
    val title: String,
    val artist: String,
    val isSelected: Boolean = false,
    val isPlayingThis: Boolean = false
)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongCard(
    song: SongCardUiState,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit= {},
    onOptionsClick: () -> Unit = {}
) {

    val isSelected: Boolean = song.isSelected
    val isPlayingThis: Boolean = song.isPlayingThis

    val backgroundColor: Color = when {
        isSelected -> VibeTheme.colors.accent
        isPlayingThis -> VibeTheme.colors.cards
        else -> Color.Transparent
    }

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


        Icon(
            imageVector = if (isPlayingThis) Icons.Default.Pause else Icons.Default.PlayArrow,
            contentDescription = if (isPlayingThis) "Pausar" else "Reproducir",
            tint = if (isPlayingThis) VibeTheme.colors.primary else VibeTheme.colors.foreground,
            modifier = Modifier.size(20.dp)
        )

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
