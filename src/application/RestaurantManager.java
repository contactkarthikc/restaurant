package application;

import java.util.ArrayList;

import Utils.DatabaseAdapter;

public class RestaurantManager{

	protected static Restaurant login(int restaurantID,String password)
	{
		boolean success = DatabaseAdapter.checkRestaurant(restaurantID,password);
	  
		if (success)
		{
			ArrayList<String> restaurant = DatabaseAdapter.getRestaurantParameters(restaurantID);
		  
			Restaurant add = new Restaurant(restaurant.get(0),restaurant.get(1),restaurant.get(2),restaurant.get(3),restaurant.get(4),restaurant.get(5),restaurant.get(6), true);
			add.setType(restaurant.get(7));
			return add;
		}
		return null;
	}
  

}