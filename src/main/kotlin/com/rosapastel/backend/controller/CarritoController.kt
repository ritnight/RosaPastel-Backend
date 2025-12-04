package com.rosapastel.backend.controller

import com.rosapastel.backend.dto.*
import com.rosapastel.backend.service.CarritoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = ["*"])
class CarritoController(
    private val carritoService: CarritoService
) {

    // obtiene el carrito de un usuario
    // GET /api/carritos/usuario/{usuarioId}
    @GetMapping("/usuario/{usuarioId}")
    fun obtenerCarrito(@PathVariable usuarioId: Long): CarritoResponse =
        carritoService.obtenerCarritoDeUsuario(usuarioId)

    // agregar producto al carrito
    // POST /api/carritos/usuario/{usuarioId}/items
    @PostMapping("/usuario/{usuarioId}/items")
    fun agregarItem(
        @PathVariable usuarioId: Long,
        @RequestBody request: AgregarItemRequest
    ): CarritoResponse =
        carritoService.agregarProducto(usuarioId, request.productoId, request.cantidad)

    // actualiza cantidad de un item
    // PUT /api/carritos/usuario/{usuarioId}/items/{itemId}
    @PutMapping("/usuario/{usuarioId}/items/{itemId}")
    fun actualizarCantidad(
        @PathVariable usuarioId: Long,
        @PathVariable itemId: Long,
        @RequestBody request: ActualizarCantidadRequest
    ): CarritoResponse =
        carritoService.actualizarCantidad(usuarioId, itemId, request.cantidad)

    // elimina un item del carrito
    // DELETE /api/carritos/usuario/{usuarioId}/items/{itemId}
    @DeleteMapping("/usuario/{usuarioId}/items/{itemId}")
    fun eliminarItem(
        @PathVariable usuarioId: Long,
        @PathVariable itemId: Long
    ): CarritoResponse =
        carritoService.eliminarItem(usuarioId, itemId)
}
