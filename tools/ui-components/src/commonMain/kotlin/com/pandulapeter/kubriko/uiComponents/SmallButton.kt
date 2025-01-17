/*
 * This file is part of Kubriko.
 * Copyright (c) Pandula Péter 2025.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */
package com.pandulapeter.kubriko.uiComponents

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    onButtonPressed: () -> Unit,
    contentDescription: StringResource?,
    contentColor: Color? = null,
) {
    FloatingActionButton(
        modifier = modifier.size(40.dp),
        containerColor = if (isSystemInDarkTheme()) FloatingActionButtonDefaults.containerColor else MaterialTheme.colorScheme.primary,
        contentColor = contentColor ?: MaterialTheme.colorScheme.onPrimary,
        onClick = onButtonPressed,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = contentDescription?.let { stringResource(it) },
        )
    }
}