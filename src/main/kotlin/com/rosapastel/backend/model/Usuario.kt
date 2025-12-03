package com.rosapastel.backend.model

import jakarta.persistence.*

@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var nombre: String = "",

    @Column(unique = true)
    var email: String = "",

    var direccion: String = "",

    var password: String = "",

    @Enumerated(EnumType.STRING)
    var rol: Rol = Rol.CLIENTE
)
