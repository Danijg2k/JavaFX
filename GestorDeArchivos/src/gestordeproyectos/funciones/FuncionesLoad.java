/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.funciones;

import gestordeproyectos.GestorDeProyectos;
import gestordeproyectos.crear.FXMLCrearController;
import gestordeproyectos.file.FXMLFileController;
import gestordeproyectos.main.FXMLDocumentController;
import gestordeproyectos.resources.Constants;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author dani
 */
public class FuncionesLoad {

    //Tipo 1: Cargar simplemente FXML
    public static void loadFXML(String title, String ruta, Modality modality) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GestorDeProyectos.class.getResource(ruta));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(modality);
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FuncionesLoad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Tipo 2: Cargan un FXML y le pasan el stage con instancia de controlador
    public static void loadMainWithStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GestorDeProyectos.class.getResource(Constants.mainFXML));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle(Constants.titleMain);
            stage.setScene(new Scene(root));
            stage.show();
            //Pasamos el stage al controlador
            FXMLDocumentController InstanciaController = fxmlLoader.getController();
            InstanciaController.setParams(stage);
            //
        } catch (IOException ex) {
            Logger.getLogger(FuncionesLoad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void loadFileWithStage(Stage st, TextArea textarea) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GestorDeProyectos.class.getResource(Constants.fileFXML));
            Parent root = (Parent) fxmlLoader.load();
            //Pasamos el stage al controlador
            FXMLFileController InstanciaController = fxmlLoader.getController();
            TilePane tp = InstanciaController.getTilePane();
            InstanciaController.filesMainCode(tp, st, textarea);
            //
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle(Constants.titleFile);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FuncionesLoad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Tipo 3: Carga un FXML y le pasa opción (File/Directory), contenido del archivo (TextArea) y el TilePane
    public static void loadCreateWithParams(String opcion, TextArea textarea, TilePane tp, Stage st) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GestorDeProyectos.class.getResource(Constants.crearFXML));
            Parent root = (Parent) fxmlLoader.load();
            //Creamos una instancia del controlador
            FXMLCrearController InstanciaController = fxmlLoader.getController();
            //Pasamos parámetros al controller
            InstanciaController.setValues(opcion, textarea, tp, st);
            //Cargamos la ventana
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(Constants.titleCreate);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FuncionesLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
