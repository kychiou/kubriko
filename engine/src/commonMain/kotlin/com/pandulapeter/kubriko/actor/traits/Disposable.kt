/*
 * This file is part of Kubriko.
 * Copyright (c) Pandula Péter 2025.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */
package com.pandulapeter.kubriko.actor.traits

import com.pandulapeter.kubriko.actor.Actor
import com.pandulapeter.kubriko.manager.ActorManager

/**
 * Should be implemented by [Actor]s that need to perform additional cleanup tasks when they are no longer part of the Scene.
 * The [ActorManager] wil automatically call the [dispose] function when the [Actor] is removed from the scene.
 */
interface Disposable : Actor {

    /**
     * Can be used to dispose / release resources referenced by the instance.
     * It will be called right before the [onRemoved] function.
     */
    fun dispose()
}