package Com.Projecte.src.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Actor extends Persona {
    static Scanner sc = new Scanner(System.in);

    public Actor(String nombre, String apellido, String poblacio, int fechaNacimiento, int id) {
        super(nombre, apellido, poblacio, fechaNacimiento, id);
    }

    public static ArrayList<Actor> CrearActor(ArrayList<Actor> actores) {
        File fichero = new File("../dades/Actores.txt");
        int ultimaId = -1;

        if (fichero.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (!linea.isEmpty()) {
                        String[] partes = linea.split(";");
                        if (partes.length > 0) {
                            try {
                                int idLinea = Integer.parseInt(partes[0]);
                                if (idLinea > ultimaId) {
                                    ultimaId = idLinea;
                                }
                            } catch (NumberFormatException ignored) {
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error leyendo el archivo para obtener la última ID: " + e.getMessage());
            }
        }

        System.out.println("Introduce los datos de los actores (escribe 'fin' en el nombre para terminar):");
        while (true) {
            System.out.print("Nombre del actor: ");
            String nombreactor = sc.nextLine();
            if (nombreactor.equalsIgnoreCase("fin"))
                break;

            System.out.print("Apellido del actor: ");
            String apellidoactor = sc.nextLine();
            if (apellidoactor.equalsIgnoreCase("fin"))
                break;

            System.out.print("Población del actor: ");
            String poblacionactor = sc.nextLine();
            if (poblacionactor.equalsIgnoreCase("fin"))
                break;

            int fechaNacimiento = 0;
            while (true) {
                try {
                    System.out.print("Año de nacimiento del actor: ");
                    fechaNacimiento = Integer.parseInt(sc.nextLine());
                    if (fechaNacimiento > 1900 && fechaNacimiento < 2025)
                        break;
                    System.out.println("Año de nacimiento no válido.");
                } catch (NumberFormatException e) {
                    System.out.println("Introduce un número válido.");
                }
            }

            int id = ++ultimaId;
            Actor actor = new Actor(nombreactor, apellidoactor, poblacionactor, fechaNacimiento, id);
            CrearficheroActor(actor);
            actores.add(actor);
        }

        return actores;
    }

    public static void CrearficheroActor(Actor Actor) {
        File fichero = new File("../src/dades/actores.txt");

        try {
            if (!fichero.exists()) {
                fichero.getParentFile().mkdirs();
                fichero.createNewFile();
            }

            try (BufferedWriter out = new BufferedWriter(new FileWriter(fichero, true))) {
                String linea = Actor.toStringPaFicheros2();
                if (!linea.equals(".")) {
                    out.write(linea);
                    out.newLine();
                    System.out.println("Texto añadido correctamente al archivo.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error al manipular el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error de tipo " + e.toString());
        }
    }
}
