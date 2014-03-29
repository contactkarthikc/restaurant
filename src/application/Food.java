package application;

import java.awt.Image;
import java.util.*;

import Utils.DatabaseAdapter;

public class Food {

	//private ArrayList<Comment> foodComments;	

	private String foodName;

	private double price;

	private String food_details;

	private String foodImage; 

	private String foodCategory;

	private int quantity;

	protected int foodId;

	private String note;


	protected Food(String foodName, double price, String food_details, String foodImage, String foodCategory,boolean newAdded) {
		this.foodName = foodName;
		this.price = price;
		this.food_details = food_details;
		this.foodImage = foodImage;
		this.foodCategory = foodCategory;
		this.quantity = 0;
		this.note = "";

		if (newAdded)
		{
			foodId = DatabaseAdapter.createFood(foodName, price, food_details, foodImage);
		}
	}	  

	protected void SetImage(String path) {
		foodImage = path;
	}

	protected void ManageInfo(String info) {
		this.food_details = info;
		DatabaseAdapter.modifyFoodInfo(foodId,this.food_details);
	}

	protected String getCategory() {
		return foodCategory;
	}

	public String getName() {
		return foodName;
	}

	protected void setName(String name) {
		this.foodName=name;
		DatabaseAdapter.modifyFoodName(foodId,this.foodName);
	}

	public double getPrice() {
		return price;
	}
	protected void setPrice(double price) {
		this.price = price;
		DatabaseAdapter.modifyFoodPrice(foodId,this.price);
	}

	protected void setPortion(int portion){
		this.quantity = portion;
	}

	protected int getPortion(){
		return quantity;
	}

	public String getNote() {
		return note;
	}
	
	protected void setNote(String note)
	{
		this.note = note;
	}
	
	protected int getId()
	{
		return foodId;
	}	

}