package com.rosapastel.backend.controller

import com.rosapastel.backend.model.Producto
import com.rosapastel.backend.service.ProductoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = ["*"])
class ProductoController(
    private val productoService: ProductoService
) {

    // GET /api/productos
    @GetMapping
    fun getProductos(): List<Producto> =
        productoService.obtenerTodos()

    // GET /api/productos/{id}
    @GetMapping("/{id}")
    fun getProducto(@PathVariable id: Long): Producto =
        productoService.obtenerPorId(id)

    // POST /api/productos
    @PostMapping
    fun crearProducto(@RequestBody producto: Producto): Producto =
        productoService.crear(producto)

    // PUT /api/productos/{id}
    @PutMapping("/{id}")
    fun actualizarProducto(
        @PathVariable id: Long,
        @RequestBody producto: Producto
    ): Producto = productoService.actualizar(id, producto)

    // DELETE /api/productos/{id}
    @DeleteMapping("/{id}")
    fun eliminarProducto(@PathVariable id: Long) {
        productoService.eliminar(id)
    }
}
