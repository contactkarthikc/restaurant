package application;

import java.awt.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 

import javax.imageio.ImageIO;

import Utils.DatabaseAdapter;

public class Main extends Application {


	public static ArrayList<Stage> stages = new ArrayList<Stage>();
	public static ArrayList<Scene> scenes = new ArrayList<Scene>();
	public ImageView cashImage;
	public ImageView CreditCardImage;

	@Override
	public void start(Stage primaryStage){
		
		//ImageView CreditCardImage = new ImageView(new Image(getResource("cash.jpg")));
		//ImageView  cashImage = new ImageView(new Image("/cash.jpg"));

		Stage mainStage = new Stage();
		mainStage.setTitle("Proje");
		Stage stageReservation = new Stage();
		Stage stageError = new Stage();
		stages.add(mainStage);
		stages.add(stageReservation);
		stages.add(stageError);
		DatabaseAdapter.connect();

		try {
			FXMLLoader LoginScreen = new FXMLLoader(Main.class.getResource("LoginScreen.fxml"));
			FXMLLoader RestaurantList= new FXMLLoader(Main.class.getResource("RestaurantList.fxml"));
			FXMLLoader RestaurantMenu = new FXMLLoader(Main.class.getResource("RestaurantMenu.fxml"));
			FXMLLoader SignUpMain = new FXMLLoader(Main.class.getResource("SignUpMain.fxml"));
			FXMLLoader SignUpCustomer = new FXMLLoader(Main.class.getResource("SignUpCustomer.fxml"));
			FXMLLoader SignUpRestaurant = new FXMLLoader(Main.class.getResource("SignUpRestaurant.fxml"));
			FXMLLoader ReservationScreen = new FXMLLoader(Main.class.getResource("ReservationScreen.fxml"));
			FXMLLoader ReservationSuccess = new FXMLLoader(Main.class.getResource("ReservationSuccess.fxml"));
			FXMLLoader ErrorMessage = new FXMLLoader(Main.class.getResource("ErrorMessage.fxml"));
			FXMLLoader RestaurantInterface = new FXMLLoader(Main.class.getResource("RestaurantInterface.fxml"));
			FXMLLoader MenuCreation = new FXMLLoader(Main.class.getResource("MenuCreation.fxml"));
			FXMLLoader ForgotPasswordPopUp = new FXMLLoader(Main.class.getResource("ForgotPasswordPopUp.fxml"));
			FXMLLoader EditCustomerInfo = new FXMLLoader(Main.class.getResource("EditCustomerInfo.fxml"));
			FXMLLoader OrderInformation = new FXMLLoader(Main.class.getResource("OrderInformation.fxml"));
			FXMLLoader EditRestaurantInfo = new FXMLLoader(Main.class.getResource("EditRestaurantInfo.fxml"));
			FXMLLoader CommentsAndRating = new FXMLLoader(Main.class.getResource("CommentsAndRating.fxml"));
			FXMLLoader PaymentChoice = new FXMLLoader(Main.class.getResource("PaymentChoice.fxml"));
			FXMLLoader TableList = new FXMLLoader(Main.class.getResource("TableList.fxml"));
			FXMLLoader TableOrder = new FXMLLoader(Main.class.getResource("TableOrder.fxml"));
			FXMLLoader OrderSuccess = new FXMLLoader(Main.class.getResource("OrderSuccess.fxml"));

			AnchorPane LoginScreenPage = (AnchorPane) LoginScreen.load();
			AnchorPane RestaurantListPage = (AnchorPane) RestaurantList.load();
			AnchorPane RestaurantMenuPage = (AnchorPane) RestaurantMenu.load();
			AnchorPane SignUpMainPage = (AnchorPane) SignUpMain.load();
			AnchorPane SignUpCustomerPage = (AnchorPane) SignUpCustomer.load();
			AnchorPane SignUpRestaurantPage = (AnchorPane) SignUpRestaurant.load();
			AnchorPane ReservationScreenPage = (AnchorPane) ReservationScreen.load();
			AnchorPane ReservationSuccessPage = (AnchorPane) ReservationSuccess.load();
			AnchorPane ErrorMessagePage = (AnchorPane) ErrorMessage.load();
			AnchorPane RestaurantInterfacePage = (AnchorPane) RestaurantInterface.load();
			AnchorPane MenuCreationPage = (AnchorPane) MenuCreation.load();
			AnchorPane ForgotPasswordPopUpPage = (AnchorPane) ForgotPasswordPopUp.load();
			AnchorPane EditCustomerInfoPage = (AnchorPane) EditCustomerInfo.load();
			AnchorPane OrderInformationPage = (AnchorPane) OrderInformation.load();
			AnchorPane EditRestaurantInfoPage = (AnchorPane) EditRestaurantInfo.load();
			AnchorPane CommentsAndRatingPage = (AnchorPane) CommentsAndRating.load();
			AnchorPane PaymentChoicePage = (AnchorPane) PaymentChoice.load();
			AnchorPane TableListPage = (AnchorPane) TableList.load();
			AnchorPane TableOrderPage = (AnchorPane) TableOrder.load();
			AnchorPane OrderSuccessPage = (AnchorPane) OrderSuccess.load();

			Scene LoginScreenScene = new Scene(LoginScreenPage);
			Scene RestaurantListScene = new Scene(RestaurantListPage);
			Scene RestaurantMenuScene = new Scene(RestaurantMenuPage);
			Scene SignUpMainScene = new Scene(SignUpMainPage);
			Scene SignUpCustomerScene = new Scene(SignUpCustomerPage);
			Scene SignUpRestaurantScene = new Scene(SignUpRestaurantPage);
			Scene ReservationScreenScene = new Scene(ReservationScreenPage);
			Scene ReservationSuccessScene = new Scene(ReservationSuccessPage);
			Scene ErrorMessageScene = new Scene(ErrorMessagePage);
			Scene RestaurantInterfaceScene = new Scene(RestaurantInterfacePage);
			Scene MenuCreationScene = new Scene(MenuCreationPage);
			Scene ForgotPasswordPopUpScene = new Scene(ForgotPasswordPopUpPage);
			Scene EditCustomerInfoScene = new Scene(EditCustomerInfoPage);
			Scene OrderInformationScene = new Scene(OrderInformationPage);
			Scene EditRestaurantInfoScene = new Scene(EditRestaurantInfoPage);
			Scene CommentsAndRatingScene = new Scene(CommentsAndRatingPage);
			Scene PaymentChoiceScene = new Scene(PaymentChoicePage);
			Scene TableListScene = new Scene(TableListPage);
			Scene TableOrderScene = new Scene(TableOrderPage);
			Scene OrderSuccessScene = new Scene(OrderSuccessPage);

			scenes.add(LoginScreenScene);//0
			scenes.add(RestaurantListScene);//1
			scenes.add(RestaurantMenuScene);//2
			scenes.add(SignUpMainScene);//3
			scenes.add(SignUpCustomerScene);//4
			scenes.add(SignUpRestaurantScene);//5
			scenes.add(ReservationScreenScene);//6
			scenes.add(ReservationSuccessScene);//7
			scenes.add(ErrorMessageScene);//8
			scenes.add(RestaurantInterfaceScene);//9
			scenes.add(MenuCreationScene);//10
			scenes.add(ForgotPasswordPopUpScene);//11
			scenes.add(EditCustomerInfoScene);//12
			scenes.add(OrderInformationScene);//13
			scenes.add(EditRestaurantInfoScene);//14
			scenes.add(CommentsAndRatingScene);//15
			scenes.add(PaymentChoiceScene);//16
			scenes.add(TableListScene);//17
			scenes.add(TableOrderScene);//18
			scenes.add(OrderSuccessScene);//19

			mainStage.setScene(LoginScreenScene);
			mainStage.show();

		} catch (IOException e) {
			System.err.println("Error!");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}


