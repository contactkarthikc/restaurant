package application;
import java.util.*;

import Utils.DatabaseAdapter;

public class FoodList{

	static ArrayList<Food> foodList = new ArrayList<Food>();
	private int restaurantId;

	//Adds the described food
	protected void addFoodToMenu(String food_name, double price, String food_details, String foodImage, String foodCategory, boolean newlyAdded) {
		foodList.add(new Food(food_name,price,food_details,foodImage, foodCategory, true));

	}

	//Deletes the food with given name
	protected void deleteFoodFromMenu(int foodId) {
		for(int i = 0; i<=foodList.size()-1; i++){
			if(foodList.get(i).foodId == foodId)
				foodList.remove(i);
		}

		DatabaseAdapter.deleteFood(foodId);
	}


	//Returns the food to be displayed
	protected static ArrayList<Food> ShowMenu(int restaurantId) {

		ArrayList<String> foods = DatabaseAdapter.getMenu(restaurantId);
		if (foodList != null)
			foodList.clear();


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

	//Changes the attributes of the food in the menu
	protected void ManageFood(int foodId,String foodName,String info,double price,String note)
	{
		for (int index = 0;index < foodList.size();index++)
		{
			if (foodList.get(index).getId()==foodId)
			{
				if (foodName != "")
				{
					foodList.get(index).setName(foodName);
				}
				if (info != "")
				{
					foodList.get(index).ManageInfo(info);
				}
				if (price != 0.0)
				{
					foodList.get(index).setPrice(price);
				}
				if (note != "")
				{
					foodList.get(index).setName(note);
				}
				
				break;
			}
		}
	}



}