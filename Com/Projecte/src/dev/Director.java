package Com.Projecte.src.dev;

// Importación métodos de Java
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Director extends Persona implements Gestionable {
    static Scanner sc = new Scanner(System.in);

     // Constructor de Director heredando la clase Persona
    public Director(String nombre, String apellido, String poblacion, int fechaNacimiento, int id) {
        super(nombre, apellido, poblacion, fechaNacimiento, id);
    }
    
    @Override
    public String getIdentificador() {
        return nombre + " " + apellido;
    }

    @Override
    public String resum() {
        return nombre + " " + apellido + ", de " + poblacio;
    }

    @Override
    public void mostrarDetalls() {
        System.out.println("Nombre completo: " + nombre + " " + apellido);
        System.out.println("Población: " + poblacio);
        System.out.println("Fecha de nacimiento: " + fechaNacimiento);
        System.out.println("ID: " + id);
    }

    public static ArrayList<Director> CrearDirector(ArrayList<Director> directores) { // Método para crear el director
        File fichero = new File("Com/Projecte/src/dades/Directores.txt"); // Indicamos la ruta del archivo donde guardas los directores
        int ultimaId = -1; // Iniciacion de la id (al crear usuario tendrá ID 0)

        if (fichero.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fichero))) { // Leemos el archivo
                String linea; 
                while ((linea = br.readLine()) != null) {
                    if (!linea.isEmpty()) { // Mientras la linea no esté vacia, divide los datos del actor con ";"
                        String[] partes = linea.split(";");
                        if (partes.length > 0) {
                            try {
                                int idLinea = Integer.parseInt(partes[0]); // La id siempre será el primer parametro en el archivo
                                if (idLinea > ultimaId) {
                                    ultimaId = idLinea;
                                }
                            } catch (NumberFormatException ignored) {
                            }
                        }
                    }
                }
            } catch (IOException e) { // Si no se puede abrir el archivo muestra el error.
                System.out.println("Error leyendo el archivo para obtener la última ID: " + e.getMessage());
            }
        }

        System.out.println("Introduce los datos de los directores (escribe 'fin' en el nombre para terminar):");

        while (true) { // Introducción de datos del director
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
            while (true) { // Verificacion de la fecha de nacimiento del director
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

            if (fichero.exists()) { // Comprueba que el director no existe ya en el archivo
                if (buscarLineaPorNombreAppellido(fichero, nombre, apellido) != null) {
                    System.out.println("El director ya existe.");
                    return directores;
                }
            }

            int id = ++ultimaId; // Autoincrementa la id cada vez que se crea un director
            Director director = new Director(nombre, apellido, poblacion, fechaNacimiento, id); // Crea el director con el constructor
            CrearficheroDirector(director); // Accede al método para crear el fichero donde se guardan los directores
            directores.add(director); // Añade el actor al ArrayList de actores
        }

        return directores;
    } // Fin CrearDirector()

    public static void CrearficheroDirector(Director director) { // Método que crea el archivo donde guardamos los directores
        File fichero = new File("Com/Projecte/src/dades/Directores.txt"); // Ruta del archivo donde guardamos los directores

        try {
            if (!fichero.exists()) { // Si no existe el archivo crea tanto el directorio, como el archivo
                fichero.getParentFile().mkdirs();
                fichero.createNewFile();
            }

            try (BufferedWriter out = new BufferedWriter(new FileWriter(fichero, true))) { // Lee el fichero y añade el actor introducido en el metodo CrearDirector()
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
    } // Fin CrearFicheroDirector()

    public static void eliminarDirector(ArrayList<Director> directores) { // Método para eliminar un director
        File fichero = new File("Com/Projecte/src/dades/Directores.txt"); // Ruta del fichero con los directores
        if (directores.isEmpty()) { // Si está vacio, no hace nada
            System.out.println("No hay directores para eliminar.");
            return;
        }

        System.out.println("Directores existentes:");
        for (Director d : directores) { // Muestra los directores existentes
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

        // Si el id introducido coincide con un director, lo elimina
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
    } // Fin eliminarDirector()

    public static ArrayList<Director> cargarDirectores() { // Cargamos el ArrayList de actores cuando lo llamamos
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
                    try { // Adjuntamos los datos del director teniendo en cuenta donde están los datos en el archivo
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
    } // Fin cargarDirectores()

    public static void mostrarDirectores(ArrayList<Director> directores) { // Método para mostrar los directores actuales
        System.out.println("Directores existentes:");
        for (Director d : directores) {
            System.out.printf("ID %d: %s %s%n", d.getId(), d.getNombre(), d.getApellido());
        }
    } // Fin mostrarDirectores()

    public static String buscarLineaPorNombreAppellido(File fichero, String nombre, String apellido) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes.length > 0 && partes[1].equalsIgnoreCase(nombre) && partes[2].equalsIgnoreCase(apellido)) {
                        return linea;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.toString());
        }
        return null;
    }
}
