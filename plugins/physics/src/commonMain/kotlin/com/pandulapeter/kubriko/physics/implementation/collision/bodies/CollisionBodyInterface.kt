/*
 * This file is part of Kubriko.
 * Copyright (c) Pandula Péter 2025.
 * https://github.com/pandulapeter/kubriko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */
package com.pandulapeter.kubriko.physics.implementation.collision.bodies

import com.pandulapeter.kubriko.actor.body.AxisAlignedBoundingBox
import com.pandulapeter.kubriko.physics.implementation.geometry.Shape
import com.pandulapeter.kubriko.physics.implementation.geometry.bodies.TranslatableBody
import com.pandulapeter.kubriko.types.AngleRadians

interface CollisionBodyInterface : TranslatableBody {
    var shape: Shape
    var dynamicFriction: Float
    var staticFriction: Float
    var orientation: AngleRadians
    var aabb: AxisAlignedBoundingBox
}