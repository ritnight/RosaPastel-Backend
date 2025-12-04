package com.rosapastel.backend.dto

data class CarritoResponse(
    val id: Long?,
    val usuarioId: Long?,
    val items: List<CarritoItemResponse>,
    val total: Int
)
