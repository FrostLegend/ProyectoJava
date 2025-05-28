package Com.Projecte.src.dev;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Actor extends Persona {
    static Scanner sc = new Scanner(System.in);

    public Actor(String nombre, String apellido, String poblacion, int fechaNacimiento, int id) {
        super(nombre, apellido, poblacion, fechaNacimiento, id);
    }

    public static ArrayList<Actor> CrearActor(ArrayList<Actor> actores) {
        File fichero = new File("Com/Projecte/src/dades/Actores.txt");
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
            String nombre = sc.nextLine();
            if (nombre.equalsIgnoreCase("fin"))
                break;

            System.out.print("Apellido del actor: ");
            String apellido = sc.nextLine();
            if (apellido.equalsIgnoreCase("fin"))
                break;

            System.out.print("Población del actor: ");
            String poblacion = sc.nextLine();
            if (poblacion.equalsIgnoreCase("fin"))
                break;

            int fechaNacimiento = 0;
            while (true) {
                try {
                    System.out.print("Año de nacimiento del actor: ");
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
            Actor actor = new Actor(nombre, apellido, poblacion, fechaNacimiento, id);
            CrearficheroActor(actor);
            actores.add(actor);
        }

        return actores;
    }

    public static void CrearficheroActor(Actor actor) {
        File fichero = new File("Com/Projecte/src/dades/Actores.txt");

        try {
            if (!fichero.exists()) {
                fichero.getParentFile().mkdirs();
                fichero.createNewFile();
            }

            try (BufferedWriter out = new BufferedWriter(new FileWriter(fichero, true))) {
                String linea = actor.toStringPaFicherosActores_Directores();
                out.write(linea);
                out.newLine();
                System.out.println("actor añadido correctamente al archivo.");
            }

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.toString());
        }
    }

    public static void eliminarActor(ArrayList<Actor> actores) {
        File fichero = new File("Com/Projecte/src/dades/Actores.txt");
        
        if (actores.isEmpty()) {
            System.out.println("No hay actores para eliminar.");
            return;
        }
        // Mostrar lista y sus IDs
        System.out.println("Actores existentes:");
        for (Actor a : actores) {
            System.out.printf("ID %d: %s %s%n", a.getId(), a.getNombre(), a.getApellido());
        }
        System.out.print("Introduce el ID a eliminar (o 0 para cancelar): ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return;
        }
        if (id == 0) {
            System.out.println("Operación cancelada.");
            return;
        }
        Actor toRemove = null;
        for (Actor a : actores) {
            if (a.getId() == id) { toRemove = a; break; }
        }
        if (toRemove == null) {
            System.out.println("ID no encontrado.");
            return;
        }
        actores.remove(toRemove);
        // Reescribir fichero sin el eliminado
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(id + ";")) {
                    lines.add(line);
                }
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, false))) {
                for (String l : lines) {
                    bw.write(l);
                    bw.newLine();
                }
            }
            System.out.println("Actor eliminado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo: " + e.getMessage());
        }
    }
}