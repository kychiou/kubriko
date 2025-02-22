/*
 * This file is part of Kubriko.
 * Copyright (c) Pandula Péter 2025.
 * https://github.com/pandulapeter/kubriko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */
package com.pandulapeter.kubriko.gameAnnoyedPenguins.implementation

import com.pandulapeter.kubriko.Kubriko
import com.pandulapeter.kubriko.audioPlayback.MusicManager
import com.pandulapeter.kubriko.audioPlayback.SoundManager
import com.pandulapeter.kubriko.gameAnnoyedPenguins.implementation.managers.AudioManager
import com.pandulapeter.kubriko.gameAnnoyedPenguins.implementation.managers.BackgroundAnimationManager
import com.pandulapeter.kubriko.gameAnnoyedPenguins.implementation.managers.GameplayManager
import com.pandulapeter.kubriko.gameAnnoyedPenguins.implementation.managers.LoadingManager
import com.pandulapeter.kubriko.gameAnnoyedPenguins.implementation.managers.UIManager
import com.pandulapeter.kubriko.gameAnnoyedPenguins.implementation.managers.UserPreferencesManager
import com.pandulapeter.kubriko.keyboardInput.KeyboardInputManager
import com.pandulapeter.kubriko.manager.StateManager
import com.pandulapeter.kubriko.manager.ViewportManager
import com.pandulapeter.kubriko.particles.ParticleManager
import com.pandulapeter.kubriko.persistence.PersistenceManager
import com.pandulapeter.kubriko.physics.PhysicsManager
import com.pandulapeter.kubriko.pointerInput.PointerInputManager
import com.pandulapeter.kubriko.shaders.ShaderManager
import com.pandulapeter.kubriko.shared.StateHolder
import com.pandulapeter.kubriko.sprites.SpriteManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface AnnoyedPenguinsGameStateHolder : StateHolder

internal class AnnoyedPenguinsGameStateHolderImpl : AnnoyedPenguinsGameStateHolder {

    private val sharedMusicManager = MusicManager.newInstance(
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    private val sharedSoundManager = SoundManager.newInstance(
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    private val sharedSpriteManager = SpriteManager.newInstance(
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    private val sharedPersistenceManager = PersistenceManager.newInstance(
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    private val viewportManager = ViewportManager.newInstance(
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    private val backgroundShaderManager = ShaderManager.newInstance(
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    val backgroundLoadingManager = LoadingManager()
    val stateManager = StateManager.newInstance(
        shouldAutoStart = false,
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    private val backgroundAnimationManager = BackgroundAnimationManager()
    val sharedUserPreferencesManager = UserPreferencesManager()
    val sharedAudioManager = AudioManager()
    private val particleManager = ParticleManager.newInstance(
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    private val keyboardInputManager = KeyboardInputManager.newInstance(
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    private val physicsManager = PhysicsManager.newInstance(
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    private val pointerInputManager = PointerInputManager.newInstance(
        isActiveAboveViewport = true,
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    val gameplayManager = GameplayManager()
    val uiManager = UIManager()
    val backgroundKubriko = Kubriko.newInstance(
        sharedPersistenceManager,
        sharedUserPreferencesManager,
        sharedAudioManager,
        sharedMusicManager,
        sharedSoundManager,
        sharedSpriteManager,
        backgroundShaderManager,
        backgroundAnimationManager,
        backgroundLoadingManager,
        isLoggingEnabled = true,
        instanceNameForLogging = LOG_TAG,
    )
    private val _kubriko = MutableStateFlow(
        Kubriko.newInstance(
            sharedMusicManager,
            sharedSoundManager,
            sharedSpriteManager,
            stateManager,
            viewportManager,
            physicsManager,
            keyboardInputManager,
            pointerInputManager,
            sharedPersistenceManager,
            sharedUserPreferencesManager,
            particleManager,
            sharedAudioManager,
            gameplayManager,
            uiManager,
            isLoggingEnabled = true,
            instanceNameForLogging = LOG_TAG,
        )
    )
    override val kubriko = _kubriko.asStateFlow()

    override fun stopMusic() = sharedAudioManager.stopMusicBeforeDispose()

    override fun navigateBack() = (stateManager.isRunning.value || uiManager.isInfoDialogVisible.value).also {
        if (stateManager.isRunning.value) {
            sharedAudioManager.playButtonToggleSoundEffect()
            stateManager.updateIsRunning(false)
        } else if (uiManager.isInfoDialogVisible.value) {
            sharedAudioManager.playButtonToggleSoundEffect()
            uiManager.toggleInfoDialogVisibility()
        }
    }

    override fun dispose() {
        backgroundKubriko.dispose()
        kubriko.value.dispose()
    }
}

private const val LOG_TAG = "AP"