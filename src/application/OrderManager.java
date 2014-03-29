package application;
import java.awt.Image;
import java.util.*;

import Utils.DatabaseAdapter;

public class OrderManager{

	static Order currentOrder;

	private int customerId;


	//Initializes the Order Manager
	public OrderManager(int customerId){
		currentOrder = new Order(-1,null,"",-1,"");
		this.customerId = customerId;
		updateOrder();
	}


	//Updates the orderList by deleting all foods and replacing the updated ones
	private void updateOrder() {
		if (currentOrder != null)
			currentOrder.deleteAllFood();

		ArrayList<String> foods = DatabaseAdapter.getCustomerOrderFood(customerId);

		for (int index = 0;index < foods.size();index++)
		{
			System.out.println(foods.get(index));
			String[]food = foods.get(index).split(",");
			//String foodName, double price, String food_details, String foodImage, String foodCategory
			currentOrder.addFood(new Food(food[0],Double.parseDouble(food[1]),food[2],food[3],food[4],false));
		}
		
	}

	//Creates the order and puts it to the current given order
	public boolean addOrder(int restaurantId, ArrayList<Food> food, String tableCode) {

		//Returns false if the table is full or tableCode does'not exists
		boolean correctCode= DatabaseAdapter.searchTable(restaurantId,tableCode);

		if (correctCode)
		{
			String time = Integer.toString(Calendar.HOUR_OF_DAY);
			int orderID = DatabaseAdapter.addOrder(restaurantId,customerId,tableCode,time);
			currentOrder = new Order(restaurantId, food,tableCode,orderID,time);
			DatabaseAdapter.changeTableOccupy(restaurantId,tableCode,1);

			for (int index = 0;index < food.size();index++)
			{
				//(String foodName,int quantity)
				DatabaseAdapter.addOrderFood(food.get(index).getName(),food.get(index).getPortion(),food.get(index).getNote(), restaurantId,customerId);
				DatabaseAdapter.modifyOrderNote(food.get(index).getNote(),orderID,food.get(index).getId());
			}

			return true;
		}
		return false;

	}

	//Deletes the order form the database and proxy object
	public void cancelOrder() {

		//This also deletes all the food that are connected to it
		DatabaseAdapter.cancelOrder(customerId);

		currentOrder = null;
	}

	//Later
	public void payOrder() {

		//This also deletes all the food that are connected to it
		DatabaseAdapter.cancelOrder(customerId);

		DatabaseAdapter.changeTableOccupy(currentOrder.getRestaurant(),currentOrder.getCode(),-1);

		currentOrder = null;
	}

	//Not implemented yet
	public void callWaiter(String note) {
	}

	//Adds food to the old order 
	public void addFood(Food food,int restaurantId)
	{
		currentOrder.addFood(food);
		//The customer id can be used for the food of the order
		DatabaseAdapter.addOrderFood(food.getName(),food.getPortion(),food.getNote(), restaurantId,customerId);		
	}

	//Remove food to the old order 
	public void deleteFood(Food food,int restaurantId)
	{
		currentOrder.deleteFood(food.getName());
		DatabaseAdapter.removeOrderFood(restaurantId,customerId,food.getName());
	}

	//Returns the arrayList for the order foods,null if order contains no food
	public ArrayList<Food> displayOrder()
	{
		ArrayList<String> foods = DatabaseAdapter.getCustomerOrderFood(customerId);
		ArrayList<Food> foodList = new ArrayList<Food>();

		if (foods.size() == 0)
		{
			return null;
		}
		for (int index = 0;index < foods.size();index++)
		{
			String[]food = foods.get(index).split(",");
			//String foodName, double price, String food_details, String foodImage, String foodCategory
			foodList.add(new Food(food[0],Double.parseDouble(food[1]),food[2],food[3],food[4],false));
		}
		return foodList;
	}
	
	


}