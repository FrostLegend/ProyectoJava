package Com.Projecte.src.dev;


public class Director extends Persona{
    private static int contadorId = 1;

    public Director(String nombre, String apellido, String poblacio, int fechaNacimiento) {
        super(nombre, apellido, poblacio, fechaNacimiento, contadorId++);
    }

}
