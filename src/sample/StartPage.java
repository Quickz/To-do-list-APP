package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StartPage
{
    public BorderPane content;
    public ListView<String> toDoList;
    public Button addButton;
    public Button removeButton;

    private String listFilePath = System.getProperty("user.home") +
            "\\AppData\\Roaming\\To do list APP";

    /**
     * runs when the window opens
     * basically sort of a constructor
     **/
    @FXML
    public void initialize() throws Exception
    {
        loadItems();
    }

    /**
     * adds an item to the todo lists listView
     * and saves the items
     **/
    public void addEntry() throws Exception
    {
        String response = ListEntry.generate();
        if (!response.equals(""))
        {
            toDoList.getItems().add(response);
            saveItems();
        }
    }

    /**
     * removes an item from the to do lists listView
     * and saves the items
     **/
    public void removeEntry()
    {
        int index = toDoList.getSelectionModel().getSelectedIndex();

        // if there's something selected
        if (index != -1)
        {
            toDoList.getItems().remove(index);
            saveItems();
        }
    }

    @FXML
    private void onDelete(KeyEvent e)
    {
        if (e.getCode() == KeyCode.DELETE)
        {
            removeEntry();
        }
    }

    /**
     * loads to do list items
     * that were previously stored in a text file
     * creates an empty text file if there's nothing to load
     **/
    private void loadItems()
    {
        loadToDoList(listFilePath + "/data.txt");
    }

    /**
     * loads a to do list from a specified file
     *
     **/
    private void loadToDoList(String path)
    {
        FileReader reader = null;
        BufferedReader bufferedReader = null;

        try
        {
            reader = new FileReader(path);
            bufferedReader = new BufferedReader(reader);

            // clearing stuff before adding new
            toDoList.getItems().clear();

            String line;
            ObservableList<String> items = toDoList.getItems();
            while ((line = bufferedReader.readLine()) != null)
            {
                items.add(line);
            }
        }
        catch (IOException e)
        {
            System.out.println("Couldn't find a file to load.");
            saveItems();
        }
        finally
        {
            try
            {
                if (bufferedReader != null)
                {
                    bufferedReader.close();
                }
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
                System.out.println("Error in closing a file after reading");
            }
        }
    }

    /**
     * saves all the to do lists items
     *
     */
    private void saveItems()
    {
        File file = new File(listFilePath);
        boolean directoryDoesntExist = file.mkdirs();
        if (directoryDoesntExist)
        {
            System.out.println(
                    "Couldn't find an existing directory. " +
                            "Creating a new one.");
        }

        saveToDoList(listFilePath + "/data.txt");
    }

    /**
     * saves the to do list to a specified file
     *
     **/
    private void saveToDoList(String path)
    {
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;

        try
        {
            writer = new FileWriter(path);
            bufferedWriter = new BufferedWriter(writer);

            ObservableList<String> items = toDoList.getItems();
            for (String item: items)
            {
                bufferedWriter.write(item + System.lineSeparator());
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Error in saving a file");
        }
        finally
        {
            try
            {
                if (bufferedWriter != null)
                {
                    bufferedWriter.close();
                }
                if (writer != null)
                {
                    writer.close();
                }
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
                System.out.println("Error in closing a file after writing");
            }
        }
    }

    @FXML
    private void importToDoList()
    {
        Stage fileDialogStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import");
        File file = fileChooser.showOpenDialog(fileDialogStage);

        if (file != null)
        {
            loadToDoList(file.getPath());
            saveItems();
        }
    }

    @FXML
    private void exportToDoList()
    {
        Stage fileDialogStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Text Documents (*.txt)", ".txt"));

        File file = fileChooser.showSaveDialog(fileDialogStage);

        if (file != null)
        {
            saveToDoList(file.getPath());
        }
    }

    @FXML
    private void openLicensePage() throws Exception
    {
        LicensePage.generate();
    }

    @FXML
    private void openAboutPage() throws Exception
    {
        AboutPage.generate();
    }

    @FXML
    private void exit()
    {
        Stage stage = (Stage)content.getScene().getWindow();
        stage.close();
    }
}
