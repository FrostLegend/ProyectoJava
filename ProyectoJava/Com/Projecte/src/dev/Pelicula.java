package Com.Projecte.src.dev;

import java.io.Serializable;
import java.util.ArrayList;

public class Pelicula implements Serializable {
    protected int id;
    private static int contadorId = 1;
    protected int any;
    protected String titol;
    protected int durada;
    protected ArrayList<Actor> actores;
    protected ArrayList<Director> directores;

    public Pelicula(int any, String titol, int durada, ArrayList<Actor> actores, ArrayList<Director> directores) {
        this.id = contadorId++;
        this.any = any;
        this.titol = titol;
        this.durada = durada;
        this.actores = new ArrayList<>();
        this.directores = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getAny() {
        return any;
    }

    public void setany(int any) {
        this.any = any;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public int getDurada() {
        return durada;
    }

    public void setDurada(int durada) {
        this.durada = durada;
    }

}
