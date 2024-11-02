package pe.edu.utp.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentSnapshot;
import  java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import pe.edu.utp.model.Cliente;
import pe.edu.utp.model.Proveedor;
import pe.edu.utp.model.Usuario;

@Service
public class FirestoreService {

    @Autowired
    private Firestore firestore;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProveedorService proveedorService;

    @PostConstruct
    public void init() {
        listenToFirestore();
    }

    // Método para escuchar cambios en Firestore
    public void listenToFirestore() {
        firestore.collection("Usuarios").addSnapshotListener((snapshot, e) -> {
            if (e != null) {
                System.err.println("Listen failed: " + e);
                return;
            }

            for (DocumentChange docChange : snapshot.getDocumentChanges()) {
                if (docChange.getType() == DocumentChange.Type.ADDED) {
                    DocumentSnapshot doc = docChange.getDocument();
                    Usuario usuario = doc.toObject(Usuario.class);

                    // Obtener el tipo y nombre completo
                    String tipo = usuario.getTipo();
                    String nombreCompleto = usuario.getNombres() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno();
                    String id = doc.getId(); // ID del documento

                    // Lógica para agregar cliente o proveedor
                    try {
                        if ("cliente".equalsIgnoreCase(tipo)) {
                            clienteService.agregarCliente(new Cliente(id, nombreCompleto));
                            System.out.println("Cliente agregado: " + nombreCompleto);
                        } else if ("proveedor".equalsIgnoreCase(tipo)) {
                            proveedorService.agregarProveedor(new Proveedor(id, nombreCompleto));
                            System.out.println("Proveedor agregado: " + nombreCompleto);
                        }
                    } catch (Exception ex) {
                        System.err.println("Error al agregar " + tipo + ": " + ex.getMessage());
                    }
                }
            }
        });
    }
    public Usuario getUsuario(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("Usuarios").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return document.exists() ? document.toObject(Usuario.class) : null;
    }

    public void addUsuario(String id, Usuario usuario) throws ExecutionException, InterruptedException{
        firestore.collection("Usuarios").document(id).set(usuario).get();
    }

    public void updateUsuario(String id, Usuario usuario) throws ExecutionException, InterruptedException {
        firestore.collection("Usuarios").document(id).set(usuario).get();
    }

    public void deleteUsuario(String id) throws ExecutionException, InterruptedException {
        firestore.collection("Usuarios").document(id).delete().get();
    }
}
