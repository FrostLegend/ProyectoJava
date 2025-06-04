package Com.Projecte.Main;

import Com.Projecte.Alejandro.DatosAlejandro;
import Com.Projecte.Alvaro.DatosAlvaro;
import Com.Projecte.Josep.Dadesjosep;
import Com.Projecte.Raul.DatosRaul;
import Com.Projecte.src.dev.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ProgramaPrincipal {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Director> directores = Director.cargarDirectores();
    static ArrayList<Actor> actores = Actor.cargarActores();
    static ArrayList<Pelicula> peliculas = Pelicula.cargarPeliculas();
    static ArrayList<Usuari> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        Pelicula.mostrarPeliculas(peliculas);
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
                            if (usuari.getRol()) {
                                menuLoginAdmin(usuari);
                            } else {
                                menuLoginUsuari(usuari);
                            }
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

    public static void menuLoginAdmin(Usuari usuari) {
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
                        menuLoginAdmin(usuari);
                    case 2:
                        Usuari.añadirPersonal(usuari, peliculas, actores, directores, 2);
                        menuLoginAdmin(usuari);
                    case 3:
                        Pelicula.CrearPelicula(peliculas);
                        menuLoginAdmin(usuari);
                    case 4:
                        Pelicula.eliminarPelicula(peliculas);
                        menuLoginAdmin(usuari);
                    case 5:
                        Actor.CrearActor(actores);
                        menuLoginAdmin(usuari);
                    case 6:
                        Actor.eliminarActor(actores);
                        menuLoginAdmin(usuari);
                    case 7:
                        System.out.println("Saliendo...");
                        menuAcces();
                    default:
                        System.out.println("No es una opción válida");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número.");
            }

        } while (true);
    }

    public static void menuLoginUsuari(Usuari usuari) {
        System.out.println("|");
        System.out.println("|");
        System.out.println("|     BENVINGUT " + usuari.getNombre() + "! Tria una opció:");
        System.out.println("|");
        System.out.println("|              1. Añadir Director.");
        System.out.println("|");
        System.out.println("|              2. Añadir Director Personal.");
        System.out.println("|");
        System.out.println("|              3. Eliminar Director Personal.");
        System.out.println("|");
        System.out.println("|              4. Añadir Pel·licula.");
        System.out.println("|");
        System.out.println("|              5. Añadir Pel·licula Personal.");
        System.out.println("|");
        System.out.println("|              6. Eliminar Pel·licula Personal.");
        System.out.println("|");
        System.out.println("|              7. Añadir Actor.");
        System.out.println("|");
        System.out.println("|              8. Añadir Actor Personal.");
        System.out.println("|");
        System.out.println("|              9. Eliminar Actor Personal.");
        System.out.println("|");
        System.out.println("|              10. Eixir.");
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
                        menuLoginUsuari(usuari);
                    case 2:
                        Usuari.añadirPersonal(usuari, peliculas, actores, directores, 2);
                        menuLoginUsuari(usuari);
                    case 3:
                        Director.eliminarDirector(directores);
                        menuLoginUsuari(usuari);
                    case 4:
                        Pelicula.CrearPelicula(peliculas);
                        menuLoginUsuari(usuari);
                    case 5:
                        Usuari.añadirPersonal(usuari, peliculas, actores, directores, 0);
                        menuLoginUsuari(usuari);
                    case 6:
                        Pelicula.eliminarPelicula(peliculas);
                        menuLoginUsuari(usuari);
                    case 7:
                        Actor.CrearActor(actores);
                        menuLoginUsuari(usuari);
                    case 8:
                        Usuari.añadirPersonal(usuari, peliculas, actores, directores, 1);
                        menuLoginUsuari(usuari);
                    case 9:
                        Actor.eliminarActor(actores);
                        menuLoginUsuari(usuari);
                    case 10:
                        System.out.println("Saliendo...");
                        menuAcces();
                    default:
                        System.out.println("No es una opción válida");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número.");
            }

        } while (true);
    }
}