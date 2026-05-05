package dev.sws.sample_pokedex.core.response

data class PaginatedResponse<T>(
    val content: List<T>,
    val currentPage: Int,
    val totalPages: Int,
    val totalElements: Long
)