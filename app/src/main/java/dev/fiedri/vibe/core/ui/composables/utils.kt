package dev.fiedri.vibe.core.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.fiedri.vibe.core.ui.screen.AlbumsScreen
import dev.fiedri.vibe.core.ui.screen.ArtistsScreen
import dev.fiedri.vibe.core.ui.screen.PlaylistsScreen
import dev.fiedri.vibe.core.ui.screen.SongsScreen
import dev.fiedri.vibe.navigation.LocalNavigator
import dev.fiedri.vibe.navigation.PlaylistDetail

@Composable
fun Pager(
    pagerState: PagerState,
    tabs: List<String>,
    innerPadding: PaddingValues
) {
    val navigator = LocalNavigator.current
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) { pageIndex ->

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when(pageIndex){
                0 -> SongsScreen()
                1 -> ArtistsScreen()
                2 -> AlbumsScreen()
                3-> PlaylistsScreen(
                    onPlaylistClick = { playlist -> navigator?.navigate(PlaylistDetail(playlist.id)) }
                )
            }
        }
    }
}

fun Modifier.borderBotton(
    color: Color,
    width: Dp = 4.dp
): Modifier = this.drawBehind {
    val strokeWidth = width.toPx()
    drawLine(
        color = color,
        start = Offset(0f, size.height),
        end = Offset(size.width, size.height),
        strokeWidth = strokeWidth
    )
}