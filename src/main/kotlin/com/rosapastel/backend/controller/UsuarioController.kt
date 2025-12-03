package com.rosapastel.backend.controller

import com.rosapastel.backend.dto.RegistroUsuarioRequest
import com.rosapastel.backend.dto.UsuarioResponse
import com.rosapastel.backend.service.UsuarioService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = ["*"])
class UsuarioController(
    private val usuarioService: UsuarioService
) {

    // Endpoint para registro desde la app (Crear Cuenta)
    // POST /api/usuarios/registro
    @PostMapping("/registro")
    fun registrarCliente(
        @RequestBody request: RegistroUsuarioRequest
    ): UsuarioResponse =
        usuarioService.registrarCliente(request)

    //listar todos los usuarios (para pruebas/admin)
    @GetMapping
    fun listarUsuarios(): List<UsuarioResponse> =
        usuarioService.obtenerTodos()

    //obtener uno por id
    @GetMapping("/{id}")
    fun obtenerUsuario(@PathVariable id: Long): UsuarioResponse =
        usuarioService.obtenerPorId(id)

    //eliminar usuario
    @DeleteMapping("/{id}")
    fun eliminarUsuario(@PathVariable id: Long) {
        usuarioService.eliminar(id)
    }
}
