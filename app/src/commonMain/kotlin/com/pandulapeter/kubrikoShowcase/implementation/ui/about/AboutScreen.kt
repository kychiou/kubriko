/*
 * This file is part of Kubriko.
 * Copyright (c) Pandula Péter 2025.
 * https://github.com/pandulapeter/kubriko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */
package com.pandulapeter.kubrikoShowcase.implementation.ui.about

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pandulapeter.kubriko.Kubriko
import com.pandulapeter.kubriko.shared.StateHolder
import com.pandulapeter.kubrikoShowcase.BuildConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


fun createAboutScreenStateHolder(): AboutScreenStateHolder = AboutScreenStateHolderImpl()

@Composable
internal fun AboutScreen(
    modifier: Modifier = Modifier,
    stateHolder: AboutScreenStateHolder = createAboutScreenStateHolder(),
    windowInsets: WindowInsets = WindowInsets.safeDrawing,
) = Text(
    modifier = modifier
        .fillMaxSize()
        .windowInsetsPadding(windowInsets)
        .padding(16.dp),
    text = "About - Work in progress\n\nKubriko version: ${BuildConfig.libraryVersion}",
)

sealed interface AboutScreenStateHolder : StateHolder

private class AboutScreenStateHolderImpl : AboutScreenStateHolder {
    override val kubriko: Flow<Kubriko?> = emptyFlow()

    override fun dispose() = Unit
}