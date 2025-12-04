package com.rosapastel.backend.dto

data class CarritoItemResponse(
    val id: Long?,
    val productoId: Long?,
    val nombreProducto: String,
    val cantidad: Int,
    val precioUnitario: Int,
    val subtotal: Int
)
