package com.rosapastel.backend.repository

import com.rosapastel.backend.model.Producto
import org.springframework.data.jpa.repository.JpaRepository

interface ProductoRepository : JpaRepository<Producto, Long>
