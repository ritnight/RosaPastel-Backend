package com.rosapastel.backend.service

import com.rosapastel.backend.model.Producto
import com.rosapastel.backend.repository.ProductoRepository
import org.springframework.stereotype.Service

@Service
class ProductoService(
    private val productoRepository: ProductoRepository
) {

    fun obtenerTodos(): List<Producto> =
        productoRepository.findAll()

    fun obtenerPorId(id: Long): Producto =
        productoRepository.findById(id)
            .orElseThrow { RuntimeException("Producto no encontrado") }

    fun crear(producto: Producto): Producto =
        productoRepository.save(producto)

    fun actualizar(id: Long, productoDetalle: Producto): Producto {
        val existente = obtenerPorId(id)
        existente.nombre = productoDetalle.nombre
        existente.descripcion = productoDetalle.descripcion
        existente.precio = productoDetalle.precio
        existente.stock = productoDetalle.stock
        existente.imagenUrl = productoDetalle.imagenUrl
        existente.categoria = productoDetalle.categoria
        return productoRepository.save(existente)
    }

    fun eliminar(id: Long) {
        productoRepository.deleteById(id)
    }
}
