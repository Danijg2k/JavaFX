/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.resources;

import java.util.ArrayList;

/**
 *
 * @author dani
 */
public class SingletonPath {

    private String archivoSeleccionado;
    private String rutaActualString;
    private final ArrayList<String> rutaActual;
    private static SingletonPath instancia;

    public static SingletonPath getInstance() {
        if (instancia == null) {
            instancia = new SingletonPath();
        }
        return instancia;
    }

    //Cada vez que llamamos a este constructor creamos el ArrayList solo con el elemento "FILES"
    private SingletonPath() {
        rutaActual = new ArrayList<>();
        rutaActual.add(Constants.rootDirectory);
    }

    //Resetear la ruta (dejar solo un elemento: "FILES")
    public void resetPath() {
        rutaActual.clear();
        rutaActual.add(Constants.rootDirectory);
    }

    //Añadir una carpeta a la ruta
    public void addFolder(String s) {
        rutaActual.add(s);
    }

    //Hacer que la ruta retroceda en una
    public void removeFolder() {
        rutaActual.remove(rutaActual.size() - 1);
    }

    //Devuelve la ruta actual en cadena
    public String getPath() {
        rutaActualString = "";
        for (int i = 0; i < rutaActual.size(); i++) {
            rutaActualString += rutaActual.get(i) + "/";
        }
        return rutaActualString;
    }

    //Guardamos la ruta del archivo en el que damos doble click
    public void setSelectedFile(String s) {
        archivoSeleccionado = getPath() + s;
    }

    //Devuelve la ruta del archivo
    public String getSelectedFile() {
        return archivoSeleccionado;
    }

}
