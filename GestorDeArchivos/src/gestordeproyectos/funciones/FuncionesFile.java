/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.funciones;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author dani
 */
public class FuncionesFile {

    public static void crearDirectorio(String ruta) {
        if (ruta != null && !ruta.equals("")) {
            Path path = Paths.get(ruta);
            try {
                Files.createDirectory(path);
                System.out.printf("Creando directorio '%s'.\n", ruta);
            } catch (IOException e) {
                System.out.printf("Directorio '%s' ya creado.\n", ruta);
            }
        }
    }

    public static void borrarDirectorio(String ruta) throws IOException {
        if (ruta != null && !ruta.equals("")) {
            Path path = Paths.get(ruta);
            try (Stream<Path> walk = Files.walk(path)) {
                walk
                        .sorted(Comparator.reverseOrder())
                        .forEach(element -> delete(element));
            }
        }
    }

    //Función auxiliar de borrarDirectorio
    private static void delete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.err.printf("No se ha podido borrar esta ruta: %s%n", path);
        }
    }

    //Avisamos de si estamos creando o sobrescribiendo el archivo.
    //Tenemos que poner ambos print donde están. Con Files.createFile, si el archivo
    //ya estaba creado, entra en el catch, pero en este caso sigue entrando en el try, sobrescribiendo.
    public static void crearActualizarFichero(String ruta, String contenido) {
        if (ruta != null && !ruta.equals("")) {
            Path path = Paths.get(ruta);
            try {
                if (Files.exists(path)) {
                    System.out.printf("Fichero '%s' actualizado.\n", ruta);
                } else {
                    System.out.printf("Fichero '%s' creado.\n", ruta);
                }
                Files.write(path, contenido.getBytes(StandardCharsets.UTF_8));
            } catch (IOException ex) {
                Logger.getLogger(FuncionesFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void crearFichero(String ruta) {
        if (ruta != null && !ruta.equals("")) {
            Path path = Paths.get(ruta);
            try {
                Files.createFile(path);
                System.out.printf("Fichero '%s' creado.\n", ruta);
            } catch (IOException ex) {
                System.out.printf("El fichero '%s' ya existe.\n", ruta);
            }
        }
    }

    public static void borrarFichero(String ruta) {
        if (ruta != null && !ruta.equals("")) {
            Path path = Paths.get(ruta);
            try {
                Files.delete(path);
                System.out.printf("Fichero '%s' borrado.\n", ruta);
            } catch (IOException ex) {
                System.out.printf("El fichero '%s' no ha podido ser borrado.\n", ruta);
            }
        }
    }

}
