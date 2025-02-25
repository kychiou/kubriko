/*
 * This file is part of Kubriko.
 * Copyright (c) Pandula Péter 2025.
 * https://github.com/pandulapeter/kubriko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */
package com.pandulapeter.kubriko.demoContentShaders.implementation

import com.pandulapeter.kubriko.Kubriko
import com.pandulapeter.kubriko.demoContentShaders.implementation.managers.ContentShadersDemoManager
import com.pandulapeter.kubriko.extensions.sceneUnit
import com.pandulapeter.kubriko.manager.ViewportManager
import com.pandulapeter.kubriko.shaders.ShaderManager
import com.pandulapeter.kubriko.shared.StateHolder
import com.pandulapeter.kubriko.types.SceneSize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface ContentShadersDemoStateHolder : StateHolder

internal class ContentShadersDemoStateHolderImpl : ContentShadersDemoStateHolder {
    private val viewportManager = ViewportManager.newInstance(
        aspectRatioMode = ViewportManager.AspectRatioMode.Stretched(SceneSize(2000.sceneUnit, 2000.sceneUnit)),
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    val shaderManager = ShaderManager.newInstance(
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    private val contentShadersDemoManager = ContentShadersDemoManager()
    private val _kubriko = MutableStateFlow(
        Kubriko.newInstance(
            viewportManager,
            shaderManager,
            contentShadersDemoManager,
            isLoggingEnabled = true,
            instanceNameForLogging = LOG_TAG,
        )
    )
    override val kubriko = _kubriko.asStateFlow()

    override fun dispose() = kubriko.value.dispose()

    override fun navigateBack(
        isInFullscreenMode: Boolean,
        onFullscreenModeToggled: () -> Unit,
    ) = contentShadersDemoManager.areControlsExpanded.value.also {
        if (it) {
            contentShadersDemoManager.toggleControlsExpanded()
        }
    }
}

private const val LOG_TAG = "ContentShaders"