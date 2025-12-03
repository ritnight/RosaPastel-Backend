package com.rosapastel.backend.dto

data class RegistroUsuarioRequest(
    val nombre: String,
    val email: String,
    val direccion: String,
    val password: String,
    val repetirPassword: String
)
