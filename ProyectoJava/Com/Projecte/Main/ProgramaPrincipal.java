package Com.Projecte.Main;

import java.util.ArrayList;
import java.util.Scanner;

import Com.Projecte.Josep.Dadesjosep;
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
