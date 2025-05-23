package Com.Projecte.src.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
        return id + ";" + recorrerArrayList(actores) + ";" + recorrerArrayList(directores) + ";" +
                any + ";" + titol + ";" + durada;
    }

    public <T> String recorrerArrayList(ArrayList<T> lista) {
        StringBuilder sb = new StringBuilder("|");
        for (T item : lista) {
            sb.append(item.toString()).append("|");
        }
        return sb.toString();
    }

    public static void crearFicheroPelicula(Pelicula pelicula) {
        File fichero = new File("../dades/peliculas.txt");

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

        File fichero = new File("../dades/peliculas.txt");
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
}
