package pe.edu.utp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "Proveedores")
public class Proveedor {

    @jakarta.persistence.Id
    @Id
    private String id;  // Usamos String para un ID de tipo VARCHAR

    private String nombre;


    // Constructor vacío
    public Proveedor() {
    }

    public Proveedor(String id, String nombreCompleto) {
        this.id = id;
        this.nombre = nombreCompleto;
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
