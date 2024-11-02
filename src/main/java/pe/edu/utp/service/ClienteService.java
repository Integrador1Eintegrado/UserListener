package pe.edu.utp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.model.Cliente;
import pe.edu.utp.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente agregarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> actualizarCliente(String id, Cliente cliente) {
        // Check if the cliente exists
        if (clienteRepository.existsById(id)) {
            cliente.setId(id); // Ensure the ID is set on the updated cliente
            return Optional.of(clienteRepository.save(cliente)); // Save and return the updated cliente
        } else {
            return Optional.empty(); // Return empty if not found
        }
    }
}
