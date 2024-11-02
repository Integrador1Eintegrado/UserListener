package pe.edu.utp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.model.Cliente;
import pe.edu.utp.model.Proveedor;
import pe.edu.utp.service.ClienteService;
import pe.edu.utp.service.ProveedorService;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    public ResponseEntity<Proveedor> agregarProveedor(@RequestBody Proveedor proveedor) {
        Proveedor guardado = proveedorService.agregarProveedor(proveedor);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping
    public ResponseEntity<List<Proveedor>> obtenerProveedor() {
        List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
        return ResponseEntity.ok(proveedores);
    }
}
