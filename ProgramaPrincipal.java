package Com.Projecte.Main;

import Com.Projecte.Josep.Dadesjosep;

import java.util.ArrayList;
import java.util.Scanner;

import Com.Projecte.Alejandro.DatosAlejandro;
import Com.Projecte.Alvaro.DatosAlvaro;
import Com.Projecte.Raul.DatosRaul;
import Com.Projecte.src.dev.*;

public class ProgramaPrincipal {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Director> directores = new ArrayList<>();
    static ArrayList<Actor> actores = new ArrayList<>();
    static ArrayList<Pelicula> peliculas = new ArrayList<>();
    static ArrayList<Usuari> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        inici();
        menuAcces();
    }

    public static void inici() {
        Dadesjosep.mostrarNom();
        DatosAlejandro.mostrarNombre();
        DatosAlvaro.mostrarNombre();
        DatosRaul.mostrarNombre();
    }

    public static void menuAcces() {
        System.out.println("|");
        System.out.println("|");
        System.out.println("|                 ACCÉS USUARI.");
        System.out.println("|");
        System.out.println("|              1. Login.");
        System.out.println("|");
        System.out.println("|              2. Registre.");
        System.out.println("|");
        System.out.println("|              3. Eixir.");
        System.out.println("|");
        System.out.println("|______________________________________________________");

        do {
            int opcio;
            String input = sc.nextLine();
            try {
                opcio = Integer.parseInt(input);

                switch (opcio) {
                    case 1:
                        Usuari usuari = Usuari.login();
                        if (usuari != null) {
                            menuLogin(usuari);
                        }
                        break;
                    case 2:
                        Usuari.crearUsuari(usuarios);
                        break;
                    case 3:
                        System.out.println("Saliendo...");
                        System.exit(opcio);
                    default:
                        System.out.println("No es una opción válida");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número.");
            }

        } while (true);
    }

    public static void menuLogin(Usuari usuari) {
        System.out.println("|");
        System.out.println("|");
        System.out.println("|     BENVINGUT " + usuari.getNombre() + "! Tria una opció:");
        System.out.println("|");
        System.out.println("|              1. Añadir Director.");
        System.out.println("|");
        System.out.println("|              2. Eliminar Director.");
        System.out.println("|");
        System.out.println("|              3. Añadir Pel·licula.");
        System.out.println("|");
        System.out.println("|              4. Eliminar Pel·licula.");
        System.out.println("|");
        System.out.println("|              5. Añadir Actor.");
        System.out.println("|");
        System.out.println("|              6. Eliminar Actor.");
        System.out.println("|");
        System.out.println("|              7. Eixir.");
        System.out.println("|");
        System.out.println("|______________________________________________________");
        do {
            int opcio;
            String input = sc.nextLine();
            try {
                opcio = Integer.parseInt(input);

                switch (opcio) {
                    case 1:
                        Director.CrearDirector(directores);
                        break;
                    case 2:
                        // meter aqui eliminar director
                        break;
                    case 3:
                        Pelicula.afegirPelicula(directores, actores);
                        break;
                    case 4:
                        // meter aqui eliminar pelicula
                        break;
                    case 5:
                        Actor.CrearActor(actores);
                        break;
                    case 6:
                        // meter aqui eliminar actor
                        break;
                    case 7:
                        System.out.println("Saliendo...");
                        System.exit(opcio);
                    default:
                        System.out.println("No es una opción válida");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número.");
            }

        } while (true);
    }
}