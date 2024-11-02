package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
