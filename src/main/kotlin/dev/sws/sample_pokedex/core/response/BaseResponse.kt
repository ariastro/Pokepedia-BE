package dev.sws.sample_pokedex.core.response

import com.fasterxml.jackson.annotation.JsonInclude
import dev.sws.sample_pokedex.dto.PageMeta

// This annotation tells Spring to hide fields from the JSON if they are null
@JsonInclude(JsonInclude.Include.NON_NULL)
data class BaseResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null,
    val meta: PageMeta? = null,
)
