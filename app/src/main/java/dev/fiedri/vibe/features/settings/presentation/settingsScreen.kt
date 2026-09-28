package dev.fiedri.vibe.features.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fiedri.vibe.core.ui.composables.borderBotton
import dev.fiedri.vibe.core.ui.theme.VibeTheme
import dev.fiedri.vibe.navigation.LocalNavigator

@Preview(showBackground = true)
@Composable
fun SettingsScreen(){
    val navigator = LocalNavigator.current
    Column(
        modifier = Modifier.fillMaxSize().background(VibeTheme.colors.background).statusBarsPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().borderBotton(VibeTheme.colors.border).padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            IconButton(
                onClick = { navigator?.goBack() }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver atras",
                    tint = VibeTheme.colors.foreground)
            }
            Text(
                "Settings".uppercase(),
                style = VibeTheme.typography.titleLarge,
                color = VibeTheme.colors.foreground,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Box(){}
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp)
        ) {
            Text(
                "Configuracion General".uppercase(),
                style = VibeTheme.typography.caption,
                color = VibeTheme.colors.foreground,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Column(
                modifier = Modifier.fillMaxWidth().background(VibeTheme.colors.popover).border(2.dp,
                    VibeTheme.colors.border).padding(12.dp)
            ) {
Box(
    modifier = Modifier.border(2.dp,
        VibeTheme.colors.border).fillMaxWidth().clickable{}.padding(12.dp)
){
    Text(
        "Configuracion General".uppercase(),
        style = VibeTheme.typography.caption,
        color = VibeTheme.colors.foreground,
        fontSize = 14.sp
    )
}
                Text(
                    "Configuracion General".uppercase(),
                    style = VibeTheme.typography.caption,
                    color = VibeTheme.colors.mutedForeground,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }
        }
    }
}