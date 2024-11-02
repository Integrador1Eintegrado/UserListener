package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.model.Proveedor;


public interface ProveedorRepository extends JpaRepository<Proveedor, String> {
}
