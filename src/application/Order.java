package application;
import java.util.*;

public class Order{

  private int restaurantId;

  private static ArrayList<Food> foods; 

  private String code; 

  private double price;

  private String time;

  protected boolean paid = false;

  private int orderId;
  
  public Order(int restaurantId, ArrayList<Food> foods,String tableCode,int orderID,String time) { 
	  this.restaurantId = restaurantId;
	 
	  if (foods == null)
	  {
		  this.foods = new ArrayList<Food>();
	  }
	  else
		  this.foods = foods; 
	  orderId = orderID;
	  
	  code = tableCode;
	  paid = false;
	  this.time = time;
	  
  }

  protected double getPrice() {
	  double totalPrice = 0.0;
	  for (int index = 0;index < foods.size();index++)
	  {
		  totalPrice = foods.get(index).getPrice();
	  }
	  
	  return totalPrice;
  }

  protected ArrayList<Food> getFoodList() {
	  return foods;
  }

  protected void deleteFood(String foodName) {
	  for(int i=1; i<=foods.size();i++){
		  if(foods.get(i).getName() == foodName)
			  foods.remove(i);
	  }
  }
  
  protected void addFood(Food food) {
	  foods.add(food);
  }
  
  protected void deleteAllFood()
  {
	  if (foods != null)
		  foods.clear();
  }

  protected int getRestaurant() {
	return restaurantId;
  }
  /*
  protected void setPaidSituation()
  {
	  paid = true;
	  DatabaseAdapter.changePayment(code);
  }
  
  protected boolean getPaidSituation()
  {
	  return paid;
  }
  */
  protected String getCode()
  {
	  return code;
  }
  
  protected int getPortion(Food food)
  {
	  return food.getPortion();
  }
}

