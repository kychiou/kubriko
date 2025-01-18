/*
 * This file is part of Kubriko.
 * Copyright (c) Pandula Péter 2025.
 * https://github.com/pandulapeter/kubriko
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */
package com.pandulapeter.kubriko.serialization.typeSerializers

import com.pandulapeter.kubriko.extensions.sceneUnit
import com.pandulapeter.kubriko.types.SceneSize
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

typealias SerializableSceneSize = @Serializable(with = SceneSizeSerializer::class) SceneSize

@Suppress("EXTERNAL_SERIALIZER_USELESS")
@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = SceneSize::class)
object SceneSizeSerializer : KSerializer<SceneSize> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("sceneSize") {
        element<Float>("width")
        element<Float>("height")
    }

    override fun serialize(encoder: Encoder, value: SceneSize) {
        encoder.encodeStructure(descriptor) {
            encodeFloatElement(descriptor, 0, value.width.raw)
            encodeFloatElement(descriptor, 1, value.height.raw)
        }
    }

    override fun deserialize(decoder: Decoder): SceneSize {
        return decoder.decodeStructure(descriptor) {
            var width = 0f
            var height = 0f
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> width = decodeFloatElement(descriptor, 0)
                    1 -> height = decodeFloatElement(descriptor, 1)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> throw SerializationException("Unexpected index $index")
                }
            }
            SceneSize(width.sceneUnit, height.sceneUnit)
        }
    }
}