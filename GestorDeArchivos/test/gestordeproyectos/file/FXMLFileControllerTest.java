/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.file;

import gestordeproyectos.GestorDeProyectos;
import gestordeproyectos.funciones.FuncionesLoad;
import gestordeproyectos.resources.Constants;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author dani
 */
public class FXMLFileControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GestorDeProyectos.class.getResource(Constants.fileFXML));
            Parent root = (Parent) fxmlLoader.load();
            //Pasamos el stage al controlador
            FXMLFileController InstanciaController = fxmlLoader.getController();
            TilePane tp = InstanciaController.getTilePane();
            InstanciaController.filesMainCode(tp, null, null);
            //
            Stage st = new Stage();
            st.initModality(Modality.WINDOW_MODAL);
            st.setTitle(Constants.titleFile);
            st.setScene(new Scene(root));
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(FuncionesLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void rmb_deleteFolder_OK() throws InterruptedException {
        rmb_createFolder_OK();
        System.err.println("Directorio click derecho borrar");
        //Buscamos un BorderPane que sea una carpeta y damos click derecho - borrar
//        TilePane tp = lookup("#pane").query();
//        BorderPane bp = (BorderPane) tp.getChildren().get(3);
        rightClickOn("#CarpetaCreadaa");
        TimeUnit.SECONDS.sleep(1);
        clickOn("#borrar");
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void rmb_deleteFile_OK() throws InterruptedException {
        rmb_createFile_OK();
        System.err.println("Archivo click derecho borrar");
        //Buscamos un BorderPane que sea un archivo y damos click derecho - borrar
//        TilePane tp = lookup("#pane").query();
//        BorderPane bp = (BorderPane) tp.getChildren().get(0);
        rightClickOn("#ArchivoCreadootxt");
        TimeUnit.SECONDS.sleep(1);
        clickOn("#borrar");
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void rmb_createFolder_OK() throws InterruptedException {
        System.err.println("Click derecho en TilePane -> Crear Carpeta");
        //Damos click derecho en el TilePane (buscamos coordenadas de la esquina del TilePane, para asegurarnos de clickearlo)
        //Y no clickear a los BorderPane que contiene, ya que siempre hace el click derecho en el centro del TilePane
        TilePane tp = lookup("#pane").query();
        Bounds bounds = tp.getBoundsInLocal();
        Bounds screenBounds = tp.localToScreen(bounds);
        int x = (int) screenBounds.getMaxX();
        int y = (int) screenBounds.getMaxY();
        //Damos el click a 1 pixel de la esquina (realmente si está llena de archivos le va a seguir dando a un BorderPane)
        rightClickOn(x - 1, y - 1);
        TimeUnit.SECONDS.sleep(1);
        clickOn("#crearCarpeta");
        TimeUnit.SECONDS.sleep(1);
        //Introducimos nombre para la carpeta y la creamos
        clickOn("#textField").write(Constants.nombreCarpeta);
        TimeUnit.SECONDS.sleep(1);
        clickOn("#createButton");
        TimeUnit.SECONDS.sleep(1);
        //Técnicamente los BorderPane ocupan toda la anchura y altura del TilePane, por lo que no se puede dar click a este último
        //Una opción sería hacer que la ventana de archivos se agrande para mostrarlos a todos, por lo que dejo esto así planteado
    }

    @Test
    public void rmb_createFile_OK() throws InterruptedException {
        System.err.println("Click derecho en TilePane -> Crear Archivo");
        //Damos click derecho en el TilePane (buscamos coordenadas de la esquina del TilePane, para asegurarnos de clickearlo)
        //Y no clickear a los BorderPane que contiene, ya que siempre hace el click derecho en el centro del TilePane
        TilePane tp = lookup("#pane").query();
        Bounds bounds = tp.getBoundsInLocal();
        Bounds screenBounds = tp.localToScreen(bounds);
        int x = (int) screenBounds.getMaxX();
        int y = (int) screenBounds.getMaxY();
        //Damos el click a 1 pixel de la esquina (realmente si está llena de archivos le va a seguir dando a un BorderPane)
        rightClickOn(x - 1, y - 1);
        TimeUnit.SECONDS.sleep(1);
        clickOn("#crearArchivo");
        TimeUnit.SECONDS.sleep(1);
        //Introducimos nombre para el archivo y lo creamos
        clickOn("#textField").write(Constants.nombreArchivo2);
        TimeUnit.SECONDS.sleep(1);
        clickOn("#createButton");
        TimeUnit.SECONDS.sleep(1);
        //Técnicamente los BorderPane ocupan toda la anchura y altura del TilePane, por lo que no se puede dar click a este último
        //Una opción sería hacer que la ventana de archivos se agrande para mostrarlos a todos, por lo que dejo esto así planteado
    }

    @Test
    public void enter_Directories_GoBack_OK() throws InterruptedException {
        System.err.println("Doble click carpeta -> Flecha para volver");
        //Creamos carpeta en caso de que no esté creada
        rmb_createFolder_OK();
        TimeUnit.SECONDS.sleep(1);
        //Damos doble click en la carpeta
        doubleClickOn("#CarpetaCreadaa");
        TimeUnit.SECONDS.sleep(1);
        //Damos click a la flecha para volver
        clickOn("#backButton");
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void hideContextMenu_When_ClickOut_OK() throws InterruptedException {
        System.err.println("Mostramos ContextMenu - damos click fuera");
        //Damos click derecho en la esquina de abajo derecha
        TilePane tp = lookup("#pane").query();
        Bounds bounds = tp.getBoundsInLocal();
        Bounds screenBounds = tp.localToScreen(bounds);
        int x = (int) screenBounds.getMaxX();
        int y = (int) screenBounds.getMaxY();
        rightClickOn(x - 1, y - 1);
        //Damos click en el centro, a ver si se quita
        clickOn("#pane");
    }
}
