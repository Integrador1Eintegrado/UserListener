package pe.edu.utp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clientes")
public class Cliente {

    @Id
    private String id;  // Usamos String para un ID de tipo VARCHAR

    private String nombre;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(String id, String nombreCompleto) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
