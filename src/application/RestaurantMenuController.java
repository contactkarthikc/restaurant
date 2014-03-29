package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RestaurantMenuController implements Initializable
{
	public static int id;
	public static Label restaurantName = new Label();
	public static Label foodNameText = new Label();
	public TextField foodNum;
	public TextField tableCode;
	public TextArea addNote;
	String foodNumString;
	String addNoteString; 
	public static ListView restaurantOrder = new ListView<String>();
	public static ListView restaurantMenu = new ListView<String>();
	public static ObservableList<String> observableRestaurantMenu = FXCollections.observableArrayList();
	public static ObservableList<String> observableRestaurantOrder = FXCollections.observableArrayList();
	public static ArrayList<Food> restaurantFoodMenus;
	public static ArrayList<Food> clickedMenuFoods = new ArrayList<Food>();
	public static Food clickedFood;
	public static Food deleteClickedFood;
	
	
	@FXML
	public void menuController()
	{
		clickedFood = restaurantFoodMenus.get(restaurantMenu.getSelectionModel().getSelectedIndex());
		clickedMenuFoods.add(clickedFood);
		String str1= RestaurantListController.observableRestaurants.get(RestaurantListController.ListFxml.getSelectionModel().getSelectedIndex());
		restaurantName.setText(str1);
		foodNameText.setText(clickedFood.getName());
		Main.stages.get(1).setScene(Main.scenes.get(13));
		Main.stages.get(1).show();
		
	}
	
	//Add to delete from Favorites
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	@FXML
	public void BackButtonController()
	{
		Main.stages.get(0).setScene(Main.scenes.get(1));
		Main.stages.get(0).show();
	}
	
	@FXML
	public void ReservationButtonController()
	{
		ReservationScreenController.setId(id);
		Main.stages.get(1).setScene(Main.scenes.get(6));
		Main.stages.get(1).show();
		TableListController.update();
	}
	
	@FXML
	public void OrderButtonController()
	{
		foodNumString = foodNum.getText();
		addNoteString = addNote.getText();
		observableRestaurantOrder.add(foodNumString + " " + clickedFood.getName());
		restaurantOrder.setItems(observableRestaurantOrder);
		Main.stages.get(1).close();
	}
	
	@FXML
	public void OrderFinalButtonController()
	{
		RestaurantListController.TempRestaurant.getRestaurantId();
		if(tableCode.getText() != null)
		{
			MainController.addOrder(RestaurantListController.TempRestaurant.getRestaurantId(),clickedMenuFoods,tableCode.getText());
			Main.stages.get(1).setScene(Main.scenes.get(19));
			Main.stages.get(1).show();
		}
	}
	
	public void OrderDeleteController()
	{
		if(restaurantOrder != null & observableRestaurantOrder != null)
		{
			deleteClickedFood = clickedMenuFoods.get(restaurantOrder.getSelectionModel().getSelectedIndex());
			int del = restaurantOrder.getSelectionModel().getSelectedIndex();
			System.out.println(del);
			observableRestaurantOrder.remove(del);
		}
	}
	
	@FXML
	public void RequestBillController()
	{
		System.out.println(restaurantFoodMenus);
		Main.stages.get(1).setScene(Main.scenes.get(16));
		Main.stages.get(1).show();
		System.out.println(restaurantFoodMenus);
	}

	public static void initializeFood(int restaurantId) {
		restaurantFoodMenus = MainController.showRestMenu(restaurantId);
		observableRestaurantMenu.clear();
		id = restaurantId;
		if (restaurantFoodMenus != null){
			for(int i = 0; i < restaurantFoodMenus.size();i++)
			{
				observableRestaurantMenu.add(restaurantFoodMenus.get(i).getName());
			} 
			
		}
		restaurantMenu.setItems(observableRestaurantMenu);
	}
	
	public static void AddFavorite()
	{
		try{MainController.addFavorites(id);}
		catch (SQLException e) {
			Main.stages.get(1).setScene(Main.scenes.get(8));
			Main.stages.get(1).show();
		}
	}
	
	public static void RemoveFavorite(){
		
		MainController.removeFavorites(id);

	}
	

	
}
