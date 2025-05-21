package Com.Projecte.src.dev;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Persona implements Serializable{
    protected String nombre;
    protected String Apellido;
    protected String email;
    protected String password;
    protected String poblacio;
    protected Boolean rol=false;
    protected LocalDate FechaNacimiento;
    protected int id;

    public Persona(String nombre, String apellido, String poblacio, LocalDate fechaNacimiento, int id) {
        this.nombre = nombre;
        Apellido = apellido;
        this.poblacio = poblacio;
        FechaNacimiento = fechaNacimiento;
        this.id = id;
    }

    public Persona(String nombre, String apellido, String email, String password, String poblacio, Boolean rol,
            LocalDate fechaNacimiento, int id) {
        this.nombre = nombre;
        Apellido = apellido;
        this.email = email;
        this.password = password;
        this.poblacio = poblacio;
        this.rol = rol;
        FechaNacimiento = fechaNacimiento;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public Boolean getRol() {
        return rol;
    }

    public void setRol(Boolean rol) {
        this.rol = rol;
    }

    public LocalDate getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

}