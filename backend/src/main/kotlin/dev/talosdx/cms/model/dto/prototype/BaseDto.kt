package dev.talosdx.cms.model.dto.prototype

import kotlinx.serialization.Serializable

@Serializable
abstract class BaseDto<T> {
    abstract var id: T?
}