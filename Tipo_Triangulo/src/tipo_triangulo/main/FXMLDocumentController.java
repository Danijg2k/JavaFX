/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipo_triangulo.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author dani
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    private TextField text3;

    @FXML
    private void enviarUno(KeyEvent e) {
        checkearTecla(e, "Uno");
    }

    @FXML
    private void enviarDos(KeyEvent e) {
        checkearTecla(e, "Dos");
    }

    @FXML
    private void enviarTres(KeyEvent e) {
        checkearTecla(e, "Tres");
    }

    @FXML
    private void checkearTecla(KeyEvent e, String num) {
        String uno = text1.getText(), dos = text2.getText(), tres = text3.getText();
        String guardar = e.getCharacter();
        if (!guardar.matches("[0-9]")) {
            e.consume();
        } else {
            switch (num) {
                case "Uno":
                    uno += e.getCharacter();
                    break;
                case "Dos":
                    dos += e.getCharacter();

                    break;
                case "Tres":
                    tres += e.getCharacter();
                    break;
            }
            if (uno.isEmpty() || dos.isEmpty() || tres.isEmpty()) {
                label.setText("Datos insuficientes");
            } else {
                int a, b, c;
                a = Integer.parseInt(uno);
                b = Integer.parseInt(dos);
                c = Integer.parseInt(tres);
                if ((a + b) > c && (b + c) > a && (a + c) > b) {
                    if (a == b && b == c) {
                        label.setText("Triángulo equilátero");
                    } else if (a != b && b != c && a != c) {
                        label.setText("Triángulo escaleno");
                    } else {
                        label.setText("Triángulo isósceles");
                    }
                } else {
                    label.setText("No existe triángulo con esas dimensiones");
                }
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
