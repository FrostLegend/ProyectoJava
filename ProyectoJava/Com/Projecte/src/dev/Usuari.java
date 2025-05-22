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
        File fichero = new File("../dades/Usuarios.txt");

        try {
            if (!fichero.exists()) {
                fichero.getParentFile().mkdirs();
                fichero.createNewFile();
            }

            try (BufferedWriter out = new BufferedWriter(new FileWriter(fichero, true))) {
                String linea = usuario.toStringPaFicheros2();
                if (!linea.equals(".")) {
                    out.write(linea);
                    out.newLine();
                    System.out.println("Texto añadido correctamente al archivo.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error al manipular el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error de tipo " + e.toString());
        }
    }

    public static ArrayList<Usuari> crearUsuari(ArrayList<Usuari> usuaris) {
        File fichero = new File("../src/dades/Usuarios.txt");
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

            System.out.print("Password del usuario: ");
            String password = sc.nextLine();
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
}
