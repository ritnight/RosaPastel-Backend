package com.rosapastel.backend.repository

import com.rosapastel.backend.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Usuario?
}
