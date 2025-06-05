package Com.Projecte.src.dev;

import java.util.Comparator;

public class Comparators {
    // Ordenar por duración ascendente
    public static Comparator<Pelicula> porDuracion = new Comparator<Pelicula>() {
        @Override
        public int compare(Pelicula p1, Pelicula p2) {
            return Integer.compare(p1.getDurada(), p2.getDurada());
        }
    };

    // Ordenar por año ascendente y luego por título
    public static Comparator<Pelicula> porAnyoYTitulo = new Comparator<Pelicula>() {
        @Override
        public int compare(Pelicula p1, Pelicula p2) {
            int resultado = Integer.compare(p1.getAny(), p2.getAny());
            if (resultado == 0) {
                return p1.getTitol().compareToIgnoreCase(p2.getTitol());
            }
            return resultado;
        }
    };
}