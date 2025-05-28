package Com.Projecte.src.dev;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Director extends Persona {
    static Scanner sc = new Scanner(System.in);

    public Director(String nombre, String apellido, String poblacion, int fechaNacimiento, int id) {
        super(nombre, apellido, poblacion, fechaNacimiento, id);
    }

    public static ArrayList<Director> CrearDirector(ArrayList<Director> directores) {
        File fichero = new File("Com/Projecte/src/dades/Directores.txt");
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
        File fichero = new File("Com/Projecte/src/dades/Directores.txt");

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

    public static void eliminarDirector(ArrayList<Director> directores) {
        File fichero = new File("Com/Projecte/src/dades/Directores.txt");
        if (directores.isEmpty()) {
            System.out.println("No hay directores para eliminar.");
            return;
        }

        System.out.println("Directores existentes:");
        for (Director d : directores) {
            System.out.printf("ID %d: %s %s%n", d.getId(), d.getNombre(), d.getApellido());
        }
        System.out.print("Introduce el ID a eliminar (o -1 para cancelar): ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return;
        }
        if (id == -1) {
            System.out.println("Operación cancelada.");
            return;
        }

        Director toRemove = null;
        for (Director d : directores) {
            if (d.getId() == id) {
                toRemove = d;
                break;
            }
        }
        if (toRemove == null) {
            System.out.println("ID no encontrado.");
            return;
        }

        // Actualizamos la lista en memoria
        directores.remove(toRemove);

        // Ahora actualizamos el archivo, eliminando la línea con ese ID
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(";");
                if (partes.length > 0) {
                    try {
                        int idLinea = Integer.parseInt(partes[0]);
                        if (idLinea != id) {
                            lines.add(line); // Solo añadimos si no coincide el ID a eliminar
                        }
                    } catch (NumberFormatException e) {
                        // Si la línea no tiene un ID válido, la conservamos para evitar pérdida de datos
                        lines.add(line);
                    }
                } else {
                    // Línea vacía o mal formada, conservamos
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo para eliminar: " + e.getMessage());
            return;
        }

        // Escribimos de nuevo el archivo sin la línea eliminada
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, false))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
            System.out.println("Director eliminado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo: " + e.getMessage());
        }
    }

    public static ArrayList<Director> cargarDirectores() {
        ArrayList<Director> directores = new ArrayList<>();
        File fichero = new File("Com/Projecte/src/dades/Directores.txt");
    
        if (!fichero.exists()) {
            // Si no existe el archivo, simplemente retornamos la lista vacía
            return directores;
        }
    
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;  // ignorar líneas vacías
                String[] partes = line.split(";");
                if (partes.length >= 5) {  
                    // Según tu constructor: nombre, apellido, poblacion, fechaNacimiento, id
                    // Asumo que el archivo está guardado en formato: id;nombre;apellido;poblacion;fechaNacimiento
                    try {
                        int id = Integer.parseInt(partes[0]);
                        String nombre = partes[1];
                        String apellido = partes[2];
                        String poblacion = partes[3];
                        int fechaNacimiento = Integer.parseInt(partes[4]);
    
                        Director director = new Director(nombre, apellido, poblacion, fechaNacimiento, id);
                        directores.add(director);
                    } catch (NumberFormatException e) {
                        System.out.println("Error al parsear línea: " + line);
                        // Puedes decidir si quieres seguir o detener la carga
                    }
                } else {
                    System.out.println("Formato incorrecto en línea: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo de directores: " + e.getMessage());
        }
    
        return directores;
    }
}
