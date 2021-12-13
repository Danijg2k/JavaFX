/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.crear;

import gestordeproyectos.GestorDeProyectos;
import gestordeproyectos.funciones.FuncionesLoad;
import gestordeproyectos.resources.Constants;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author dani
 */
public class FXMLCrearControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GestorDeProyectos.class.getResource(Constants.crearFXML));
            Parent root = (Parent) fxmlLoader.load();
            //Pasamos el stage al controlador
            //FXMLCrearController InstanciaController = fxmlLoader.getController();
            //
            Stage st = new Stage();
            st.initModality(Modality.WINDOW_MODAL);
            st.setTitle(Constants.titleCreate);
            st.setScene(new Scene(root));
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(FuncionesLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void clickCancel_CloseWindow_OK() throws InterruptedException {
        System.err.println("Click en cancel -> se cierra ventana");
        //Esperamos y le damos a cancel
        moveTo("#button1");
        TimeUnit.SECONDS.sleep(1);
        clickOn("#button1");
    }

    @Test
    public void checkEnable_Disable_TextField_OK() throws InterruptedException {
        System.err.println("Escribir - borrar TextField (Enable/Disable)");
        //Esperamos y le damos a cancel
        clickOn("#textField").write("a");
        TimeUnit.SECONDS.sleep(1);
        eraseText(1);
        TimeUnit.SECONDS.sleep(1);
        write("b");
        TimeUnit.SECONDS.sleep(1);
        eraseText(1);
        TimeUnit.SECONDS.sleep(1);
        write("c");
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void checkOnlyLetters_OK() throws InterruptedException {
        System.err.println("Comprobar solo letras TextField");
        //Esperamos e intentamos escribir números
        clickOn("#textField").write("1");
        TimeUnit.SECONDS.sleep(1);
        write("a");
        TimeUnit.SECONDS.sleep(1);
        write("2");
        TimeUnit.SECONDS.sleep(1);
        write("b");
        TimeUnit.SECONDS.sleep(1);
    }

}
