package com.shaikh.seamless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Charcoal = Color(0xFF111113)
private val Panel = Color(0xFF1C1C20)
private val Silver = Color(0xFFE5E5E8)
private val Muted = Color(0xFF909096)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SeamlessApp()
        }
    }
}

@Composable
fun SeamlessApp() {

    val colors = darkColorScheme(
        primary = Silver,
        onPrimary = Charcoal,
        background = Charcoal,
        onBackground = Silver,
        surface = Panel,
        onSurface = Silver,
        secondary = Muted
    )

    MaterialTheme(colorScheme = colors) {
        SeamlessHome()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SeamlessHome() {

    var selectedTab by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        containerColor = Charcoal,

        bottomBar = {
            NavigationBar(
                containerColor = Panel,
                contentColor = Silver
            ) {

                val tabs = listOf(
                    "Music",
                    "Diary",
                    "Settings"
                )

                tabs.forEachIndexed { index, title ->

                    NavigationBarItem(
                        selected = selectedTab == index,

                        onClick = {
                            selectedTab = index
                        },

                        icon = {
                            Text(
                                text = when (index) {
                                    0 -> "♫"
                                    1 -> "✎"
                                    else -> "⚙"
                                },
                                fontSize = 22.sp
                            )
                        },

                        label = {
                            Text(title)
                        }
                    )
                }
            }
        }
    ) { padding ->

        AnimatedContent(
            targetState = selectedTab,

            transitionSpec = {
                (
                    fadeIn(
                        animationSpec = tween(220)
                    ) + slideInHorizontally(
                        animationSpec = tween(220),
                        initialOffsetX = { it / 12 }
                    )
                ).togetherWith(
                    fadeOut(
                        animationSpec = tween(160)
                    ) + slideOutHorizontally(
                        animationSpec = tween(160),
                        targetOffsetX = { -it / 12 }
                    )
                )
            },

            label = "SeamlessScreenTransition",

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)

        ) { targetTab ->

            when (targetTab) {

                0 -> MusicScreen()

                1 -> DiaryScreen()

                else -> SettingsScreen()
            }
        }
    }
}

@Composable
fun MusicScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "SEAMLESS",
            color = Muted,
            fontSize = 12.sp,
            letterSpacing = 5.sp
        )

        Spacer(Modifier.height(36.dp))

        Box(
            modifier = Modifier
                .size(260.dp)
                .background(
                    Panel,
                    RoundedCornerShape(28.dp)
                ),

            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "S",
                color = Silver,
                fontSize = 150.sp,
                fontWeight = FontWeight.Light
            )
        }

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Your music, uninterrupted.",
            color = Silver,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Your next listening experience starts here.",
            color = Muted,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(36.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Magic Shuffle",
                color = Silver,
                fontSize = 13.sp
            )

            Spacer(Modifier.width(24.dp))

            Text(
                text = "Motion Lyrics",
                color = Silver,
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun DiaryScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Your diary",
            color = Silver,
            fontSize = 30.sp,
            fontWeight = FontWeight.Light
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "A little space for everything.",
            color = Muted,
            fontSize = 15.sp
        )

        Spacer(Modifier.height(28.dp))

        Surface(
            color = Panel,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Start a new entry",
                    color = Silver,
                    fontSize = 18.sp
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Your memories, your way.",
                    color = Muted,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun SettingsScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Settings",
            color = Silver,
            fontSize = 30.sp,
            fontWeight = FontWeight.Light
        )

        Spacer(Modifier.height(24.dp))

        SettingsItem("Appearance")
        SettingsItem("Music player")
        SettingsItem("Magic Shuffle")
        SettingsItem("Motion Lyrics")
        SettingsItem("Diary customization")
        SettingsItem("Animations")

        Spacer(
            Modifier.weight(1f)
        )

        Text(
            text = "Developed by Shaikh Mzanak",
            color = Muted,
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
    }
}

@Composable
fun SettingsItem(title: String) {

    Surface(
        color = Panel,
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {

        Text(
            text = title,
            color = Silver,
            fontSize = 15.sp,
            modifier = Modifier.padding(18.dp)
        )
    }
}
