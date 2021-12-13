/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestordeproyectos.funciones;

import gestordeproyectos.GestorDeProyectos;
import gestordeproyectos.resources.Constants;
import gestordeproyectos.resources.SingletonPath;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author dani
 */
public class FuncionesShow {

    public static void recogerArchivos(TilePane tp, Stage st, TextArea textarea) throws IOException {
        SingletonPath instancia = SingletonPath.getInstance();
        Path path = Paths.get(instancia.getPath());
        List<Path> result;
        //Si no especificamos el '1' va a mostrar todos los directorios y subdirectorios.
        //Ese 1 indica que solo se cojan los que se encuentran a la primera altura/profundidad 1.
        try (Stream<Path> walk = Files.walk(path, 1)) {
            result = walk.collect(Collectors.toList());
        }
        recorrerArchivos(result, tp, st, textarea);
        //'result' contiene -> archivos y directorios de carpeta que pasemos como parámetro (FILES)
    }

    private static void recorrerArchivos(List<Path> result, TilePane tp, Stage st, TextArea textarea) {
        //Mostramos contenido de result excepto primer elemento (también muestra la propia carpeta FILES; queremos evitarlo)
        for (int i = 1; i < result.size(); i++) {
            mostrarArchivo(result, i, tp, st, textarea);
        }
    }

    private static void mostrarArchivo(List<Path> result, int i, TilePane tp, Stage st, TextArea textarea) {
        ImageView imageview = null;
        BorderPane bp = new BorderPane();
        Text text = new Text();
        //Pasamos el path a FILE, para ver si es archivo o directorio
        File f = result.get(i).toFile();
        if (f.isDirectory()) {
            imageview = getImageView(Constants.directory);
        } else if (f.isFile()) {
            imageview = getImageView(Constants.file);
        }
        //Ponemos su nombre y añadimos foto+nombre al panel
        text.setText(f.getName());
        bp.setCenter(imageview);
        bp.setBottom(text);
        //Centramos el texto de los BorderPane que vamos generando
        BorderPane.setAlignment(text, Pos.CENTER);
        //Ponemos padding a cada BorderPane, para que se separen las imágenes y texto
        bp.setPadding(new Insets(10, 10, 10, 10));
        //Si es archivo ponemos la id archivotxt (quitando el punto, que da problemas)
        String s = f.getName();
        if (s.contains(".")) {
            s = s.replace(".", "");
        }
        bp.setId(s);
        //Añadimos el panel al TilePane
        tp.getChildren().add(bp);
        //Añadimos la funcionalidad de seleccionar archivos o carpetas con doble click, y añadimos ContextMenu borrar.
        if (f.isFile()) {
            dobleClickArchivo(bp, text, st, textarea);
            FuncionesContextMenu.ContextMenuBorrar(Constants.file, tp, bp, st, textarea, f);
        } else if (f.isDirectory()) {
            dobleClickDirectorio(bp, text, tp, st, textarea);
            FuncionesContextMenu.ContextMenuBorrar(Constants.directory, tp, bp, st, textarea, f);
        }
    }

    private static void dobleClickArchivo(BorderPane bp, Text text, Stage st, TextArea textarea) {
        bp.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                SingletonPath instancia = SingletonPath.getInstance();
                //Actualizamos título del DocumentController (ponemos el nombre del fichero)
                st.setTitle(text.getText());
                //Guardamos ruta del archivo, por si queremos borrarlo o actualizarlo después
                instancia.setSelectedFile(text.getText());
                //Actualizamos el textArea con el contenido del archivo
                try {
                    //Recogemos contenido archivo
                    byte[] bytes = Files.readAllBytes(Paths.get(instancia.getSelectedFile()));
                    String contenidoArchivo = new String(bytes, StandardCharsets.UTF_8);
                    //Lo ponemos en el textArea
                    textarea.setText(contenidoArchivo);
                } catch (IOException ex) {
                    Logger.getLogger(FuncionesShow.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Cerramos la ventana
                FuncionesGeneral.closeWindow(event);
            }
        });
    }

    private static void dobleClickDirectorio(BorderPane bp, Text text, TilePane tp, Stage st, TextArea textarea) {
        bp.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                SingletonPath instancia = SingletonPath.getInstance();
                try {
                    //Calculamos la nueva ruta
                    instancia.addFolder(text.getText());
                    //Cambiamos el título de la ventana
                    FuncionesGeneral.changeTitle(event, instancia.getPath());
                    //Mostramos ruta actualizada
                    FuncionesGeneral.refresh(tp, st, textarea);
                } catch (IOException ex) {
                    Logger.getLogger(FuncionesShow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    //Función auxiliar de mostrarArchivos()
    public static ImageView getImageView(String s) {
        ImageView imageview = new ImageView();
        imageview.setFitHeight(100);
        imageview.setFitWidth(100);
        //Ponemos el icono de archivo o de carpeta, según lo que le pasemos como parámetro
        Image image = new Image(GestorDeProyectos.class.getResource("resources/images/" + s + ".png").toString());
        imageview.setImage(image);
        return imageview;
    }

}
