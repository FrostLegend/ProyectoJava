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
    ArrayList<Director> directores = new ArrayList<>();
    ArrayList<Actor> actores = new ArrayList<>();
    ArrayList<Pelicula> peliculas = new ArrayList<>();
    ArrayList<Usuari> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        inici();
        /* esta funcion es lo que meteras en el switch para poder logear usuarios */
        /*
         * Usuari usuarioLogueado = Usuari.login();
         * if (usuarioLogueado != null) {
         * // Acceder al sistema o menú
         * }
         */
    }

    public static void inici() {
        Dadesjosep.mostrarNom();
        DatosAlejandro.mostrarNombre();
        DatosAlvaro.mostrarNombre();
        DatosRaul.mostrarNombre();
    }

}
