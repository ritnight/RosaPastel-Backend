package com.rosapastel.backend.service

import com.rosapastel.backend.dto.*
import com.rosapastel.backend.model.Carrito
import com.rosapastel.backend.model.CarritoItem
import com.rosapastel.backend.repository.CarritoRepository
import com.rosapastel.backend.repository.ProductoRepository
import com.rosapastel.backend.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class CarritoService(
    private val carritoRepository: CarritoRepository,
    private val usuarioRepository: UsuarioRepository,
    private val productoRepository: ProductoRepository
) {

    // obtiene carrito de un usuario (si no existe, lo crea vacío)
    fun obtenerCarritoDeUsuario(usuarioId: Long): CarritoResponse {
        val carrito = carritoRepository.findByUsuarioId(usuarioId)
            ?: crearCarritoParaUsuario(usuarioId)
        return carrito.toResponse()
    }

    // agrega producto al carrito (si ya existe, aumenta cantidad)
    fun agregarProducto(usuarioId: Long, productoId: Long, cantidad: Int): CarritoResponse {
        val carrito = carritoRepository.findByUsuarioId(usuarioId)
            ?: crearCarritoParaUsuario(usuarioId)

        val producto = productoRepository.findById(productoId)
            .orElseThrow { RuntimeException("Producto no encontrado") }

        val itemExistente = carrito.items.firstOrNull { it.producto?.id == productoId }

        if (itemExistente != null) {
            itemExistente.cantidad += cantidad
        } else {
            val nuevoItem = CarritoItem(
                carrito = carrito,
                producto = producto,
                cantidad = cantidad,
                precioUnitario = producto.precio
            )
            carrito.items.add(nuevoItem)
        }

        val guardado = carritoRepository.save(carrito)
        return guardado.toResponse()
    }

    // cambia cantidad de un item (si cantidad <= 0, se elimina)
    fun actualizarCantidad(usuarioId: Long, itemId: Long, cantidad: Int): CarritoResponse {
        val carrito = carritoRepository.findByUsuarioId(usuarioId)
            ?: throw RuntimeException("Carrito no encontrado para usuario $usuarioId")

        val item = carrito.items.firstOrNull { it.id == itemId }
            ?: throw RuntimeException("Item no encontrado en el carrito")

        if (cantidad <= 0) {
            carrito.items.remove(item)
        } else {
            item.cantidad = cantidad
        }

        val guardado = carritoRepository.save(carrito)
        return guardado.toResponse()
    }

    // elimina un item específico
    fun eliminarItem(usuarioId: Long, itemId: Long): CarritoResponse {
        val carrito = carritoRepository.findByUsuarioId(usuarioId)
            ?: throw RuntimeException("Carrito no encontrado para usuario $usuarioId")

        val item = carrito.items.firstOrNull { it.id == itemId }
            ?: throw RuntimeException("Item no encontrado en el carrito")

        carrito.items.remove(item)

        val guardado = carritoRepository.save(carrito)
        return guardado.toResponse()
    }



    private fun crearCarritoParaUsuario(usuarioId: Long): Carrito {
        val usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow { RuntimeException("Usuario no encontrado") }

        val carrito = Carrito(usuario = usuario)
        return carritoRepository.save(carrito)
    }

    private fun Carrito.toResponse(): CarritoResponse {
        val itemResponses = this.items.map { it.toResponse() }
        val total = itemResponses.sumOf { it.subtotal }

        return CarritoResponse(
            id = this.id,
            usuarioId = this.usuario?.id,
            items = itemResponses,
            total = total
        )
    }

    private fun CarritoItem.toResponse(): CarritoItemResponse {
        val productoId = this.producto?.id
        val nombreProducto = this.producto?.nombre ?: "Producto desconocido"
        val subtotal = this.cantidad * this.precioUnitario

        return CarritoItemResponse(
            id = this.id,
            productoId = productoId,
            nombreProducto = nombreProducto,
            cantidad = this.cantidad,
            precioUnitario = this.precioUnitario,
            subtotal = subtotal
        )
    }
}
