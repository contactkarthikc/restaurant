package application;
import java.sql.SQLException;
import java.util.*;

import Utils.DatabaseAdapter;
import javafx.scene.chart.PieChart.Data;

public class  Customer  {

	private String email;

	private String pass;

	private String username;

	private String creditCardNumber;

	protected static int customer_id;
	
	protected static OrderManager orderManager;
	
	protected static ReservationManager reservationManager;
	

	//The constructor with creditCard number
	protected Customer(String name, String email, String pass, String creditCarNum,boolean login) {
		this.email = email;
		this.creditCardNumber = creditCarNum;
		this.pass = pass;
		this.username =name;
		

		//Enter these attributes to SQL database to create a new customer
		if (!login)
		{
			DatabaseAdapter.createCustomer(name,email,pass,"");
		} 
		
		//Database get All about order by giving customer_id
		//put static parameters as proxy here
		
		int id = DatabaseAdapter.getCustomerId(email);
		
		Customer.customer_id = id;
		
		reservationManager = new ReservationManager(customer_id);
		
		orderManager = new OrderManager(customer_id);
		
	}

	//Constructor without credit card number
	protected Customer(String name, String email, String pass,boolean login) {
		this.email = email;
		this.creditCardNumber = "";
		this.pass = pass;
		this.username =name;
		
		//Enter these attributes to SQL database to create a new customer 
		if (!login)
		{
			DatabaseAdapter.createCustomer(name,email,pass,"");
		}
		
		int id = DatabaseAdapter.getCustomerId(email);
		
		Customer.customer_id = id;
		
		reservationManager = new ReservationManager(customer_id);
		
		orderManager = new OrderManager(customer_id);

	}

	//Logs into the database and returns the information for constructing
	protected static String login(String email, String pass) {
		
		boolean success = DatabaseAdapter.checkLoginInfo(email,pass);

		String result = "";
		
		if (success)
		{
			result = DatabaseAdapter.getUsername(email) + "," + DatabaseAdapter.getCardNumber(email);
			
			int id = DatabaseAdapter.getCustomerId(email);
			
			customer_id = id;
			
			reservationManager = new ReservationManager(customer_id);
			
			orderManager = new OrderManager(customer_id);
			
		}
		
		return result;

	}

	//Updates the Customer info on database
	protected void manageAccount (String newName, String newPass, String newEmail, String creditCarNum) {
		if (creditCarNum != null && !creditCarNum.equals(""))
			this.creditCardNumber = creditCarNum;
		if (newPass != null && !newPass.equals(""))
			this.pass = newPass;
		if (newName != null && !newName.equals(""))
			this.username = newName;
		
		DatabaseAdapter.modifyCustomer(customer_id, this.email,this.pass,this.username,this.creditCardNumber);
	}
	
	protected void addFavoritesList(int restaurantId) throws SQLException
	{
		DatabaseAdapter.addToFavorites(email,restaurantId);
	}
	
	protected ArrayList<String> getFavoritesList()
	{
		return DatabaseAdapter.getFavorites(email);
	}
	
	protected void deleteFromFavorites(int restaurantId)
	{
		DatabaseAdapter.deleteFavorite(email,restaurantId);
	}
	
	//This will change
	protected String getCurrentOrderInfo()
	{
		String result = orderManager.currentOrder.getRestaurant() + ":\n";
		ArrayList <Food> foods = orderManager.currentOrder.getFoodList();
		
		for (int foodIndex = 0;foodIndex < foods.size();foodIndex++)
		{
			result = result + foods.get(foodIndex).getName() + "-" + foods.get(foodIndex).getPortion() + "\n";
		}
		
		return result;
		
	}

}

