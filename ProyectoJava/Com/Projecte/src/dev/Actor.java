package Com.Projecte.src.dev;


public class Actor extends Persona {
    private static int contadorId = 1;

    public Actor(String nombre, String apellido, String poblacio, int fechaNacimiento) {
        super(nombre, apellido, poblacio, fechaNacimiento, contadorId++);
    }

}
