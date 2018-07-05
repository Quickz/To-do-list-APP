package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListEntry
{
    public StackPane mainLayout;
    public TextField inputField;
    public Button okButton;
    public Button cancelButton;

    private Stage stage;
    private static boolean pressedOk;
    private static ListEntry listEntry;

    @FXML
    public void start()
    {
        System.out.println("starting");
    }

    @FXML
    public void initialize()
    {
        pressedOk = false;
        listEntry = this;
    }

    public void ok()
    {
        pressedOk = true;
        Stage stage = (Stage)okButton.getScene().getWindow();
        stage.close();
    }

    public void cancel()
    {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }

    public void onEnter()
    {
        ok();
    }

    public void onEscape(KeyEvent e)
    {
        if (e.getCode() == KeyCode.ESCAPE)
        {
            cancel();
        }
    }

    /**
     * generates a window with an input field
     * for a new list entry
     * returns the input field contents
     **/
    public static String generate() throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("ListEntry.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New entry");
        stage.getIcons().add(new Image("sample/paper_icon.png"));
        stage.setScene(scene);
        stage.showAndWait();

        if (pressedOk)
        {
            return listEntry.inputField.getText();
        }
        else
        {
            return "";
        }
    }
}
