/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posibleexamenv2.funciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author dani
 */
public class FunctionGeneral {

    //Comprobar si un número está en ArrayList
    public static boolean numInArrayList(List<Integer> array, int number) {
        for (Integer element : array) {
            if (element == number) {
                return true;
            }
        }
        return false;
    }

    //Comprobar si un número está en un array de números
    public static boolean numInArray(int[] array, int number) {
        for (int element : array) {
            if (element == number) {
                return true;
            }
        }
        return false;
    }

    //Obtener el momento actual
    public static String time() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    //Mostrar cada segundo el momento actual
    public static void showTimeLabel(Label l) {
        Timeline timeline;
        timeline = new Timeline(new KeyFrame(javafx.util.Duration.ONE, (event) -> {
            l.setText(time());
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //Cerrar ventana
    public static void closeWindow(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
