/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.main;

import gestordeproyectos.funciones.FuncionesFile;
import gestordeproyectos.funciones.FuncionesLoad;
import gestordeproyectos.resources.Constants;
import gestordeproyectos.resources.SingletonPath;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author dani
 */
public class FXMLDocumentController implements Initializable {

    public Stage mainStage;

    @FXML
    public TextArea content;

    @FXML
    private void toAbout(ActionEvent e) {
        //Abrimos el FXML de about
        FuncionesLoad.loadFXML(Constants.titleAbout, Constants.aboutFXML, Modality.WINDOW_MODAL);
    }

    @FXML
    private void toFile(ActionEvent e) {
        SingletonPath instancia = SingletonPath.getInstance();
        instancia.resetPath();
        //Abrimos la siguiente ventana, pasando el stage a la vez
        FuncionesLoad.loadFileWithStage(mainStage, content);
    }

    @FXML
    private void saveFile(ActionEvent e) {
        SingletonPath instancia = SingletonPath.getInstance();
        if (mainStage.getTitle().equals(Constants.titleMain)) {
            instancia.resetPath();
            //Cargamos la ventana de crear, con el TilePane null (no vamos a mostrar tras crear)
            FuncionesLoad.loadCreateWithParams(Constants.file, content, null, mainStage);
        } else {
            //Si se tiene un archivo seleccionado, se sobrescribirá directamente
            FuncionesFile.crearActualizarFichero(instancia.getSelectedFile(), content.getText());
        }
    }

    @FXML
    private void deleteFile(ActionEvent e) {
        SingletonPath instancia = SingletonPath.getInstance();
        //Si el título no es "NUEVO ARCHIVO" borramos el fichero actual
        if (!mainStage.getTitle().equals(Constants.titleMain)) {
            FuncionesFile.borrarFichero(instancia.getSelectedFile());
            //No creo que sea necesario, pero lo quitamos.
            instancia.setSelectedFile(null);
        }
        newFile(e);
    }

    @FXML
    private void newFile(ActionEvent e) {
        cleanContent();
        resetTitle();
    }

    //Limpiar TextArea
    public void cleanContent() {
        content.setText("");
    }

    //Poner "NUEVO ARCHIVO" como título
    public void resetTitle() {
        mainStage.setTitle(Constants.titleMain);
    }

    public void setParams(Stage st) {
        mainStage = st;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
