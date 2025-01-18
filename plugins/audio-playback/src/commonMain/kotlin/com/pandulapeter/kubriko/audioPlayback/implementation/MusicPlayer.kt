/*
 * This file is part of Kubriko.
 * Copyright (c) Pandula Péter 2025.
 * https://github.com/pandulapeter/kubriko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */
package com.pandulapeter.kubriko.audioPlayback.implementation

import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope

internal interface MusicPlayer {

    suspend fun preload(uri: String): Any?

    suspend fun play(cachedMusic: Any, shouldLoop: Boolean)

    fun isPlaying(cachedMusic: Any): Boolean

    fun pause(cachedMusic: Any)

    fun stop(cachedMusic: Any)

    fun dispose(cachedMusic: Any)

    fun dispose()
}

@Composable
internal expect fun createMusicPlayer(coroutineScope: CoroutineScope): MusicPlayer