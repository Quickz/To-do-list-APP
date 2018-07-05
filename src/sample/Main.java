package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        generateMainWindow(primaryStage);
    }

    private void generateMainWindow(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        root.getStylesheets().add("sample/style.css");
        stage.setTitle("To do List");
        stage.getIcons().add(new Image("sample/book_icon.png"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
