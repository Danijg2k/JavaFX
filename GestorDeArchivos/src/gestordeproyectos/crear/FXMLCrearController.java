/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.crear;

import gestordeproyectos.funciones.FuncionesFile;
import gestordeproyectos.funciones.FuncionesGeneral;
import gestordeproyectos.resources.Constants;
import gestordeproyectos.resources.SingletonPath;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dani
 */
public class FXMLCrearController implements Initializable {

    public TilePane tilePane; //será null cuando lleguemos aquí desde el botón guardar
    public TextArea textArea;
    public String textAreaContent;
    public Stage mainStage;

    @FXML
    private Label centeredLabel;

    @FXML
    private TextField textField;

    @FXML
    private Button createButton;

    //Crear Archivo/Directorio al darle al botón 'Crear'
    public void crear(MouseEvent m) {
        SingletonPath instancia = SingletonPath.getInstance();
        if (textArea != null) {
            textAreaContent = textArea.getText();
        }
        try {
            if (centeredLabel.getText().contains(Constants.file)) {
                if (tilePane != null) {
                    FuncionesFile.crearFichero(instancia.getPath() + textField.getText() + ".txt");
                } else {
                    FuncionesFile.crearActualizarFichero(instancia.getPath() + textField.getText() + ".txt", textAreaContent);
                }
            } else if (centeredLabel.getText().contains(Constants.directory)) {
                FuncionesFile.crearDirectorio(instancia.getPath() + textField.getText());
            }
            //Cerramos la ventana de crear
            FuncionesGeneral.closeWindow(m);
            //Limpiamos el TilePane que muestra todos los archivos y volvemos a llamar a la función de mostrar.
            //Si hemos llegado hasta aqui desde 'guardar' el tilePane será null. No lo será si hemos llegado con ContextMenu.
            if (tilePane != null) {
                FuncionesGeneral.refresh(tilePane, mainStage, textArea);
            } else {
                instancia.setSelectedFile(textField.getText() + ".txt");
                mainStage.setTitle(textField.getText() + ".txt");
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLCrearController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Solo permitir que se introduzcan letras en el TextField
    @FXML
    private void checkIntroduced(KeyEvent e) {
        FuncionesGeneral.onlyLetters(e);
    }

    //Habilitar y deshabilitar el botón de crear dependiendo si hay texto o no
    @FXML
    private void disableEnableCreateButton(KeyEvent e) {
        if (textField.getText().isEmpty()) {
            createButton.setDisable(true);
        } else {
            createButton.setDisable(false);
        }
    }

    //Cerrar ventana con Cancel
    @FXML
    private void cancel(MouseEvent e) {
        FuncionesGeneral.closeWindow(e);
    }

    //Cambiamos el valor del Label para mostrarlo. Guardamos el TilePane para usarlo después (limpiar y actualizar).
    public void setValues(String s, TextArea textarea, TilePane tp, Stage st) {
        centeredLabel.setText("Create " + s);
        tilePane = tp;
        textArea = textarea;
        mainStage = st;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        No podemos actualizar aquí el texto del label a 'CREATE Folder/Directory' porque
//        existe delay. Tenemos que hacerlo en una función aparte, que se ejecute antes del show
//        de este stage (initialize). Esta función es 'setValues(String, TilePane)'.
    }

}
