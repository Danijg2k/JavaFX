/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posibleexamenv2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import posibleexamenv2.funciones.Funciones;
import posibleexamenv2.funciones.FunctionGeneral;

/**
 *
 * @author dani
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label time;

    @FXML
    private Label lives;

    @FXML
    private TilePane tilePane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Funciones.shuffle();
        Funciones.labelLives = lives;
        Funciones.showLives();
        Funciones.showPane(tilePane);
        FunctionGeneral.showTimeLabel(time);
    }

}
