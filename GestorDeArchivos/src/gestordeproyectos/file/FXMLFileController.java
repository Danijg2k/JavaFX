/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.file;

import gestordeproyectos.funciones.FuncionesContextMenu;
import gestordeproyectos.funciones.FuncionesFile;
import gestordeproyectos.funciones.FuncionesGeneral;
import gestordeproyectos.funciones.FuncionesShow;
import gestordeproyectos.resources.Constants;
import gestordeproyectos.resources.SingletonPath;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 *
 *
 * @author dani
 */
public class FXMLFileController implements Initializable {

    private Stage stage;
    private TextArea textArea;

    @FXML
    private TilePane pane;

    public TilePane getTilePane() {
        return pane;
    }

    @FXML
    private void goBack(MouseEvent e) {
        SingletonPath instancia = SingletonPath.getInstance();
        //Si estamos en la raíz (primera altura) cerramos ventana
        if (instancia.getPath().equals(Constants.rootDirectory + "/")) {
            FuncionesGeneral.closeWindow(e);
        } //Si no, retrocedemos una altura, volvemos a mostrar archivos, y actualizamos ruta (título superior)
        else {
            try {
                instancia.removeFolder();
                FuncionesGeneral.refresh(pane, stage, textArea);
                FuncionesGeneral.changeTitle(e, instancia.getPath());
            } catch (IOException ex) {
                Logger.getLogger(FXMLFileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Tenemos que poner este código en una función, y no podemos aqui, ya que el initialize
        //sucede lo primero, y todavía no tendríamos el stage que traemos de archivos anteriores
        //para seguir utilizándolo, a no ser que lo hagamos tal y como hemos dicho. Ejecutamos
        //la función desde la creación de instancia, en la misma función que iniciamos este controller.
    }

    public void filesMainCode(TilePane tp, Stage st, TextArea textarea) {
        stage = st;
        textArea = textarea;
        //Creamos el directorio raíz en caso de que no esté creado
        FuncionesFile.crearDirectorio(Constants.rootDirectory);
        //Cada vez que abrimos FILES mostramos sus contenidos (a la altura de la raíz)
        try {
            FuncionesShow.recogerArchivos(tp, st, textarea);
        } catch (IOException ex) {
            Logger.getLogger(FXMLFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Con esto ya asignamos para siempre el ContextMenu al TilePane
        FuncionesContextMenu.ContextMenuCrear(tp, st, textarea);
    }
}
