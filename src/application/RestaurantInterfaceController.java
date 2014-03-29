package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class RestaurantInterfaceController implements Initializable
{
	ArrayList<Food>foodList;
	ArrayList<String> reservations;
	ObservableList<String> observableFoods = FXCollections.observableArrayList();
	ObservableList<String> observableRes = FXCollections.observableArrayList();
	public ListView<String> CurrentMenuListView = new ListView<String>();
	public ListView<String> ReservationsListView = new ListView<String>();

	@FXML
	public void CreateMenuController()
	{
		Main.stages.get(0).setScene(Main.scenes.get(10));
		Main.stages.get(0).show();
	}
	@FXML
	public void LogoutButtonController()
	{
		Main.stages.get(0).setScene(Main.scenes.get(0));
		Main.stages.get(0).show();
		MainController.Logout();
	}

	public void EditButtonController()
	{
		Main.stages.get(1).setScene(Main.scenes.get(14));
		Main.stages.get(1).show();
	}

	public void TablesListButtonController()
	{
		Main.stages.get(1).setScene(Main.scenes.get(17));
		Main.stages.get(1).show();
	}

	public void TablesOrderController()
	{
		Main.stages.get(1).setScene(Main.scenes.get(18));
		Main.stages.get(1).show();
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	public void refresh()
	{
		reservations = MainController.showReservations();
		
		observableRes.clear();

		if (reservations != null){
			
			for(int i = 0; i < reservations.size();i++)
			{
				String[] res = reservations.get(i).split(",");
				observableRes.add(res[0] + " - " + res[1] + 
						" - " + res[2]);
			}
			
			ReservationsListView.setItems(observableRes);
		}
		
		foodList = MainController.showMenu();
		
		observableFoods.clear();
		
		if (foodList != null){
			for(int i = 0; i < foodList.size();i++)
			{
				observableFoods.add(foodList.get(i).getName());
			}
			
			CurrentMenuListView.setItems(observableFoods);
		}
	}
	
}
