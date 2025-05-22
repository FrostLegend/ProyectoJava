package Com.Projecte.src.dev;

import java.io.Serializable;

public abstract class Persona implements Serializable {
    protected String nombre;
    protected String Apellido;
    protected String email;
    protected String password;
    protected String poblacio;
    protected Boolean rol = false;
    protected int FechaNacimiento;
    protected int id;

    public Persona(String nombre, String apellido, String poblacio, int fechaNacimiento, int id) {
        this.nombre = nombre;
        Apellido = apellido;
        this.poblacio = poblacio;
        FechaNacimiento = fechaNacimiento;
        this.id = id;
    }

    public Persona(String nombre, String apellido, String email, String password, String poblacio, Boolean rol,
            int fechaNacimiento, int id) {
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

    public int getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public String toStringPaFicheros() {
        return "" + getNombre() + "," + getApellido() + "," + getEmail()
                + "," + getPassword() + "," + getPoblacio() + "," + getRol()
                + "," + getFechaNacimiento() + "," + getId() + "";
    }

    public String toStringPaFicheros2() {
        return "" + getNombre() + "," + getApellido() + "," + getPoblacio() + ","
                + getFechaNacimiento() + "," + getId() + "";
    }

    @Override
    public String toString() {
        return "Persona [getNombre()=" + getNombre() + ", getApellido()=" + getApellido() + ", getEmail()=" + getEmail()
                + ", getPassword()=" + getPassword() + ", getPoblacio()=" + getPoblacio() + ", getRol()=" + getRol()
                + ", getFechaNacimiento()=" + getFechaNacimiento() + ", getId()=" + getId() + "]";
    }
    
}