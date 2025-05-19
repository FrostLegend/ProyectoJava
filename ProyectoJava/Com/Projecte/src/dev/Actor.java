package Com.Projecte.src.dev;

import java.time.LocalDate;

public class Actor extends Persona {
    private static int contadorId = 1;

    public Actor(String nombre, String apellido, String poblacio, LocalDate fechaNacimiento) {
        super(nombre, apellido, poblacio, fechaNacimiento, contadorId++);
    }

}
