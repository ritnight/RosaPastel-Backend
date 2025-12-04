package com.rosapastel.backend.repository

import com.rosapastel.backend.model.Carrito
import org.springframework.data.jpa.repository.JpaRepository

interface CarritoRepository : JpaRepository<Carrito, Long> {
    fun findByUsuarioId(usuarioId: Long): Carrito?
}
