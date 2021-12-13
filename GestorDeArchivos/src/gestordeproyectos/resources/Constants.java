/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.resources;

/**
 *
 * @author dani
 */
public final class Constants {

    //Directorio raíz en el que guardamos archivos (si lo cambiamos sigue funcionando correctamente)
    //Tengo que reiniciar NetBeans para que haga efecto (generalmente), pero todo trabaja con constantes
    public static final String rootDirectory = "FILES";

    //---Rutas y títulos ventanas ---
    //Main
    public static final String mainFXML = "main/FXMLDocument.fxml";
    public static final String titleMain = "NUEVO ARCHIVO";
    //About
    public static final String aboutFXML = "about/FXMLAbout.fxml";
    public static final String titleAbout = "About";
    //File
    public static final String fileFXML = "file/FXMLFile.fxml";
    public static final String titleFile = "FILES";
    //Crear
    public static final String crearFXML = "crear/FXMLCrear.fxml";
    public static final String titleCreate = "Create";

    //Texto ContextMenu
    public static final String createFile = "Crear Archivo";
    public static final String createDirectory = "Crear Carpeta";
    public static final String delete = "Delete";

    //Opciones
    public static final String file = "File";
    public static final String directory = "Directory";

    //---Pruebas test integración---
    //Texto TextArea DocumentController
    public static final String textAreaString = "Vamos a crear 'ArchivoTest' con este contenido";
    public static final String textAreaString2 = ". Añadimos un poco de felicidad.";
    public static final String testBorrar = "Vamos a ver si 'Borrar' borra esto";
    public static final String testNewFile = "Vamos a ver si 'New File' borra esto";
    //Texto TextField CrearController
    public static final String nombreArchivo = "ArchivoTest";
    public static final String nombreArchivo2 = "ArchivoCreadoo";
    public static final String nombreCarpeta = "CarpetaCreadaa";
}
