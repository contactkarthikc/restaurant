package application;

import java.sql.SQLException;
import java.util.*;

import Utils.DatabaseAdapter;


public class MainController {

	protected static Customer loggedInCustomer;
	protected static Restaurant activeRestaurant;

	//Checks the login information of the customer and initializes the loggedInCustomer for
	//fast and easy use
	protected static boolean loginCustomer(String email,String pass)
	{
		String success = Customer.login(email,pass);

		if (success == "")
			return false;

		//Divide the result to get name and if there is cardName
		String[] result = success.split(",");

		if (result.length == 1)
		{
			loggedInCustomer = new Customer(result[0],email,pass,true);
		}
		else
		{
			loggedInCustomer = new Customer(result[0],email,pass,result[1],true);
		}

		return true;
	}

	//Calls the login function of Restaurant and checks if login is success and returns accordingly
	protected static boolean loginRestaurant(int restaurantID,String password)
	{
		activeRestaurant =  RestaurantManager.login(restaurantID,password);

		if (activeRestaurant != null)
		{
			return true;
		}

		return false;
	}

	//Adds the data of customer to database and logs in afterwards
	protected static boolean signUpCustomer(String name,String email,String password,String CreditCardNumber)
	{
		if (!DatabaseAdapter.checkUserEmail(email))
			return false;

		if (CreditCardNumber == "")
		{
			loggedInCustomer = new Customer(name,email,password,false);
		}
		else
		{
			loggedInCustomer = new Customer(name,email,password,CreditCardNumber,false);
		}

		return true;
	}

	//Adds the data of restaurant to database and logs in afterwards
	protected static boolean signUpRestaurant(String restaurantName,String location,String openTime,String closeTime,String telephone,String bankNumber,int Restaurant_id,String password)
	{
		if (DatabaseAdapter.checkRestaurantId(Restaurant_id))
			return false;

		activeRestaurant = new Restaurant(restaurantName,location,openTime,closeTime,telephone,bankNumber,password,false);

		return true;
	}

	//Returns an Arraylist of restaurants
	protected static ArrayList<Restaurant>displayRestaurants(String location,String type,String name)
	{
		return RestaurantList.filterRestaurantList(location,type,name);
	}

	//Returns the reservations list for the loggedIn Customer
	protected static ArrayList<Reservation> reservationList()
	{
		return loggedInCustomer.reservationManager.getReservations();
	}

	//Creates reservation with acceptable time and restaurantID
	protected static String makeReservation(int restaurantId,String time)
	{
		return loggedInCustomer.reservationManager.createReservation(restaurantId,time);
	}

	protected static void deleteCustomerReservation(String resCode)
	{
		loggedInCustomer.reservationManager.cancelReservation(resCode);
	}

	//Calls the method of the logged Customer to added to restaurant from favorites table
	protected static void addFavorites(int RestaurantId) throws SQLException
	{
		loggedInCustomer.addFavoritesList(RestaurantId);
	}

	//Calls the method of the logged Customer to remove the restaurant from favorites table
	protected static void removeFavorites(int restaurantId)
	{
		loggedInCustomer.deleteFromFavorites(restaurantId);
	}

	//Returns the ArrayList of Restaurants to Gui Controllers
	protected static ArrayList <Restaurant>displayFavoritesList()
	{
		//returns anything exceept bankAccountNumber and password which will be left in the proxy construction
		ArrayList<String> favorites = loggedInCustomer.getFavoritesList();
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

		Restaurant add;

		
		if (favorites.size() == 0)
			return null;
		for (int i = 0;i < favorites.size();i++)
		{
			String[] info = favorites.get(i).split(",");
			
			//The proxy construction
			add = new Restaurant(info[0],info[1],info[2],info[3],info[4],"","",true);
			add.setType(info[5]);
			if (info.length == 7)
				add.setPicture(info[6]);

			restaurants.add(add);
		}

		return restaurants;
	}

	//Returns true if email is not found on the database
	protected static boolean forgotPassword(String email)
	{
		return DatabaseAdapter.checkUserEmail(email);
		/*
		else
		{
			// Recipient's email
			String to = "email";

			// Sender's email
			String from = "restaurant@gmail.com";

			// Assuming sending email from localhost
			String host = "localhost";


			Properties properties = System.getProperties();

			properties.setProperty("mail.smtp.host", host);

			Session session = Session.getDefaultInstance(properties);

			try{
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(to));

				// Set Subject: header field
				message.setSubject("Your Restaurant Reservation System");

				// Now set the actual message
				
				if(email.contains(@))
					message.setText(DatabaseAdapter.getCustomerPassword(email));
				else
					message.setText(DatabaseAdapter.getRestaurantPassword(email) + " " + DatabaseAdapter.getRestaurantID(email));
				
				

				// Send message
				Transport.send(message);
			}catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}*/
	}

	//Changes the attributes of the Customer
	protected static void manageAccount(String newName,String newPass,String newEmail,String creditCardNumber)
	{
		loggedInCustomer.manageAccount(newName, newPass, "", creditCardNumber);
	}

	
	protected static boolean addOrder(int restaurantId,ArrayList<Food> food,String tableCode)
	{
		return loggedInCustomer.orderManager.addOrder(restaurantId, food, tableCode);
	}
	
	protected static void deleteOrder()
	{
		loggedInCustomer.orderManager.cancelOrder();
	}
	
	protected static ArrayList<Food> displayCurrentOrder()
	{
		 return loggedInCustomer.orderManager.displayOrder();
	}
	
	//If the choice is -1, the food is erase else if 1, it will be added
	protected static void manageOrder(Food food,int restaurantId,int choise)
	{
		if (choise == 0)
		{
			loggedInCustomer.orderManager.addFood(food,restaurantId);
		}
		else if (choise == 1)
		{
			loggedInCustomer.orderManager.deleteFood(food,restaurantId);
		}
	}
	
	protected static void payOrder()
	{
		loggedInCustomer.orderManager.payOrder();
	}
	
	protected static void addTable(int tableId)
	{
		activeRestaurant.tableManager.addTable(tableId);
	}
	
	protected static boolean deleteTable(int tableId)
	{
		//Returns false if the table is not empty 
		return activeRestaurant.tableManager.deleteTable(tableId);
	}
	
	protected static ArrayList<String> displayTableList()
	{
		return activeRestaurant.tableManager.displayTables();
	}
	
	//Returns the table things in arraylist as foodname portion and note
	protected static ArrayList<String> showTableOrder(int table_num)
	{
		return activeRestaurant.orderList.displayOrder(activeRestaurant.getRestaurantId(),table_num);
	}
	
	protected static void menuFoodAddition(String food_name, double price, String food_details, String foodImage, String foodCategory)
	{
		activeRestaurant.menu.addFoodToMenu(food_name,price,food_details,foodImage,foodCategory,true);
	}
	
	protected static void menuFoodRemoval(int food_id)
	{
		activeRestaurant.menu.deleteFoodFromMenu(food_id);
	}
	
	protected static ArrayList<String> showReservations() {


		return activeRestaurant.reservationList.displayReservations(activeRestaurant.getRestaurantId());
	}

	protected static void deleteRestaurantReservation(String code)
	{
		activeRestaurant.reservationList.eraseReservation(code);
	}
	
	protected static ArrayList<Food>showMenu()
	{	
		return activeRestaurant.menu.ShowMenu(activeRestaurant.getRestaurantId());
	}
	
	protected static ArrayList<Food>showRestMenu(int RestaurantId)
	{
		ArrayList<String> foods = DatabaseAdapter.getMenu(RestaurantId);
		ArrayList<Food> foodList = new ArrayList<Food>();
		
		if (foods.size() == 0)
		{		
			return null;
		}

		for (int index = 0;index < foods.size();index++)
		{
			String[]food = foods.get(index).split(",");
			System.out.println(food.length);
			//String foodName, double price, String food_details, String foodImage, String foodCategory
			foodList.add(new Food(food[0],Double.parseDouble(food[1]),food[2],food[3],food[4],false));
		}
		return foodList;
	}
	
	protected static void Logout()
	{
		loggedInCustomer = null;
		activeRestaurant = null;
	}
	
	

}
