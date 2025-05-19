package Com.Projecte.src.dev;

import java.time.LocalDate;

public class Pelicula {
    protected int id;
    private static int contadorId = 1;
    protected LocalDate Any;
    protected String Titol;
    protected int Durada;

    public Pelicula(LocalDate any, String titol, int durada) {
        this.id = contadorId++;
        Any = any;
        Titol = titol;
        Durada = durada;
    }

    public int getId() {
        return id;
    }

    public LocalDate getAny() {
        return Any;
    }

    public void setAny(LocalDate any) {
        Any = any;
    }

    public String getTitol() {
        return Titol;
    }

    public void setTitol(String titol) {
        Titol = titol;
    }

    public int getDurada() {
        return Durada;
    }

    public void setDurada(int durada) {
        Durada = durada;
    }

}
