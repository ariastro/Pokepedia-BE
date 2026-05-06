package dev.sws.sample_pokedex.core.response

data class PageMeta(
    val currentPage: Int,
    val totalPages: Int,
    val totalElements: Long,
    val hasNext: Boolean
)