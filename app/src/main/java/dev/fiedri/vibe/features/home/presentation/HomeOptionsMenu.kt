package dev.fiedri.vibe.features.home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.fiedri.vibe.R
import dev.fiedri.vibe.core.ui.composables.borderBotton
import dev.fiedri.vibe.core.ui.theme.VibeTheme

@Composable
fun SettingsDrawer(
    open: Boolean,
    onDismissRequest: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = open,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(VibeTheme.colors.background.copy(alpha = 0.2f))
                    .clickable { onDismissRequest() }
            )
        }

        AnimatedVisibility(
            visible = open,
            modifier = Modifier.align(Alignment.CenterStart),
            enter = slideInHorizontally(animationSpec = tween(durationMillis = 200)) { -it },
            exit = slideOutHorizontally(animationSpec = tween(durationMillis = 200)) { -it }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.7f)
                    .shadow(elevation = 8.dp, shape = RectangleShape)
                    .background(VibeTheme.colors.cards)
                    .border(BorderStroke(1.dp, VibeTheme.colors.border)).statusBarsPadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .borderBotton(VibeTheme.colors.border, 1.dp)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onDismissRequest) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.menus_close),
                            tint = VibeTheme.colors.foreground,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSettingsClick()
                            onDismissRequest()
                        }
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null,
                        tint = VibeTheme.colors.mutedForeground,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = stringResource(R.string.menus_mainmenu_settings),
                        style = VibeTheme.typography.bodyLarge,
                        color = VibeTheme.colors.foreground
                    )
                }
            }
        }
    }
}
