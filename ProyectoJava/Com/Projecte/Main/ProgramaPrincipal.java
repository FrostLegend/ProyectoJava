package Com.Projecte.Main;

import Com.Projecte.Josep.Dadesjosep;
import Com.Projecte.Alejandro.DatosAlejandro;
import Com.Projecte.Alvaro.DatosAlvaro;
import Com.Projecte.Raul.DatosRaul;

public class ProgramaPrincipal {
    public static void main(String[] args) {
        inici();
    }

    public static void inici() {
        Dadesjosep.mostrarNom();
        DatosAlejandro.mostrarNombre();
        DatosAlvaro.mostrarNombre();
        DatosRaul.mostrarNombre();
    }
}