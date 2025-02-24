/*
 * This file is part of Kubriko.
 * Copyright (c) Pandula Péter 2025.
 * https://github.com/pandulapeter/kubriko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */
package com.pandulapeter.kubriko.gameAnnoyedPenguins.implementation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kubriko.examples.game_annoyed_penguins.generated.resources.Res
import kubriko.examples.game_annoyed_penguins.generated.resources.close_confirmation
import kubriko.examples.game_annoyed_penguins.generated.resources.close_confirmation_negative
import kubriko.examples.game_annoyed_penguins.generated.resources.close_confirmation_positive
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CloseConfirmationDialog(
    modifier: Modifier = Modifier,
    onCloseConfirmed: () -> Unit = {},
    onCloseCancelled: () -> Unit = {},
    onPointerEnter: () -> Unit = {},
) = Box(
    modifier = modifier.fillMaxSize(),
) {
    Column(
        modifier = Modifier
            .align(Alignment.Center)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            color = MaterialTheme.colorScheme.primary,
            text = stringResource(Res.string.close_confirmation),
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AnnoyedPenguinsButton(
                title = stringResource(Res.string.close_confirmation_positive),
                onButtonPressed = onCloseConfirmed,
                onPointerEnter = onPointerEnter,
            )
            AnnoyedPenguinsButton(
                title = stringResource(Res.string.close_confirmation_negative),
                onButtonPressed = onCloseCancelled,
                onPointerEnter = onPointerEnter,
            )
        }
    }
}