package com.rosapastel.backend.model

import jakarta.persistence.*

@Entity
@Table(name = "carritos")
data class Carrito(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    var usuario: Usuario? = null,

    // un carrito tiene muchos ítems
    @OneToMany(
        mappedBy = "carrito",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var items: MutableList<CarritoItem> = mutableListOf()
)
