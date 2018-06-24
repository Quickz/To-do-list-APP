package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class Controller
{
    public BorderPane content;
    public ListView<String> toDoList;
    public Button addButton;
    public Button removeButton;

    public void addEntry() throws Exception
    {
        String response = ListEntry.generate();
        if (!response.equals(""))
        {
            toDoList.getItems().add(response);
        }
    }

    public void removeEntry()
    {
        int index = toDoList.getSelectionModel().getSelectedIndex();

        // if there's something selected
        if (index != -1)
        {
            toDoList.getItems().remove(index);
        }
    }
}
