package Com.Projecte.src.dev;

import java.io.Serializable;

public abstract class Persona implements Serializable {
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String password;
    protected String poblacio;
    protected boolean rol = false;
    protected int fechaNacimiento;
    protected int id;

    public Persona(String nombre, String apellido, String poblacio, int fechaNacimiento, int id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.poblacio = poblacio;
        this.fechaNacimiento = fechaNacimiento;
        this.id = id;
    }

    public Persona(String nombre, String apellido, String email, String password, String poblacio, boolean rol,
            int fechaNacimiento, int id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.poblacio = poblacio;
        this.rol = rol;
        this.fechaNacimiento = fechaNacimiento;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public boolean getRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public String toStringPaFicherosUsuarios() {
        return id + ";" + nombre + ";" + apellido + ";" + email + ";" + password + ";" + poblacio + ";" + rol + ";"
                + fechaNacimiento;
    }

    public String toStringPaFicherosActores_Directores() {
        return id + ";" + nombre + ";" + apellido + ";" + poblacio + ";" + fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + "; apellido=" + apellido + "; email=" + email
                + "; password=" + password + "; poblacio=" + poblacio + "; rol=" + rol
                + "; fechaNacimiento=" + fechaNacimiento + "; id=" + id + "]";
    }
}
