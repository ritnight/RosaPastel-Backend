package com.rosapastel.backend.dto

import com.rosapastel.backend.model.Rol

data class UsuarioResponse(
    val id: Long?,
    val nombre: String,
    val email: String,
    val direccion: String,
    val rol: Rol
)
