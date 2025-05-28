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

public class Pelicula implements Serializable {
    protected int id;
    protected int any;
    protected String titol;
    protected int durada;
    protected ArrayList<Actor> actores;
    protected ArrayList<Director> directores;
    static Scanner sc = new Scanner(System.in);

    public Pelicula(int any, String titol, int durada, ArrayList<Actor> actores, ArrayList<Director> directores,
            int id) {
        this.id = id;
        this.any = any;
        this.titol = titol;
        this.durada = durada;
        this.actores = actores;
        this.directores = directores;
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
        return id + ";" + titol + ";" + recorrerArrayList(actores) + ";" + recorrerArrayList(directores) + ";" +
                any + ";" + durada;
    }

    public <T> String recorrerArrayList(ArrayList<T> lista) {
        StringBuilder sb = new StringBuilder("|");
        for (T item : lista) {
            if (item instanceof Actor) {
                sb.append(((Actor) item).getNombre()).append("|");
            } else if (item instanceof Director) {
                sb.append(((Director) item).getNombre()).append("|");
            } else {
                sb.append(item.toString()).append("|");
            }
        }
        return sb.toString();
    }

    public static void crearFicheroPelicula(Pelicula pelicula) {
        File fichero = new File("Com/Projecte/src/dev/dades/peliculas.txt");

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

    public static Pelicula afegirPelicula(ArrayList<Director> directoresExistentes,
            ArrayList<Actor> actoresExistentes) {
        int año = 0;
        String titulo = "";
        int duracion = 0;

        File fichero = new File("Com/Projecte/src/dev/dades/peliculas.txt");
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

        while (true) {
            try {
                System.out.print("Dime el año: ");
                año = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Año inválido. Intenta de nuevo.");
            }
        }

        while (true) {
            System.out.print("Dime el título de la película: ");
            titulo = sc.nextLine();
            if (!titulo.isBlank())
                break;
            System.out.println("El título no puede estar vacío.");
        }

        while (true) {
            try {
                System.out.print("Dime la duración de la película: ");
                duracion = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Duración inválida. Intenta de nuevo.");
            }
        }

        ArrayList<Actor> actores = Actor.CrearActor(actoresExistentes);
        ArrayList<Director> directores = Director.CrearDirector(directoresExistentes);

        int nuevaId = ++ultimaId;
        Pelicula nuevaPelicula = new Pelicula(año, titulo, duracion, actores, directores, nuevaId);
        crearFicheroPelicula(nuevaPelicula);
        return nuevaPelicula;
    }

    public static void eliminarPelicula(ArrayList<Pelicula> peliculas) {
        File fichero = new File("Com/Projecte/src/dev/dades/peliculas.txt");
        if (peliculas.isEmpty()) {
            System.out.println("No hay películas para eliminar.");
            return;
        }
        System.out.println("Películas existentes:");
        for (Pelicula p : peliculas) {
            System.out.printf("ID %d: %s%n", p.getId(), p.getTitol());
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
        Pelicula toRemove = null;
        for (Pelicula p : peliculas) {
            if (p.getId() == id) { toRemove = p; break; }
        }
        if (toRemove == null) {
            System.out.println("ID no encontrado.");
            return;
        }
        peliculas.remove(toRemove);
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
            System.out.println("Película eliminada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo: " + e.getMessage());
        }
    }
}
