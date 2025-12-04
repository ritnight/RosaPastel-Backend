package com.rosapastel.backend.model

import jakarta.persistence.*

@Entity
@Table(name = "carrito_items")
data class CarritoItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrito_id")
    var carrito: Carrito? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id")
    var producto: Producto? = null,

    var cantidad: Int = 1,

    // guarda el precio al momento de agregarlo al carrito
    var precioUnitario: Int = 0
)
