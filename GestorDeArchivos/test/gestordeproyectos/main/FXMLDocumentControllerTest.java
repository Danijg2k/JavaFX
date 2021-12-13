/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.main;

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
public class FXMLDocumentControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GestorDeProyectos.class.getResource(Constants.mainFXML));
            Parent root = (Parent) fxmlLoader.load();
            Stage st = new Stage();
            st.initModality(Modality.WINDOW_MODAL);
            st.setTitle(Constants.titleMain);
            st.setScene(new Scene(root));
            st.show();
            //Pasamos el stage al controlador
            FXMLDocumentController InstanciaController = fxmlLoader.getController();
            InstanciaController.setParams(st);
            //
        } catch (IOException ex) {
            Logger.getLogger(FuncionesLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void clickOn_About_OpenAboutFXML_OK() throws InterruptedException {
        System.err.println("Abrir about");
        //Clickeamos en info y esperamos en about para clickearlo
        clickOn("#info");
        moveWaitClick("about");
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void clickOn_Open_OpenFileFXML_OK() throws InterruptedException {
        System.err.println("Abrir file");
        //Clickeamos en file y esperamos en open para clickearlo
        clickOn("#file");
        moveWaitClick("open");
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void writeOn_TextArea_clickOn_NewFile_Clean_TextArea_OK() throws InterruptedException {
        System.err.println("Escribimos en textArea - borrar con NewFiles");
        //Escribimos en textArea
        clickOn("#content").write(Constants.testNewFile);
        //TimeUnit.SECONDS.sleep(1);
        //Clickeamos en file y esperamos en newfile para clickearlo
        clickOn("#file");
        moveWaitClick("newfile");
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void writeOn_TextArea_clickOn_Delete_Clean_TextArea_OK() throws InterruptedException {
        System.err.println("Escribimos en textArea - borrar con Delete");
        //Escribimos en textArea
        clickOn("#content").write(Constants.testBorrar);
        //TimeUnit.SECONDS.sleep(1);
        //Clickeamos en file y esperamos en delete para clickearlo
        clickOn("#file");
        moveWaitClick("delete");
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void saveFile_with_TextArea_Content_And_Check_OK() throws InterruptedException {
        System.err.println("Checkear file - crear archivo con contenido textArea - checkear file");
        //Checkeamos file para ver archivos creados
        clickOn_Open_OpenFileFXML_OK();
        TimeUnit.SECONDS.sleep(2);
        clickOn("#backButton");
        //Volvemos y escribimos en textArea
        clickOn("#content").write(Constants.textAreaString);
        //TimeUnit.SECONDS.sleep(1);
        //Clickeamos en file y esperamos en save para clickearlo
        clickOn("#file");
        moveWaitClick("save");
        //Escribimos nombre para el archivo
        clickOn("#textField").write(Constants.nombreArchivo);
        TimeUnit.SECONDS.sleep(1);
        //Esperamos en create para clickearlo
        moveWaitClick("createButton");
        TimeUnit.SECONDS.sleep(1);
        //Entramos y vemos que se ha creado el archivo
        clickOn_Open_OpenFileFXML_OK();
    }

    @Test
    public void selectFile_Delete_CheckDeletion_OK() throws InterruptedException {
        System.err.println("Crear archivo - abrir file - doble click archivo - borrar - abrir file - comprobar borrado");
        //Creamos archivo por si no está creado
        saveFile_with_TextArea_Content_And_Check_OK();
        //Clickeamos en el archivo
        TimeUnit.SECONDS.sleep(1);
        doubleClickOn("#" + Constants.nombreArchivo + "txt");
        TimeUnit.SECONDS.sleep(1);
        //Una vez lo tenemos seleccionado le damos a borrar en DocumentFXML
        clickOn("#file");
        moveWaitClick("delete");
        //Mostramos file de nuevo, para ver si se ha borrado
        clickOn_Open_OpenFileFXML_OK();
    }

    @Test
    public void selectFile_RewriteContent_CheckContentChange_OK() throws InterruptedException {
        System.err.println("Abrir file - doble click archivo - cambiar TextArea - abrir file - comprobar cambios");
        //Creamos archivo por si no está creado
        saveFile_with_TextArea_Content_And_Check_OK();
        //Volvemos a main, limpiamos textArea
        clickOn("#backButton");
        clickOn("#file");
        clickOn("#newfile");
        TimeUnit.SECONDS.sleep(1);
        //Vamos al archivo y le damos doble click
        clickOn("#file");
        clickOn("#open");
        doubleClickOn("#" + Constants.nombreArchivo + "txt");
        TimeUnit.SECONDS.sleep(1);
        //Cambiamos TextArea, guardamos y limpiamos TextArea
        clickOn("#content").write(Constants.textAreaString2);
        clickOn("#file");
        clickOn("#save");
        TimeUnit.SECONDS.sleep(1);
        clickOn("#file");
        clickOn("#newfile");
        //Vamos y damos doble click en el archivo
        clickOn("#file");
        clickOn("#open");
        doubleClickOn("#" + Constants.nombreArchivo + "txt");
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void RMBdeleteFile_Check_Main_Title_Changes_OK() throws InterruptedException {
        System.err.println("Click derecho borrar archivo - ver titulo Main");
        //Creamos archivo por si no está creado
        saveFile_with_TextArea_Content_And_Check_OK();
        //Borramos el archivo
        rightClickOn("#ArchivoTesttxt");
        clickOn("#borrar");
        TimeUnit.SECONDS.sleep(1);
        clickOn("#backButton");
        TimeUnit.SECONDS.sleep(1);
    }

    //Funciones auxiliares para los test de integración
    public void moveWaitClick(String elemento) throws InterruptedException {
        moveTo("#" + elemento);
        TimeUnit.SECONDS.sleep(1);
        clickOn("#" + elemento);
    }

}
