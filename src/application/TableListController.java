package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TableListController implements Initializable
{ 
	public static ListView<String> tables = new ListView<String>();
	public TextField TableNoTextField;
	public int tableNo = 0;
	public static ArrayList<String> tableList = new ArrayList<String>();
	static ObservableList<String> observableTables = FXCollections.observableArrayList();
	
	public void TableAddController()
	{
		tableNo = Integer.parseInt(TableNoTextField.getText());
		MainController.addTable(tableNo);
		update();
	}
	
	public void TableRemoveController()
	{
		tableNo = Integer.parseInt(TableNoTextField.getText());
		MainController.deleteTable(tableNo);
		update();
	}
	
	
	public void TablesOrderController()
	{
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		update();
		
	}
	
	public static void update()
	{
		tableList = MainController.displayTableList();
	
		observableTables.clear();

		if (tableList != null){
			
			for(int i = 0; i < tableList.size();i++)
			{
				observableTables.add(tableList.get(i));
			}
			
			tables.setItems(observableTables);
		}
		
	}
}


