/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posibleexamendados;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import posibleexamendados.funciones.Funciones;

/**
 *
 * @author dani
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label username;

    @FXML
    private Label osname;

    @FXML
    private Label date;

    @FXML
    private Pane paneDado;

    @FXML
    private ImageView dado;

    @FXML
    private void closeWindow(MouseEvent e) {
        Funciones.closeWindow(e);
        Funciones.loadFXML("Juego", "juego/FXMLJuego.fxml", Modality.WINDOW_MODAL);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String user = System.getProperty("user.name");
        username.setText(user);
        osname.setText(System.getProperty("os.name"));
        Funciones.date(date);
    }

}
