package Com.Projecte.Main;

import Com.Projecte.Josep.Dadesjosep;

import java.io.BufferedWriter;
import java.io.FileWriter;
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

    public static void main(String[] args) {
        inici();
    }

    public static void inici() {
        Dadesjosep.mostrarNom();
        DatosAlejandro.mostrarNombre();
        DatosAlvaro.mostrarNombre();
        DatosRaul.mostrarNombre();
    }

    public static void CrearficheroUsuario(Usuari usuario) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("../src/dades/Usuarios.txt", true));) {
            String linea = usuario.toStringPaFicheros();
            if (!linea.equals(".")) {
                out.write(linea);
                out.newLine();
            } else {
                return;
            }
            System.out.println("Texto añadido correctamente al archivo ");
        } catch (Exception e) {
            System.out.println("Error de tipo " + e.toString());
        }
    }

    public static void CrearficheroActor(Actor Actor) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("../src/dades/Actores.txt", true));) {
            String linea = Actor.toStringPaFicheros2();
            if (!linea.equals(".")) {
                out.write(linea);
                out.newLine();
            } else {
                return;
            }
            System.out.println("Texto añadido correctamente al archivo ");
        } catch (Exception e) {
            System.out.println("Error de tipo " + e.toString());
        }
    }

    public static void CrearficheroDirector(Director director) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("../src/dades/director.txt", true));) {
            String linea = director.toStringPaFicheros2();
            if (!linea.equals(".")) {
                out.write(linea);
                out.newLine();
            } else {
                return;
            }
            System.out.println("Texto añadido correctamente al archivo ");
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

        return new Pelicula(año, titulo, durada, CrearActor(actores), CrearDirector(directores));
    }

    public static ArrayList<Usuari> crearUsuari(ArrayList<Usuari> usuaris) {
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

            Boolean rol = null;
            while (rol == null) {
                System.out.print("¿Es administrador? (true/false): ");
                String entradaRol = sc.nextLine();
                if (entradaRol.equalsIgnoreCase("fin"))
                    return usuaris;
                if (entradaRol.equalsIgnoreCase("true") || entradaRol.equalsIgnoreCase("false")) {
                    rol = Boolean.parseBoolean(entradaRol);
                } else {
                    System.out.println("Introduce 'true' o 'false'.");
                }
            }

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

            int id = 0;
            while (true) {
                try {
                    System.out.print("ID del usuario: ");
                    id = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Introduce un número válido.");
                }
            }

            usuaris.add(new Usuari(nombre, apellido, email, password, poblacio, rol, fechaNacimiento, id));
        }
        return usuaris;
    }

    public static ArrayList<Director> CrearDirector(ArrayList<Director> directores) {
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

            directores.add(new Director(nombredirector, apellidodirector, poblaciondirector, fechaNacimiento));
        }
        return directores;
    }

    public static ArrayList<Actor> CrearActor(ArrayList<Actor> actores) {
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

            actores.add(new Actor(nombreactor, apellidoactor, poblacionactor, fechaNacimiento));
        }
        return actores;

    }
}
