package Com.Projecte.src.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pelicula implements Serializable, Gestionable, Comparable<Pelicula> {
    protected int id;
    protected int any;
    protected String titol;
    protected int durada;
    static Scanner sc = new Scanner(System.in);

    public Pelicula(int any, String titol, int durada, int id) {
        this.id = id;
        this.any = any;
        this.titol = titol;
        this.durada = durada;

    }

    public int getId() {
        return id;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public int getDurada() {
        return durada;
    }

    public void setDurada(int durada) {
        this.durada = durada;
    }

    public String toStringPaPeliculas() {
        return id + ";" + titol + ";" + any + ";" + durada;
    }

    @Override
    public String getIdentificador() { //Titulo
        return titol;
    }

    @Override
    public String resum() { // Descripcion resumida para listados
        return titol + " (" + any + ")";
    }

    @Override
    public void mostrarDetalls() { //Todos los detalles de la pelicula
        System.out.println("Títol: " + titol);
        System.out.println("Any: " + any);
    }

    @Override
    public int compareTo(Pelicula o) {
        return this.titol.compareToIgnoreCase(o.titol);
    }

    public static void crearFicheroPelicula(Pelicula pelicula) {
        File fichero = new File("Com/Projecte/src/dades/Peliculas.txt");

        try {
            if (!fichero.exists()) {
                fichero.getParentFile().mkdirs();
                fichero.createNewFile();
            }

            try (BufferedWriter out = new BufferedWriter(new FileWriter(fichero, true))) {
                String linea = pelicula.toStringPaPeliculas();
                out.write(linea);
                out.newLine();
                System.out.println("Película añadida correctamente al archivo.");
            }

        } catch (IOException e) {
            System.out.println("Error al manipular el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error de tipo: " + e.toString());
        }
    }

    public static ArrayList<Pelicula> CrearPelicula(ArrayList <Pelicula> peliculas) {
        File fichero = new File("Com/Projecte/src/dades/Peliculas.txt");
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

        int año = 0;
        while (true) {
            try {
                System.out.print("Dime el año: ");
                año = Integer.parseInt(sc.nextLine());
                if (año >= 1900) {
                    break;
                } else {
                    System.out.println("Año de la pelicula está fuera del rango válido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
            }
        }

        System.out.print("Dime el título de la película: ");
            String titulo = sc.nextLine();
            if (titulo.equalsIgnoreCase("fin"))
                return peliculas;

        int duracion = 0;
        while (true) {
            try {
                System.out.print("Dime la duración de la película: ");
                duracion = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Duración inválida. Intenta de nuevo.");
            }
        }

        if (fichero.exists()) {
            if (buscarLineaPorTitulo(fichero, titulo) != null) {
                System.out.println("La película ya existe.");
                return peliculas;
            }
        }

        int nuevaId = ++ultimaId;
        Pelicula nuevaPelicula = new Pelicula(año, titulo, duracion, nuevaId);
        crearFicheroPelicula(nuevaPelicula);
        peliculas.add(nuevaPelicula);
        return peliculas;
    }

    public static void eliminarPelicula(ArrayList<Pelicula> peliculas) {
        File fichero = new File("Com/Projecte/src/dades/Peliculas.txt");
        if (peliculas.isEmpty()) {
            System.out.println("No hay películas para eliminar.");
            return;
        }
        System.out.println("Películas existentes:");
        for (Pelicula p : peliculas) {
            System.out.printf("ID %d: %s%n", p.getId(), p.getTitol());
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
        Pelicula toRemove = null;
        for (Pelicula p : peliculas) {
            if (p.getId() == id) {
                toRemove = p;
                break;
            }
        }
        if (toRemove == null) {
            System.out.println("ID no encontrado.");
            return;
        }
        peliculas.remove(toRemove);
        
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
            System.out.println("Pelicula eliminado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo: " + e.getMessage());
        }
    }

    public static ArrayList<Pelicula> cargarPeliculas() {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        File fichero = new File("Com/Projecte/src/dades/Peliculas.txt");
    
        if (!fichero.exists()) {
            // Si no existe el archivo, simplemente retornamos la lista vacía
            return peliculas;
        }
    
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;  // ignorar líneas vacías
                String[] partes = line.split(";");
                if (partes.length >= 4) {  
                    try {
                        int id = Integer.parseInt(partes[0]);
                        String titulo = partes[1];
                        int año = Integer.parseInt(partes[2]);
                        int duracion = Integer.parseInt(partes[3]);
    
                        Pelicula pelicula = new Pelicula(año, titulo, duracion, id);
                        peliculas.add(pelicula);
                    } catch (NumberFormatException e) {
                        System.out.println("Error al parsear línea: " + line);
                        // Puedes decidir si quieres seguir o detener la carga
                    }
                } else {
                    System.out.println("Formato incorrecto en línea: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo de peliculas: " + e.getMessage());
        }
    
        return peliculas;
    }

    public static void mostrarPeliculas(ArrayList<Pelicula> peliculas){
        System.out.println("Películas existentes:");
        for (Pelicula p : peliculas) {
            System.out.printf("ID %d: %s%n", p.getId(), p.getTitol());
        }
    }

    public static String buscarLineaPorTitulo(File fichero, String titulo) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes.length > 0 && partes[1].replace(" ", "").equalsIgnoreCase(titulo.replace(" ", ""))) {
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
