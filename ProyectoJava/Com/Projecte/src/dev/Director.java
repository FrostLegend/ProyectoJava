package Com.Projecte.src.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Director extends Persona {
    static Scanner sc = new Scanner(System.in);

    public Director(String nombre, String apellido, String poblacio, int fechaNacimiento, int id) {
        super(nombre, apellido, poblacio, fechaNacimiento, id);
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
            String nombredirector = sc.nextLine();
            if (nombredirector.equalsIgnoreCase("fin"))
                break;

            System.out.print("Apellido del director: ");
            String apellidodirector = sc.nextLine();
            if (apellidodirector.equalsIgnoreCase("fin"))
                break;

            System.out.print("Población del director: ");
            String poblaciondirector = sc.nextLine();
            if (poblaciondirector.equalsIgnoreCase("fin"))
                break;

            int fechaNacimiento = 0;
            while (true) {
                try {
                    System.out.print("Año de nacimiento del director: ");
                    fechaNacimiento = Integer.parseInt(sc.nextLine());
                    if (fechaNacimiento > 1900 && fechaNacimiento < 2025)
                        break;
                    System.out.println("Año de nacimiento no válido.");
                } catch (NumberFormatException e) {
                    System.out.println("Introduce un número válido.");
                }
            }

            int id = ++ultimaId;
            Director director = new Director(nombredirector, apellidodirector, poblaciondirector, fechaNacimiento, id);
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
                String linea = director.toStringPaFicheros2();
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
