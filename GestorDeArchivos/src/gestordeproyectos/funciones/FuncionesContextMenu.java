/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.funciones;

import gestordeproyectos.resources.Constants;
import gestordeproyectos.resources.SingletonPath;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 *
 * @author dani
 */
public class FuncionesContextMenu {

    public static void ContextMenuCrear(TilePane tp, Stage st, TextArea textarea) {
        //Creamos ContextMenu
        ContextMenu contextMenu = new ContextMenu();
        MenuItem crearArchivo = new MenuItem(Constants.createFile);
        crearArchivo.setId("crearArchivo");
        MenuItem crearCarpeta = new MenuItem(Constants.createDirectory);
        crearCarpeta.setId("crearCarpeta");
        contextMenu.getItems().addAll(crearArchivo, crearCarpeta);
        //Mostrar ContextMenu con click derecho
        tp.setOnContextMenuRequested((ContextMenuEvent e) -> {
            contextMenu.show(tp, e.getScreenX(), e.getScreenY());
        });
        //Ocultar ContextMenu cuando clickeamos fuera
        tp.setOnMousePressed((event) -> {
            hide(contextMenu);
        });
        //Le damos click a Crear Archivo
        crearArchivo.setOnAction((event) -> {
            FuncionesLoad.loadCreateWithParams(Constants.file, textarea, tp, st);
        });
        //Le damos click a Crear Carpeta
        crearCarpeta.setOnAction((event) -> {
            FuncionesLoad.loadCreateWithParams(Constants.directory, textarea, tp, st);
        });
    }

    public static void ContextMenuBorrar(String opcion, TilePane tp, BorderPane bp, Stage st, TextArea textarea, File f) {
        SingletonPath instancia = SingletonPath.getInstance();
        //Creamos ContextMenu
        ContextMenu contextMenu = new ContextMenu();
        MenuItem borrar = new MenuItem(Constants.delete);
        borrar.setId("borrar");
        contextMenu.getItems().addAll(borrar);
        //Mostrar ContextMenu con click derecho
        bp.setOnContextMenuRequested((ContextMenuEvent e) -> {
            contextMenu.show(bp, e.getScreenX(), e.getScreenY());
            e.consume();
        });
        //Ocultar ContextMenu cuando clickeamos fuera
        bp.setOnMousePressed((event) -> {
            hide(contextMenu);
        });
        //Le damos click a borrar
        borrar.setOnAction((event) -> {
            try {
                if (opcion.equals(Constants.file)) {
                    //Borramos el archivo
                    FuncionesFile.borrarFichero(instancia.getPath() + f.getName());
                } else if (opcion.equals(Constants.directory)) {
                    //Borramos el directorio
                    FuncionesFile.borrarDirectorio(instancia.getPath() + f.getName());
                }
                //Volvemos a mostrar
                FuncionesGeneral.refresh(tp, st, textarea);
                //Si el archivo que tenemos en el main Stage (con su nombre en el título y contenido en TextArea)
                //se ha borrado, resetearemos dicha ventana.
                if (instancia.getSelectedFile() != null && !Files.exists(Paths.get(instancia.getSelectedFile()))) {
                    st.setTitle(Constants.titleMain);
                    textarea.setText("");
                }
            } catch (IOException ex) {
                Logger.getLogger(FuncionesShow.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    //Ocultar ContextMenu
    private static void hide(ContextMenu c) {
        if (c.isShowing()) {
            c.hide();
        }
    }
}
