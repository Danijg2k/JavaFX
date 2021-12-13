/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posibleexamendados.juego;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import posibleexamendados.PosibleExamenDados;
import posibleexamendados.funciones.Funciones;

public class FXMLJuegoController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private Label fichas1;

    @FXML
    private Label fichas2;

    @FXML
    private TextField field1;

    @FXML
    private TextField field2;

    @FXML
    private Pane top;

    @FXML
    private Pane mid;

    @FXML
    private Pane bot;

    @FXML
    private ImageView central;

    private static int fic1 = 500;
    private static int fic2 = 500;

    @FXML
    private void numbers(KeyEvent e) {
        Funciones.onlyNumbers(e);
    }

    @FXML
    private void apostar() {
        field1.setDisable(true);
        field2.setDisable(true);
        bot.setStyle("-fx-background-color: green");
        central.setOnMouseClicked((event) -> {

            central.setImage(new Image(PosibleExamenDados.class.getResource("images/" + (int) (Math.random() * 6 + 1) + ".jpg").toString()));
//            central.setDisable(true);
        });

    }

    @FXML
    private void fichasuno(KeyEvent e) {
        if (!field1.getText().equals("")) {
            //Si no llega a 20 fichas ALL IN
            if (fic1 < 20) {
                field1.setText(String.valueOf(fic1));
            }//Si tiene mínimo 20 fichas
            else {
                //Mínimo 20
                if ((Integer.parseInt(field1.getText())) < 20) {
                    field1.setText(String.valueOf(20));
                }//Si intenta jugarse más de las que tiene naada
                if ((Integer.parseInt(field1.getText())) > fic1) {
                    field1.setText(String.valueOf(fic1));
                }
            }
        }
    }

    @FXML
    private void fichasdos(KeyEvent e) {
        if (!field2.getText().equals("")) {
            //Si no llega a 20 fichas ALL IN
            if (fic2 < 20) {
                field2.setText(String.valueOf(fic2));
            }//Si tiene mínimo 20 fichas
            else {
                //Mínimo 20
                if ((Integer.parseInt(field2.getText())) < 20) {
                    field2.setText(String.valueOf(20));
                }//Si intenta jugarse más de las que tiene naada
                if ((Integer.parseInt(field2.getText())) > fic2) {
                    field2.setText(String.valueOf(fic2));
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Funciones.textLabel(fichas1, String.valueOf(fic1));
        Funciones.textLabel(fichas2, String.valueOf(fic2));

    }

}
