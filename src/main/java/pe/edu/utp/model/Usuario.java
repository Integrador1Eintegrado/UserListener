package pe.edu.utp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {
    @JsonProperty("id")
    private String id; // ID del usuario
    @JsonProperty("apellidoMaterno")
    private String apellidoMaterno;

    @JsonProperty("apellidoPaterno")
    private String apellidoPaterno;

    @JsonProperty("correo")
    private String correo;

    @JsonProperty("dni")
    private String dni;

    @JsonProperty("nombreUsuario")
    private String nombreUsuario;

    @JsonProperty("nombres")
    private String nombres;

    @JsonProperty("telefono")
    private String telefono;

    @JsonProperty("tipo")
    private String tipo;

    @JsonProperty("validatorDni")
    private boolean validatorDni;



    // Getters y Setters

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isValidatorDni() {
        return validatorDni;
    }

    public void setValidatorDni(boolean validatorDni) {
        this.validatorDni = validatorDni;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
