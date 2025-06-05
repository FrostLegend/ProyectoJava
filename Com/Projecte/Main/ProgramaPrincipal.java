package Com.Projecte.Main;

//Importación de los paquetes de los miembros y de los objetos.
import Com.Projecte.Alejandro.DatosAlejandro;
import Com.Projecte.Alvaro.DatosAlvaro;
import Com.Projecte.Josep.Dadesjosep;
import Com.Projecte.Raul.DatosRaul;
import Com.Projecte.src.dev.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProgramaPrincipal { //Declaracion de Scanner estatico y carga de ArrayList de los objetos.
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

    public static void inici() { //Muestra los miembros del equipo.
        Dadesjosep.mostrarNom();
        DatosAlejandro.mostrarNombre();
        DatosAlvaro.mostrarNombre();
        DatosRaul.mostrarNombre();

    }

    public static void menuAcces() { //Menu de acceso.
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
                opcio = Integer.parseInt(input); //Pasamos el string a int.

                switch (opcio) {
                    case 1:
                        Usuari usuari = Usuari.login(); //Accede a el metodo de login de la clase Usuari.
                        if (usuari != null) {
                            if (usuari.getRol()) {
                                menuLoginAdmin(usuari); //Mientras el usuario sea admin, accederá al menú de login de admin.
                            } else {
                                menuLoginUsuari(usuari); //Sino, entrará al de usuario normal.
                            }
                        }
                        break;
                    case 2:
                        Usuari.crearUsuari(usuarios); //Accede a el metodo de registro de la clase Usuari.
                        break;
                    case 3:
                        System.out.println("Saliendo..."); 
                        System.exit(opcio); //Salida total del programa
                    default:
                        System.out.println("No es una opción válida");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número.");
            }

        } while (true);
    } // Fin menuAcces()

    public static void menuLoginAdmin(Usuari usuari) { //Menu de login del admin
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
                        Director.CrearDirector(directores); // Accede al método de creación de directores en la clase Director
                        menuLoginAdmin(usuari);
                    case 2:
                        Usuari.añadirPersonal(usuari, peliculas, actores, directores, 2); // Accede al método de añadir directores a la lista personal en la clase Usuari
                        menuLoginAdmin(usuari);
                    case 3:
                        Pelicula.CrearPelicula(peliculas); // Accede al método de creación de películas en la clase Pelicula
                        menuLoginAdmin(usuari);
                    case 4:
                        Pelicula.eliminarPelicula(peliculas); // Accede al método de eliminar peliculas en la clase Pelicula
                        menuLoginAdmin(usuari);
                    case 5:
                        Actor.CrearActor(actores); // Accede al método de creación de actores en la clase Actor
                        menuLoginAdmin(usuari);
                    case 6:
                        Actor.eliminarActor(actores); // Accede al método de eliminar de actores en la clase Actor
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
        System.out.println("|              6. Ordenar Pel·licules");
        System.out.println("|");
        System.out.println("|              7. Eliminar Pel·licula Personal.");
        System.out.println("|");
        System.out.println("|              8. Añadir Actor.");
        System.out.println("|");
        System.out.println("|              9. Añadir Actor Personal.");
        System.out.println("|");
        System.out.println("|              10. Eliminar Actor Personal.");
        System.out.println("|");
        System.out.println("|              11. Eixir.");
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
                        Usuari.añadirPersonal(usuari, peliculas, actores, directores, 0); // Accede al método de añadir peliculas a la lista personal en la clase Usuari
                        menuLoginUsuari(usuari);
                    case 6:
                        menuOrdenacionPeliculas();
                    case 7:
                        Pelicula.eliminarPelicula(peliculas);
                        menuLoginUsuari(usuari);
                    case 8:
                        Actor.CrearActor(actores);
                        menuLoginUsuari(usuari);
                    case 9:
                        Usuari.añadirPersonal(usuari, peliculas, actores, directores, 1); // Accede al método de añadir actores a la lista personal en la clase Usuari
                        menuLoginUsuari(usuari);
                    case 10:
                        Actor.eliminarActor(actores);
                        menuLoginUsuari(usuari);
                    case 11:
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

    public static void menuOrdenacionPeliculas() {
        System.out.println("Tria una opció d'ordenació de les pel·lícules:");
        System.out.println("1. Per títol");
        System.out.println("2. Per duració");
        System.out.println("3. Per any + títol");
        System.out.print("Opció: ");

        String input = sc.nextLine();
        int opcion;
        try {
            opcion = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida.");
            return;
        }

        switch (opcion) {
            case 1:
                Collections.sort(peliculas);
                System.out.println("Películas ordenadas por título:");
                break;
            case 2:
                peliculas.sort(Comparators.porDuracion);
                System.out.println("Películas ordenadas por duración:");
                break;
            case 3:
                peliculas.sort(Comparators.porAnyoYTitulo);
                System.out.println("Películas ordenadas por año + título:");
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        for (Pelicula p : peliculas) {
            System.out.println(p.resum());
        }
    }
}