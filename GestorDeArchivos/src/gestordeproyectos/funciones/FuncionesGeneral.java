/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.funciones;

import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 *
 * @author dani
 */
public class FuncionesGeneral {

    //Cerrar una ventana con un click de ratón
    public static void closeWindow(MouseEvent e) {
        getActualStage(e).close();
    }

    //Cambiar el título de la ventana ACTUAL (en la que hagamos click)
    public static void changeTitle(MouseEvent e, String title) {
        getActualStage(e).setTitle(title);
    }

    //Obtener stage actual
    public static Stage getActualStage(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        return stage;
    }

    //Permitir que solo se puedan introducir letras por teclado
    public static void onlyLetters(KeyEvent e) {
        if (e.getCharacter().matches("[0-9]")) {
            e.consume();
        }
    }

    //Volver a mostrar archivos (cuando borramos o creamos uno nuevo)
    public static void refresh(TilePane tp, Stage st, TextArea textarea) throws IOException {
        tp.getChildren().clear();
        FuncionesShow.recogerArchivos(tp, st, textarea);
    }
}
