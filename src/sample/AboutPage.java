package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

    @FXML
    private void openSourceCode()
    {
        String url = "https://github.com/Quickz/To-do-list-APP";

        try
        {
            if (Desktop.isDesktopSupported())
            {
                Desktop.getDesktop().browse(new URI(url));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println("Error in browsing the source code web page");
        }
    }
}
