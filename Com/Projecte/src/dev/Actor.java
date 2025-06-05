package Com.Projecte.src.dev;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Actor extends Persona implements Gestionable {
    static Scanner sc = new Scanner(System.in);

    // Constructor de Actor heredando la clase Persona
    public Actor(String nombre, String apellido, String poblacion, int fechaNacimiento, int id) {
        super(nombre, apellido, poblacion, fechaNacimiento, id);
    }

    @Override
    public String getIdentificador() {
        return nombre + " " + apellido;
    }

    @Override
    public String resum() {
        return nombre + " " + apellido + ", fecha nacimiento: " + fechaNacimiento;
    }

    @Override
    public void mostrarDetalls() {
        System.out.println("Nombre completo: " + nombre + " " + apellido);
        System.out.println("Población: " + poblacio);
        System.out.println("Fecha de nacimiento: " + fechaNacimiento);
        System.out.println("ID: " + id);
    }

    public static ArrayList<Actor> CrearActor(ArrayList<Actor> actores) { // Método para crear el actor
        File fichero = new File("Com/Projecte/src/dades/Actores.txt"); // Indicamos la ruta del archivo donde guardas los actores
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

        System.out.println("Introduce los datos de los actores (escribe 'fin' en el nombre para terminar):");

        while (true) { // Introducción de datos del actor
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
            while (true) { // Verificacion de la fecha de nacimiento del actor
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

            if (fichero.exists()) { // Comprueba que el actor no existe ya en el archivo
                if (buscarLineaPorNombreAppellido(fichero, nombre, apellido) != null) {
                    System.out.println("El actor ya existe.");
                    return actores;
                }
            }

            int id = ++ultimaId; // Autoincrementa la id cada vez que se crea un actor
            Actor actor = new Actor(nombre, apellido, poblacion, fechaNacimiento, id); // Crea el actor con el constructor
            CrearficheroActor(actor); // Accede al método para crear el fichero donde se guardan los actores
            actores.add(actor); // Añade el actor al ArrayList de actores
        }

        return actores;
    } // Fin CrearActor()

    public static void CrearficheroActor(Actor actor) { // Método que crea el archivo donde guardamos los actores
        File fichero = new File("Com/Projecte/src/dades/Actores.txt"); // Ruta del archivo donde guardamos los actores

        try {
            if (!fichero.exists()) { // Si no existe el archivo crea tanto el directorio, como el archivo
                fichero.getParentFile().mkdirs();
                fichero.createNewFile();
            }

            try (BufferedWriter out = new BufferedWriter(new FileWriter(fichero, true))) { // Lee el fichero y añade el actor introducido en el metodo CrearActor()
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
    } // Fin CrearFicheroActor()

    public static void eliminarActor(ArrayList<Actor> actores) { // Método para eliminar actores
        File fichero = new File("Com/Projecte/src/dades/Actores.txt");
        
        if (actores.isEmpty()) {
            System.out.println("No hay actores para eliminar.");
            return;
        }
        // Mostrar lista de actores existentes y sus IDs
        System.out.println("Actores existentes:");
        for (Actor a : actores) {
            System.out.printf("ID %d: %s %s%n", a.getId(), a.getNombre(), a.getApellido());
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
        // Al introducir la id del actor a eliminar, se busca esa id en el ArrayList de actores.
        Actor toRemove = null;
        for (Actor a : actores) {
            if (a.getId() == id) {
                toRemove = a; break;
            }
        }
        if (toRemove == null) { // Si la id no está en el ArrayList, no hace nada
            System.out.println("ID no encontrado.");
            return;
        }
        actores.remove(toRemove); // Si está la id en el ArrayList, borra ese usuario de la ArrayList

        // Reescribir fichero sin el eliminado
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) { // Lee el fichero
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
            System.out.println("Actor eliminado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo: " + e.getMessage());
        }
    }

    public static ArrayList<Actor> cargarActores() { // Cargamos el ArrayList de acttores cuando la llamamos
        ArrayList<Actor> actores = new ArrayList<>();
        File fichero = new File("Com/Projecte/src/dades/Actores.txt");
    
        if (!fichero.exists()) {
            // Si no existe el archivo, simplemente retornamos la lista vacía
            return actores;
        }
    
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) { // Leemos el archivo
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;  // ignorar líneas vacías
                String[] partes = line.split(";");
                if (partes.length >= 5) {  
                    try { // Adjuntamos los datos del actor teniendo en cuenta donde están los dats en el archivo
                        int id = Integer.parseInt(partes[0]);
                        String nombre = partes[1];
                        String apellido = partes[2];
                        String poblacion = partes[3];
                        int fechaNacimiento = Integer.parseInt(partes[4]);
    
                        Actor actor = new Actor(nombre, apellido, poblacion, fechaNacimiento, id);
                        actores.add(actor);
                    } catch (NumberFormatException e) {
                        System.out.println("Error al parsear línea: " + line);
                        // Puedes decidir si quieres seguir o detener la carga
                    }
                } else {
                    System.out.println("Formato incorrecto en línea: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo de actores: " + e.getMessage());
        }
    
        return actores;
    } // Fin cargarActores()

    public static void mostrarActores(ArrayList<Actor> actores) { // Método para mostrar los actores actuales
        System.out.println("Actores existentes:");
        for (Actor a : actores) {
            System.out.printf("ID %d: %s %s%n", a.getId(), a.getNombre(), a.getApellido());
        }
    } // Fin mostrarActores()

    public static String buscarLineaPorNombreAppellido(File fichero, String nombre, String apellido) { //Método para buscar en el archivo el actor por nombre y apellido
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) { // Leemos el archivo
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes.length > 0 && partes[1].equalsIgnoreCase(nombre) && partes[2].equalsIgnoreCase(apellido)) {
                        // Si el nombre y apellido que damos al programa coincide con un actor del archivo, devuelve ese actor
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
    } // Fin buscarLineaPorNombreAppellido()
}