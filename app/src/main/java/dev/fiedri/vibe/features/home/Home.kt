package dev.fiedri.vibe.features.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.fiedri.vibe.core.ui.composables.Pager
import dev.fiedri.vibe.core.ui.composables.SettingsDrawer
import dev.fiedri.vibe.core.ui.composables.VibeToBar
import dev.fiedri.vibe.core.ui.theme.VibeTheme
import dev.fiedri.vibe.features.player.presentation.Player
import dev.fiedri.vibe.features.player.presentation.PlayerState
import dev.fiedri.vibe.features.player.presentation.Song
import dev.fiedri.vibe.navigation.LocalNavigator
import dev.fiedri.vibe.navigation.Search
import dev.fiedri.vibe.navigation.Settings
import kotlinx.coroutines.launch

@Composable
fun HomeLayout(){
    val tabs = listOf("Songs", "Artists", "Albums", "Playlist")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()
    val navigator = LocalNavigator.current

    var drawerOpen by remember { mutableStateOf(false) }

    BackHandler(enabled = drawerOpen) { drawerOpen = false }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                VibeToBar(
                    tabs = tabs,
                    activeTab = tabs[pagerState.currentPage],
                    onTabSelected = { selectedTab ->
                        val targetIndex = tabs.indexOf(selectedTab)
                        if (targetIndex != -1) {

                            coroutineScope.launch {
                                pagerState.animateScrollToPage(targetIndex)
                            }
                        }
                    },
                    onMenuClick = { drawerOpen = true },
                    onSearchClick = { navigator?.navigate(Search) }
                )
            },
            containerColor = VibeTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Pager(
                pagerState = pagerState,
                tabs = tabs,
                innerPadding = innerPadding
            )
        }

        SettingsDrawer(
            open = drawerOpen,
            onDismissRequest = { drawerOpen = false },
            onSettingsClick = { navigator?.navigate(Settings) }
        )
    }


}
