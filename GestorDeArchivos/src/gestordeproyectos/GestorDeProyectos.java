/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos;

import gestordeproyectos.funciones.FuncionesLoad;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author dani
 */
public class GestorDeProyectos extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Cargamos FXMLDocument
        FuncionesLoad.loadMainWithStage();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
