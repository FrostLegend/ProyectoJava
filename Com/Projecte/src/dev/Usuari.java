package Com.Projecte.src.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Usuari extends Persona {
    static Scanner sc = new Scanner(System.in);

    public Usuari(String nombre, String apellido, String email, String password, String poblacio, Boolean rol,
            int fechaNacimiento, int id) {
        super(nombre, apellido, email, password, poblacio, rol, fechaNacimiento, id);
    }

    public static void CrearficheroUsuario(Usuari usuario) {
        File fichero = new File("Com/Projecte/src/dades/Usuarios.txt");

        try {
            if (!fichero.exists()) {
                fichero.getParentFile().mkdirs();
                fichero.createNewFile();
            }

            try (BufferedWriter out = new BufferedWriter(new FileWriter(fichero, true))) {
                String linea = usuario.toStringPaFicherosUsuarios();
                out.write(linea);
                out.newLine();
                System.out.println("Usuario añadido correctamente al archivo.");
            }

        } catch (IOException e) {
            System.out.println("Error al manipular el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error de tipo " + e.toString());
        }
        CrearficheroUsuarioPersonal(usuario);
    }

    public static void CrearficheroUsuarioPersonal(Usuari usuario) {
        String[] nombre = { "peliculasfavoritas", "actoresfavoritos", "directoresfavoritos" };
        for (int i = 0; i < nombre.length; i++) {
            File ficheropersonal1 = new File(
                    "Com/Projecte/src/dades_" + usuario.getEmail() + "/" + nombre[i] + ".txt");
            try {
                if (!ficheropersonal1.exists()) {
                    ficheropersonal1.getParentFile().mkdirs();
                    ficheropersonal1.createNewFile();
                }

                try (BufferedWriter out = new BufferedWriter(new FileWriter(ficheropersonal1, true))) {
                    String linea = "";
                    out.write(linea);
                }

            } catch (IOException e) {
                System.out.println("Error al manipular el archivo: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error de tipo " + e.toString());
            }
        }
        System.out.println("carpetas de" + usuario.getNombre() + " añadidas correctamente al archivo.");
    }

    public static ArrayList<Usuari> crearUsuari(ArrayList<Usuari> usuaris) {
        File fichero = new File("Com/Projecte/src/dades/Usuarios.txt");
        int ultimaId = -1;

        if (fichero.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (!linea.isEmpty()) {
                        String[] partes = linea.split(";");
                        if (partes.length > 0) {
                            try {
                                int idLinea = Integer.parseInt(partes[0]);
                                if (idLinea > ultimaId) {
                                    ultimaId = idLinea;
                                }
                            } catch (NumberFormatException ignored) {
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error leyendo el archivo para obtener la última ID: " + e.getMessage());
            }
        }

        System.out.println("Introduce los datos de los usuarios (escribe 'fin' en el nombre para terminar):");
        while (true) {
            System.out.print("Nombre del usuario: ");
            String nombre = sc.nextLine();
            if (nombre.equalsIgnoreCase("fin"))
                break;

            System.out.print("Apellido del usuario: ");
            String apellido = sc.nextLine();
            if (apellido.equalsIgnoreCase("fin"))
                break;

            System.out.print("Email del usuario: ");
            String email = sc.nextLine();
            if (email.equalsIgnoreCase("fin"))
                break;

            String password = "";
            String password2 = "";
            do {
                System.out.print("Password del usuario: ");
                password = sc.nextLine();

                System.out.print("Otra vez password del usuario: ");
                password2 = sc.nextLine();
            } while (!password.equals(password2));

            if (password.equalsIgnoreCase("fin"))
                break;

            System.out.print("Población del usuario: ");
            String poblacio = sc.nextLine();
            if (poblacio.equalsIgnoreCase("fin"))
                break;

            Boolean rol = false;

            int fechaNacimiento = 0;
            while (true) {
                try {
                    System.out.print("Año de nacimiento del usuario: ");
                    fechaNacimiento = Integer.parseInt(sc.nextLine());
                    if (fechaNacimiento > 1900 && fechaNacimiento < 2025)
                        break;
                    System.out.println("Año de nacimiento no válido.");
                } catch (NumberFormatException e) {
                    System.out.println("Introduce un número válido.");
                }
            }

            int id = ++ultimaId;
            Usuari usuario = new Usuari(nombre, apellido, email, password, poblacio, rol, fechaNacimiento, id);
            CrearficheroUsuario(usuario);
            usuaris.add(usuario);
        }

        return usuaris;
    }

    public static Usuari login() {
        File fichero = new File("Com/Projecte/src/dades/Usuarios.txt");

        if (!fichero.exists()) {
            System.out.println("No existe el archivo de usuarios.");
            return null;
        }

        System.out.print("Introduce el email: ");
        String emailInput = sc.nextLine();

        System.out.print("Introduce la contraseña: ");
        String passwordInput = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes.length >= 8) {
                        int id = Integer.parseInt(partes[0]);
                        String nombre = partes[1];
                        String apellido = partes[2];
                        String email = partes[3];
                        String password = partes[4];
                        String poblacio = partes[5];
                        boolean rol = Boolean.parseBoolean(partes[6]);
                        int fechaNacimiento = Integer.parseInt(partes[7]);

                        if (email.equals(emailInput) && password.equals(passwordInput)) {
                            System.out.println("Inicio de sesión exitoso. Bienvenido, " + nombre + "!");
                            return new Usuari(nombre, apellido, email, password, poblacio, rol, fechaNacimiento, id);
                        }
                    }
                }
            }
            System.out.println("Email o contraseña incorrectos.");
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.toString());
        }

        return null;
    }

    public static void añadirPersonal(Usuari usuario, ArrayList<Pelicula> peliculas, ArrayList<Actor> actores, ArrayList<Director> directores, int tipo){
        if (tipo == 0) {
            if (peliculas.isEmpty()) {
            System.out.println("No hay directores disponibles.");
            return;
            } else {
                Pelicula.mostrarPeliculas(peliculas);
            }
        }

        if (tipo == 1) {
            if (actores.isEmpty()) {
            System.out.println("No hay directores disponibles.");
            return;
            } else {
                Actor.mostrarActores(actores);
            }
        }

        if (tipo == 2) {
            if (directores.isEmpty()) {
            System.out.println("No hay directores disponibles.");
            return;
            } else {
                Director.mostrarDirectores(directores);
            }
        }

        String[] general = { "Peliculas", "Actores", "Directores" };
        String[] personal = { "peliculasfavoritas", "actoresfavoritos", "directoresfavoritos" };

        System.out.print("Introduce el ID a seleccionar (o -1 para cancelar): ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return;
        }
        if (id == -1) {
            System.out.println("Operación cancelada.");
            return;
        }

        File ficheroGeneral = new File("Com/Projecte/src/dades/" + general[tipo] + ".txt");
        File ficheroPersonal = new File("Com/Projecte/src/dades_" + usuario.getEmail() + "/" + personal[tipo] + ".txt");

        String linea = buscarLineaPorId(ficheroGeneral, id);
        String seRepite = buscarLineaPorId(ficheroPersonal, id);

        if (seRepite != null) {
            System.out.println(general[tipo] + " ya añadido.");
            return;
        }

        if (linea != null) {
            try (BufferedWriter out = new BufferedWriter(new FileWriter(ficheroPersonal, true))) {
                out.write(linea);
                out.newLine();
                System.out.println(general[tipo] + " añadido correctamente al archivo.");
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }
        } else {
            System.out.println(general[tipo] + " No encontrado.");
        }
    }

    public static String buscarLineaPorId(File fichero, int id) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes.length > 0 && partes[0].equals(String.valueOf(id))) {
                        return linea;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.toString());
        }
        return null;
    }

}
