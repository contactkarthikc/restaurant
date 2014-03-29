package application;

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

public class RestaurantListController implements Initializable
{
	@FXML
	public static ListView<String> ListFxml = new ListView<String>();
	public Label RestaurantInfoLabel;
	public TextField searchTextField;
	public ComboBox<String> locationCity;
	public ComboBox<String> foodTypeCombo;
	static ArrayList<Restaurant> restaurants = MainController.displayRestaurants(RestaurantListController.location,RestaurantListController.foodType,RestaurantListController.searchedRestaurant);
	public static ObservableList<String> observableRestaurants = FXCollections.observableArrayList();
	public static String location;
	public static String searchedRestaurant;
	public static String foodType;
	public static Restaurant currentRestaurant;
	public static Restaurant TempRestaurant;
	
	//public ImageView RestaurantListImage;

	//Image image = new Image("deneme.png");

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		observableRestaurants.clear();
		if (restaurants != null){
			for(int i = 0; i < restaurants.size();i++)
			{
				observableRestaurants.add(restaurants.get(i).getName());
			} 
		}
		ListFxml.setItems(observableRestaurants);
	}
	
	
	//TODO It goes the restaurantScreen wherever I click
	@FXML
	public void ListController()
	{
		currentRestaurant = restaurants.get(ListFxml.getSelectionModel().getSelectedIndex());
		RestaurantMenuController.restaurantName.setText(RestaurantListController.currentRestaurant.getName());
		RestaurantMenuController.restaurantFoodMenus = MainController.showRestMenu(currentRestaurant.getRestaurantId());
		Main.stages.get(0).setScene(Main.scenes.get(2));
		Main.stages.get(0).show();
		RestaurantMenuController.initializeFood(currentRestaurant.getRestaurantId());
		TempRestaurant = currentRestaurant;
		currentRestaurant = null;
	}

	@FXML
	public void FavoriteButtonController()
	{
		restaurants = MainController.displayFavoritesList();
		observableRestaurants.clear();
		if(restaurants != null)
		{
			for(int i = 0; i < restaurants.size();i++)
			{
				observableRestaurants.add(restaurants.get(i).getName());
			}
			ListFxml.setItems(observableRestaurants);
		}

	}

	@FXML
	public void ImageController()
	{/*
		RestaurantListImage.setImage(image);*/
	}

	@FXML
	public void LabelController()
	{
		RestaurantInfoLabel.setText(currentRestaurant.getName() + "\n" + currentRestaurant.getLocation());
	}

	@FXML
	public void LogoutButtonController()
	{
		Main.stages.get(0).setScene(Main.scenes.get(0));
		Main.stages.get(0).show();
		MainController.Logout();
	}

	@FXML
	public void EditInfoButtonController()
	{
		Main.stages.get(1).setScene(Main.scenes.get(12));
		Main.stages.get(1).show();
	}

	@FXML
	public void CommentButtonController()
	{
		Main.stages.get(1).setScene(Main.scenes.get(12));
		Main.stages.get(1).show();
	}

	@FXML
	public void CommentsAndRatingButtonController()
	{
		Main.stages.get(1).setScene(Main.scenes.get(15));
		Main.stages.get(1).show();
	}

	@FXML
	public void LocationController()
	{
		location = (String) locationCity.getSelectionModel().getSelectedItem().toString();
		if (location.equals("All"))
			location = null;
	}

	@FXML
	public void TypeController()
	{
		foodType = (String)  foodTypeCombo.getSelectionModel().getSelectedItem().toString();
		if (foodType.equals("All"))
			foodType = null;
	}

	@FXML
	public void SearchButtonController()
	{
		searchedRestaurant = searchTextField.getText();
		observableRestaurants.clear();
		
		restaurants = MainController.displayRestaurants(location, foodType, searchedRestaurant);
		if (restaurants != null){
			for(int i = 0; i < restaurants.size();i++)
			{
				observableRestaurants.add(restaurants.get(i).getName());
			} 
		}
		ListFxml.setItems(observableRestaurants);
	}
	
	public void myReservationButtonController()
	{
		Main.stages.get(1).setScene(Main.scenes.get(19));
		Main.stages.get(1).show();
	}
	
	public void myReservationBackButtonController()
	{
		Main.stages.get(1).close();
	}
}
