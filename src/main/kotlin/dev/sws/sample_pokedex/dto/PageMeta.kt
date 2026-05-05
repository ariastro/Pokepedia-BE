package dev.sws.sample_pokedex.dto

data class PageMeta(
    val currentPage: Int,
    val totalPages: Int,
    val totalElements: Long,
    val hasNext: Boolean
)
