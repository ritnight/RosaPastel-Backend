package com.rosapastel.backend.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Producto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var nombre: String = "",
    var descripcion: String = "",
    var precio: Int = 0,
    var stock: Int = 0,
    var imagenUrl: String = "",
    var categoria: String = ""
)
