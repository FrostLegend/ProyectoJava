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
        System.out.println("-----ACCES USUARI-----");
        System.out.println("1. Login.");
        System.out.println("2. Registre.");
        System.out.println("3. Eixir.");

        do {
            System.out.println("------ACCESO PÁGINA------");
            System.out.println("1. Login");
            System.out.println("2. Registro");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcio;
            String input = sc.nextLine();
            try {
                opcio = Integer.parseInt(input); 

                switch (opcio) {
                    case 1:
                        login();
                        break;
                    case 2:
                        Usuari.crearUsuari(usuarios);
                        break;
                    case 3:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("No es una opción válida");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número.");
            }

        } while (true);
    }

    public static void login() {
        System.out.println("");
    }

    public static void registre() {

    }

}
