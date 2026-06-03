package dev.sws.pokepedia.core.response

import com.fasterxml.jackson.annotation.JsonInclude

// This annotation tells Spring to hide fields from the JSON if they are null
@JsonInclude(JsonInclude.Include.NON_NULL)
data class BaseResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null,
    val meta: PageMeta? = null,
)
