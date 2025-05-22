package Com.Projecte.Main;

import Com.Projecte.Josep.Dadesjosep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Com.Projecte.Alejandro.DatosAlejandro;
import Com.Projecte.Alvaro.DatosAlvaro;
import Com.Projecte.Raul.DatosRaul;
import Com.Projecte.src.dev.*;

public class ProgramaPrincipal {
    static Scanner sc = new Scanner(System.in);
    ArrayList<Director> directores = new ArrayList<>();
    ArrayList<Actor> actores = new ArrayList<>();
    ArrayList<Pelicula> peliculas = new ArrayList<>();
    ArrayList<Usuari> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        inici();
    }

    public static void inici() {
        Dadesjosep.mostrarNom();
        DatosAlejandro.mostrarNombre();
        DatosAlvaro.mostrarNombre();
        DatosRaul.mostrarNombre();
    }

    public static void CrearficheroPelicula(Pelicula pelicula) {
        File fichero = new File("../src/dades/peliculas.txt");

        try {
            if (!fichero.exists()) {
                fichero.getParentFile().mkdirs();
                fichero.createNewFile();
            }

            try (BufferedWriter out = new BufferedWriter(new FileWriter(fichero, true))) {
                String linea = pelicula.toStringPaFicheros();
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

    public static void CrearficheroUsuario(Usuari usuario) {
        File fichero = new File("../src/dades/Usuarios.txt");

        try {
            if (!fichero.exists()) {
                fichero.getParentFile().mkdirs();
                fichero.createNewFile();
            }

            try (BufferedWriter out = new BufferedWriter(new FileWriter(fichero, true))) {
                String linea = usuario.toStringPaFicheros2();
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

    public static void CrearficheroDirector(Director director) {
        File fichero = new File("../src/dades/director.txt");

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

    public static Pelicula AfegirPelicula(ArrayList<Director> directores, ArrayList<Actor> actores) {
        int año = 0;
        String titulo = "";
        int durada = 0;
        actores = new ArrayList<>();
        directores = new ArrayList<>();

        File fichero = new File("../src/dades/Peliculas.txt");
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
                durada = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Duración inválida. Intenta de nuevo.");
            }
        }
        int id = ++ultimaId;
        CrearficheroPelicula(new Pelicula(año, titulo, durada, actores, directores, id));
        return new Pelicula(año, titulo, durada, CrearActor(actores), CrearDirector(directores), id);
    }

    public static ArrayList<Usuari> crearUsuari(ArrayList<Usuari> usuaris) {
        File fichero = new File("../src/dades/Usuarios.txt");
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

        System.out.println("Introduce los datos de los usuarios (escribe 'fin' en el nombre para terminar):");
        while (true) {
            System.out.print("Nombre del usuario: ");
            String nombre = sc.nextLine();
            if (nombre.equalsIgnoreCase("fin"))
                break;

            System.out.print("Apellido del usuario: ");
            String apellido = sc.nextLine();
            if (apellido.equalsIgnoreCase("fin"))
                break;

            System.out.print("Email del usuario: ");
            String email = sc.nextLine();
            if (email.equalsIgnoreCase("fin"))
                break;

            System.out.print("Password del usuario: ");
            String password = sc.nextLine();
            if (password.equalsIgnoreCase("fin"))
                break;

            System.out.print("Población del usuario: ");
            String poblacio = sc.nextLine();
            if (poblacio.equalsIgnoreCase("fin"))
                break;

            Boolean rol = false;

            int fechaNacimiento = 0;
            while (true) {
                try {
                    System.out.print("Año de nacimiento del usuario: ");
                    fechaNacimiento = Integer.parseInt(sc.nextLine());
                    if (fechaNacimiento > 1900 && fechaNacimiento < 2025)
                        break;
                    System.out.println("Año de nacimiento no válido.");
                } catch (NumberFormatException e) {
                    System.out.println("Introduce un número válido.");
                }
            }

            int id = ++ultimaId;
            Usuari usuario = new Usuari(nombre, apellido, email, password, poblacio, rol, fechaNacimiento, id);
            CrearficheroUsuario(usuario);
            usuaris.add(usuario);
        }

        return usuaris;
    }

    public static ArrayList<Director> CrearDirector(ArrayList<Director> directores) {
        File fichero = new File("../src/dades/Directores.txt");
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

    public static ArrayList<Actor> CrearActor(ArrayList<Actor> actores) {
        File fichero = new File("../src/dades/Actores.txt");
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

}
