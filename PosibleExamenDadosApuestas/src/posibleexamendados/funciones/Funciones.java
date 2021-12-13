/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posibleexamendados.funciones;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import posibleexamendados.PosibleExamenDados;

/**
 *
 * @author dani
 */
public class Funciones {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    //Obtener el momento actual
    public static String time() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    //Mostrar cada segundo el momento actual
//    public static void showTimeLabel(Label l) {
//        Timeline timeline;
//        timeline = new Timeline(new KeyFrame(javafx.util.Duration.ONE, (event) -> {
//            l.setText(time());
//        }));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
//    }
    public static void date(Label l) {
        Date currentDate = new Date();
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        // manipulate date
        c.add(Calendar.DATE, 24);
        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        l.setText(dateFormat.format(currentDatePlusOne));
    }

    //Cerrar ventana
    public static void closeWindow(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    //Cargar FXML
    public static void loadFXML(String title, String ruta, Modality modality) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PosibleExamenDados.class.getResource(ruta));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(modality);
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Hacer que solo se puedan números
    public static void onlyNumbers(KeyEvent e) {
        if (!e.getCharacter().matches("[0-9]")) {
            e.consume();
        }
    }

    //Poner texto en label
    public static void textLabel(Label l, String s) {
        l.setText(s);
    }

}
