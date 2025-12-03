package com.rosapastel.backend.service

import com.rosapastel.backend.dto.RegistroUsuarioRequest
import com.rosapastel.backend.dto.UsuarioResponse
import com.rosapastel.backend.model.Rol
import com.rosapastel.backend.model.Usuario
import com.rosapastel.backend.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository
) {

    fun registrarCliente(request: RegistroUsuarioRequest): UsuarioResponse {
        // 1. Validar correos únicos
        if (usuarioRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("El correo ya está registrado")
        }

        // 2. Validar contraseñas iguales
        if (request.password != request.repetirPassword) {
            throw IllegalArgumentException("Las contraseñas no coinciden")
        }

        // 3. Crear entidad Usuario
        val usuario = Usuario(
            nombre = request.nombre,
            email = request.email,
            direccion = request.direccion,
            password = request.password, // TODO: encriptar más adelante
            rol = Rol.CLIENTE           // registro desde app → siempre cliente
        )

        val guardado = usuarioRepository.save(usuario)
        return guardado.toResponse()
    }

    fun obtenerTodos(): List<UsuarioResponse> =
        usuarioRepository.findAll().map { it.toResponse() }

    fun obtenerPorId(id: Long): UsuarioResponse =
        usuarioRepository.findById(id)
            .orElseThrow { RuntimeException("Usuario no encontrado") }
            .toResponse()

    fun eliminar(id: Long) {
        usuarioRepository.deleteById(id)
    }

    // --- MAPPER privado de entidad → DTO de respuesta
    private fun Usuario.toResponse() = UsuarioResponse(
        id = this.id,
        nombre = this.nombre,
        email = this.email,
        direccion = this.direccion,
        rol = this.rol
    )
}
