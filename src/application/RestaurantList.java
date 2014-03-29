package application;

import java.util.ArrayList;

import Utils.DatabaseAdapter;



//Restaurant List is used to search the database for restaurants which satisfy the given parameters. If no restaurant is found
//It returns null, else it creates a proxy restaurant instance for GUI by constructing and adding addtional attributes. It then
//returns an ArrayList of Restaurants 
class RestaurantList {

	
  protected static ArrayList<Restaurant> filterRestaurantList(String Location,String type, String name)
  {
		ArrayList<String> restaurantList = new ArrayList();
		ArrayList<Restaurant> restaurants = new ArrayList();
		
		Restaurant restaurantToBeAdded;
		//Find number of all restaurants with the given parameters
		
		//TODOGet restaurants must return restaurantID too
		restaurantList = DatabaseAdapter.getRestaurants(Location,type,name);
		
		if (restaurantList.size() == 0)
			return null;
		
		//The fetched information is used for restaurant Creation
		for (int i = 0;i < restaurantList.size();i++)
		{
			String[] info = restaurantList.get(i).split(",");
		
			restaurantToBeAdded = new Restaurant(info[0],info[1],info[2],info[3],info[4],"", "", true);
			restaurantToBeAdded.setType(info[5]);
			if (info.length == 7)
				restaurantToBeAdded.setPicture(info[6]);
			
			restaurants.add(restaurantToBeAdded);
		}
		
		return restaurants;
  }
  
}
