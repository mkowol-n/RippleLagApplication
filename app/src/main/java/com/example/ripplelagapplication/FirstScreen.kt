package com.example.ripplelagapplication

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import java.util.UUID

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }, onClick = onClick
    )
}

class FirstScreen : Screen {

    override val key: ScreenKey = UUID.randomUUID().toString()

    @Composable
    override fun Content() {
        FirstScreenContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FirstScreenContent() {
    val navigator = LocalNavigator.current
    var items by remember {
        mutableStateOf<List<List<ItemModel>>?>(null)
    }
    LaunchedEffect(Unit) {
        delay(150)
        items = listOf(
            listOf(
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
            ),
            listOf(
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
            ),
            listOf(
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
            ),
            listOf(
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
            ),
            listOf(
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
                ItemModel(url = imageUrl),
            ),
        )
    }
    Crossfade(targetState = items, label = "") {
        if (it == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items?.forEach { rowItems ->
                    LazyRow(
                        contentPadding = PaddingValues(14.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        items(rowItems) { model ->
                            Card(
                                modifier = Modifier
                                    .size(120.dp)
                                    .noRippleClickable {
                                        navigator?.push(FirstScreen())
                                    },
//                                onClick = {
//                                    navigator?.push(FirstScreen())
//                                },
                                shape = RoundedCornerShape(3.dp)
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(model.url)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}