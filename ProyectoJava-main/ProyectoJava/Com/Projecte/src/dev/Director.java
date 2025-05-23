package Com.Projecte.src.dev;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Director extends Persona {
    static Scanner sc = new Scanner(System.in);

    public Director(String nombre, String apellido, String poblacion, int fechaNacimiento, int id) {
        super(nombre, apellido, poblacion, fechaNacimiento, id);
    }

    public static ArrayList<Director> CrearDirector(ArrayList<Director> directores) {
        File fichero = new File("../dades/Directores.txt");
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

        System.out.println("Introduce los datos de los directores (escribe 'fin' en el nombre para terminar):");

        while (true) {
            System.out.print("Nombre del director: ");
            String nombre = sc.nextLine();
            if (nombre.equalsIgnoreCase("fin"))
                break;

            System.out.print("Apellido del director: ");
            String apellido = sc.nextLine();
            if (apellido.equalsIgnoreCase("fin"))
                break;

            System.out.print("Población del director: ");
            String poblacion = sc.nextLine();
            if (poblacion.equalsIgnoreCase("fin"))
                break;

            int fechaNacimiento = 0;
            while (true) {
                try {
                    System.out.print("Año de nacimiento del director: ");
                    fechaNacimiento = Integer.parseInt(sc.nextLine());
                    if (fechaNacimiento >= 1900 && fechaNacimiento <= 2024) {
                        break;
                    } else {
                        System.out.println("Año de nacimiento fuera de rango válido.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Introduce un número válido.");
                }
            }

            int id = ++ultimaId;
            Director director = new Director(nombre, apellido, poblacion, fechaNacimiento, id);
            CrearficheroDirector(director);
            directores.add(director);
        }

        return directores;
    }

    public static void CrearficheroDirector(Director director) {
        File fichero = new File("../dades/Directores.txt");

        try {
            if (!fichero.exists()) {
                fichero.getParentFile().mkdirs();
                fichero.createNewFile();
            }

            try (BufferedWriter out = new BufferedWriter(new FileWriter(fichero, true))) {
                String linea = director.toStringPaFicherosActores_Directores();
                out.write(linea);
                out.newLine();
                System.out.println("Director añadido correctamente al archivo.");
            }

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.toString());
        }
    }
}
