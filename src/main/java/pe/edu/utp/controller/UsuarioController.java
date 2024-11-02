package pe.edu.utp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.model.Cliente;

import pe.edu.utp.model.Proveedor;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.service.ClienteService;
import pe.edu.utp.service.FirestoreService;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.edu.utp.service.ProveedorService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private FirestoreService firestoreService;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String id) {
        try {
            Usuario usuario = firestoreService.getUsuario(id);
            return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
        } catch (ExecutionException e) {
            logger.error("Error al obtener el usuario con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).build();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
            logger.error("La operación fue interrumpida al obtener el usuario con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> addUsuario(@RequestBody Usuario usuario) {
        // Validar que el ID no sea nulo o vacío
        if (usuario.getId() == null || usuario.getId().isEmpty()) {
            logger.error("El ID del usuario no puede ser nulo o vacío");
            return ResponseEntity.status(400).build(); // 400 Bad Request
        }

        try {
            firestoreService.addUsuario(usuario.getId(), usuario);
            return ResponseEntity.status(201).build(); // 201 Created
        } catch (ExecutionException e) {
            logger.error("Error al agregar el usuario: {}", e.getMessage());
            return ResponseEntity.status(500).build(); // 500 Internal Server Error
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
            logger.error("La operación fue interrumpida al agregar el usuario: {}", e.getMessage());
            return ResponseEntity.status(500).build(); // 500 Internal Server Error
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        try {
            Usuario existingUsuario = firestoreService.getUsuario(id);
            if (existingUsuario == null) {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }

            // Update the usuario in Firestore
            firestoreService.updateUsuario(id, usuario);

            // Prepare the full name for the database update
            String nombreCompleto = usuario.getNombres() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno();
            String tipo = usuario.getTipo();

            // Update the corresponding table in MariaDB
            if ("cliente".equalsIgnoreCase(tipo)) {
                Cliente cliente = new Cliente(id, nombreCompleto);
                Optional<Cliente> updatedCliente = clienteService.actualizarCliente(id, cliente);
                if (updatedCliente.isPresent()) {
                    logger.info("Cliente actualizado: {}", nombreCompleto);
                } else {
                    logger.warn("Cliente con ID {} no encontrado para actualización.", id);
                }
            } else if ("proveedor".equalsIgnoreCase(tipo)) {
                Proveedor proveedor = new Proveedor(id, nombreCompleto);
                Optional<Proveedor> updatedProveedor = proveedorService.actualizarProveedor(id, proveedor);
                if (updatedProveedor.isPresent()) {
                    logger.info("Proveedor actualizado: {}", nombreCompleto);
                } else {
                    logger.warn("Proveedor con ID {} no encontrado para actualización.", id);
                }
            }

            return ResponseEntity.ok().build();
        } catch (ExecutionException e) {
            logger.error("Error al actualizar el usuario con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).build();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted state
            logger.error("La operación fue interrumpida al actualizar el usuario con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String id) {
        try {
            firestoreService.deleteUsuario(id);
            return ResponseEntity.ok().build();
        } catch (ExecutionException e) {
            logger.error("Error al eliminar el usuario con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).build();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
            logger.error("La operación fue interrumpida al eliminar el usuario con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
