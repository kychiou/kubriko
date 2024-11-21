package com.pandulapeter.kubriko.sceneEditor.implementation.userInterface.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun EditorIcon(
    modifier: Modifier = Modifier,
    drawableResource: DrawableResource,
    contentDescription: String,
    onClick: (() -> Unit)? = null,
    isEnabled: Boolean = true,
) = Icon(
    modifier = modifier
        .size(24.dp)
        .clip(CircleShape)
        .alpha(if (isEnabled) 1f else 0.2f)
        .run { onClick?.let { clickable(enabled = isEnabled, onClick = onClick) } ?: this }
        .padding(4.dp),
    painter = painterResource(drawableResource),
    contentDescription = contentDescription,
)