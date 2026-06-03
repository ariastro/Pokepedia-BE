package dev.sws.pokepedia.core.response

data class PageMeta(
    val currentPage: Int,
    val totalPages: Int,
    val totalElements: Long,
    val hasNext: Boolean
)