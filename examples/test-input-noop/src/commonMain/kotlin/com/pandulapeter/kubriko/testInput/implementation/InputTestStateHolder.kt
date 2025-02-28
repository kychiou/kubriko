/*
 * This file is part of Kubriko.
 * Copyright (c) Pandula Péter 2025.
 * https://github.com/pandulapeter/kubriko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */
package com.pandulapeter.kubriko.testInput.implementation

import androidx.compose.runtime.Composable
import com.pandulapeter.kubriko.Kubriko
import com.pandulapeter.kubriko.shared.StateHolder
import kotlinx.coroutines.flow.emptyFlow

sealed interface InputTestStateHolder : StateHolder {

    companion object {
        @Composable
        fun areResourcesLoaded() = true
    }
}

internal class InputTestStateHolderImpl : InputTestStateHolder {

    override val kubriko = emptyFlow<Kubriko?>()

    override fun dispose() = Unit
}