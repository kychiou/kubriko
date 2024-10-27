package com.pandulapeter.gameTemplate.engine.implementation.serializers

import androidx.compose.ui.geometry.Size
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

typealias SerializableSize = @Serializable(with = SizeSerializer::class) Size

@Serializer(forClass = Size::class)
object SizeSerializer : KSerializer<Size> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Size") {
        element<Float>("width")
        element<Float>("height")
    }

    override fun serialize(encoder: Encoder, value: Size) {
        encoder.encodeStructure(descriptor) {
            encodeFloatElement(descriptor, 0, value.width)
            encodeFloatElement(descriptor, 1, value.height)
        }
    }

    override fun deserialize(decoder: Decoder): Size {
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
            Size(width, height)
        }
    }
}