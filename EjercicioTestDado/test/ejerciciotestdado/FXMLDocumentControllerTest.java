/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciotestdado;

import ejerciciotestdado.helper.LocatedImage;
import java.util.concurrent.TimeUnit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author dani
 */
public class FXMLDocumentControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void clickOnStart_WaitOneSecond_ResultNumberOne_OK() throws InterruptedException {
        clickOn("#btnStart");
        TimeUnit.SECONDS.sleep(1);
        //Preparamos imagen 1 e imagen actual
        LocatedImage uno = new LocatedImage(EjercicioTestDado.class.getResource("resources/1.png").toString());
        ImageView imageView = lookup("#imageView").query();
        LocatedImage actual = (LocatedImage) imageView.getImage();
        //Comparamos a ver si son la misma
        assertEquals(uno.getURL(), actual.getURL());
    }

    @Test
    public void clickOnStart_WaitTwoSecond_ResultNumberTwo_OK() throws InterruptedException {
        clickOn("#btnStart");
        TimeUnit.SECONDS.sleep(2);
        //Preparamos imagen 2 e imagen actual
        LocatedImage dos = new LocatedImage(EjercicioTestDado.class.getResource("resources/2.png").toString());
        ImageView imageView = lookup("#imageView").query();
        LocatedImage actual = (LocatedImage) imageView.getImage();
        //Comparamos a ver si son la misma
        assertEquals(dos.getURL(), actual.getURL());
    }

//    @Test
//    public void integrationTest_Equilatero_OK() throws InterruptedException {
//        clickOn();
//        Label label = lookup("#txtResult").query();
//        assertEquals(label.getText(), Constants.TRIANGULO_EQUILATERO);
//    }
//
//    private void clickOn(String valueUp, String valueMiddle, String valueDown) {
//        clickOn("#textFieldUp").write(valueUp).press(KeyCode.ENTER);
//        clickOn("#textFieldMiddle").write(valueMiddle).press(KeyCode.ENTER);
//        clickOn("#textFieldDown").write(valueDown).press(KeyCode.ENTER);
//    }
//
//    @Test
//    public void integrationTest_LetterValues_KO() throws InterruptedException {
//        clickOn();
//        Label label = lookup("#txtResult").query();
//        assertEquals(label.getText(), Constants.TRIANGULO_EQUILATERO);
//    }
//
//    @Test
//    public void integrationTest__Isosceles_OK() throws InterruptedException {
//        clickOn("#textFieldUp").write("2").press(KeyCode.ENTER);
//        clickOn("#textFieldMiddle").write("2").press(KeyCode.ENTER);
//        clickOn("#textFieldDown").write("3").press(KeyCode.ENTER);
//        Label label = lookup("#txtResult").query();
//        assertEquals(label.getText(), Constants.TRIANGULO_ISOSCELES);
//    }
}
