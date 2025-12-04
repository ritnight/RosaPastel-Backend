package com.rosapastel.backend.dto

data class AgregarItemRequest(
    val productoId: Long,
    val cantidad: Int = 1
)
