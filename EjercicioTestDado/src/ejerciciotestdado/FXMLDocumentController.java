/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciotestdado;

import ejerciciotestdado.helper.LocatedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 *
 * @author dani
 */
public class FXMLDocumentController implements Initializable {

    private int seconds = 0;
    private Timeline timeline;

    @FXML
    private ImageView imageView;

    /*@FXML
    private Button btnStart;

    @FXML
    private Button btnStop;*/
    @FXML
    private void start() {
        timeline.play();
    }

    @FXML
    private void stop() {
        timeline.stop();
        seconds = 0;
    }

    private void running() {
        timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), (event) -> {
            seconds++;
            imageView.setImage(new LocatedImage(EjercicioTestDado.class.getResource("resources/" + (seconds % 6) + ".png").toString()));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        running();
    }

}
