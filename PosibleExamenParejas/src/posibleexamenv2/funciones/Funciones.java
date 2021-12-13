/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posibleexamenv2.funciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import posibleexamenv2.PosibleExamenV2;

/**
 *
 * @author dani
 */
public class Funciones {

    //Gestionar y comprobar último y penúltimo que clickeamos
    private static int lastPosition = -1;
    private static String lastImage = null;
    private static ImageView lastImageView = null;
    private static String lastColor = null;
    //Llevar la cuenta de los que hemos girado
    private static final List<Integer> girados = new ArrayList<Integer>();
    //Gestionar vidas
    public static int vidas = 3;
    public static Label labelLives;
    //Gestionar victoria
    public static int acertadas = 0;
    //Preparamos la lista aleatoria
    private static final String[] im = {"barco", "calavera", "minion", "mono", "nube", "perro", "barco", "calavera", "minion", "mono", "nube", "perro"};
    private static final List<String> imagenes = Arrays.asList(im);

    public static void shuffle() {
        Collections.shuffle(imagenes);
    }

    public static void showLives() {
        labelLives.setText(String.valueOf(vidas));
    }

    public static void checkLives(MouseEvent e) {
        if (vidas == 0) {
            FunctionGeneral.closeWindow(e);
        }
    }

    public static void checkGuessed() {
        if (acertadas == 6) {
            System.out.println("Felicidades. Has ganado.");
        }
    }

    public static void showPane(TilePane tp) {
        for (int i = 0; i < imagenes.size(); i++) {
            BorderPane bp = new BorderPane();
            ImageView imageView = new ImageView();
            //Preparamos posiciones rosas y grises
            int[] rosas = {0, 2, 5, 7, 8, 10};
            int[] grises = {1, 3, 4, 6, 9, 11};
            //
            if (FunctionGeneral.numInArray(rosas, i)) {
                ponerFoto(imageView, "rosa");
            } else if (FunctionGeneral.numInArray(grises, i)) {
                ponerFoto(imageView, "gris");
            }
            bp.setCenter(imageView);
            bp.setPadding(new Insets(10, 10, 10, 10));
            tp.getChildren().addAll(bp);
            //Preparamos variables auxiliares
            final String imagenActu = imagenes.get(i);
            final int pos = i;
            //Preparamos los listener
            bp.setOnMouseClicked((MouseEvent event) -> {
                if (FunctionGeneral.numInArray(rosas, pos)) {
                    comprobar(imagenActu, imageView, pos, event, "rosa");
                } else if (FunctionGeneral.numInArray(grises, pos)) {
                    comprobar(imagenActu, imageView, pos, event, "gris");
                }
            });
        }
    }

    public static void comprobar(String imagenActu, ImageView imageview, int posicion, MouseEvent e, String color) {
        //No podemos clickear ya girados
        if (!FunctionGeneral.numInArrayList(girados, posicion)) {
            //Si no se ha clickeado ninguno antes
            if (lastPosition == -1 && lastImage == null) {
                //Damos la vuelta
                ponerFoto(imageview, imagenActu);
                //Guardamos la que hemos clickeado (ImageView, foto, color, posicion)
                saveChosenImage(posicion, imagenActu, imageview, color);
            } //Si es la segunda vez clickeando
            else {
                //Tenemos que darle a otra foto para que haga algo
                if (posicion != lastPosition) {
                    //Damos la vuelta
                    ponerFoto(imageview, imagenActu);
                    //Si es pareja
                    if (imagenActu.equals(lastImage)) {
                        acertadas++;
                        checkGuessed();
                        girados.add(lastPosition);
                        girados.add(posicion);
                    }//Si no es pareja
                    else {
                        vidas--;
                        showLives();
                        checkLives(e);
                        //Esperamos un segundo para que puedan ver la girada

                        //Damos la vuelta a ambas de nuevo
                        ponerFoto(imageview, color);
                        ponerFoto(lastImageView, lastColor);
                    }
                    resetChosenImage();
                } else {
                    System.out.println("No intentes hacer click en una girada.");
                }
            }
        } else {
            System.out.println("No hagas click en una pareja ya hecha.");
        }
    }

    private static void saveChosenImage(int posicion, String imagenActu, ImageView imageview, String color) {
        lastPosition = posicion;
        lastImage = imagenActu;
        lastImageView = imageview;
        lastColor = color;
    }

    private static void resetChosenImage() {
        saveChosenImage(-1, null, null, null);
    }

    public static void resetTilePane(TilePane tp) {
        tp.getChildren().clear();
        showPane(tp);
    }

    public static void ponerFoto(ImageView imageview, String s) {
        imageview.setFitHeight(100);
        imageview.setFitWidth(100);
        //Ponemos color gris o rosa
        Image image = new Image(PosibleExamenV2.class.getResource("resources/" + s + ".jpg").toString());
        imageview.setImage(image);
    }

    public static Image getImage(String s) {
        return new Image(PosibleExamenV2.class.getResource("resources/" + s + ".jpg").toString());
    }

    public static ImageView getImageView(String s) {
        ImageView imageview = new ImageView();
        imageview.setFitHeight(100);
        imageview.setFitWidth(100);
        //Ponemos el icono de archivo o de carpeta, según lo que le pasemos como parámetro
        Image image = new Image(PosibleExamenV2.class.getResource("resources/" + s + ".jpg").toString());
        imageview.setImage(image);
        return imageview;
    }

}
