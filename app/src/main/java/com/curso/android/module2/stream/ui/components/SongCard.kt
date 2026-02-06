package com.curso.android.module2.stream.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun SongFavorite(
    colorSeed: Int,
    size: Dp,
    isFavorite: Boolean,
    songId: String,
    onFavoriteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {

        SongCoverMock(
            colorSeed = colorSeed,
            size = size
        )

        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Favorite",
            tint = if (isFavorite) Color.Red else Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .size(30.dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(50))
                .clickable { onFavoriteClick(songId) }
        )
    }
}
