package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AboutPage
{
    public static void generate() throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("AboutPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.getIcons().add(new Image("sample/book_icon.png"));
        stage.setScene(scene);
        stage.showAndWait();

    }
}
