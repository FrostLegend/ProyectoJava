package Com.Projecte.src.dev;

import java.time.LocalDate;

public class Director extends Persona{
    private static int contadorId = 1;

    public Director(String nombre, String apellido, String poblacio, LocalDate fechaNacimiento) {
        super(nombre, apellido, poblacio, fechaNacimiento, contadorId++);
    }

}
