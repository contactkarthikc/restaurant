package application;

import java.sql.Time;

import Utils.DatabaseAdapter;


//TODO make
public class Restaurant {

	protected String name;

	protected String location;

	protected String openTime;

	protected String closedTime;

	protected String telephone;

	protected String bankNumber;

	protected String type = "";

	protected int restaurantId;

	protected FoodList menu;

	protected String url = "";

	protected static TableManager tableManager;

	protected String password;

	protected static OrderList orderList;

	protected ReservationList reservationList;

	protected static FoodList foodList;
	
	protected Restaurant(String name, String location, String openTime, String closedTime, String telephone,String bankNumber,String password, boolean login) {
		this.name = name;
		this.location = location;
		this.openTime = openTime;
		this.closedTime = closedTime;
		this.telephone = telephone;
		this.bankNumber = bankNumber;
		this.password = password;

		if (!login)
		{	
			//String password, String name, String location, String openTime, String closeTime, String telephone, String bankNumber, String url
			DatabaseAdapter.createRestaurant(password,name,location,openTime,closedTime,telephone,bankNumber,url);

		}

		int id = DatabaseAdapter.getRestaurantId(telephone);
		this.restaurantId = id;
		
		reservationList = new ReservationList();
		foodList = new FoodList();
		tableManager = new TableManager(restaurantId);
		
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	protected String getOpenTime() {
		return openTime;
	}

	protected String getClosedTime() {
		return closedTime;
	}

	protected String getTelephone() {
		return telephone;
	}
	protected int getRestaurantId() {
		return restaurantId;
	}
	protected String getBankNumber() {
		return bankNumber;
	}

	protected void setName(String name) {
		this.name = name;
		DatabaseAdapter.modifyRestaurantName(restaurantId,this.name);
	}

	protected void setLocation(String location) {
		this.location = location;
		DatabaseAdapter.modifyRestaurantLocation(restaurantId,this.location);
	}

	protected void setOpenTime(String open) {
		this.openTime = open;
		DatabaseAdapter.modifyRestaurantOpenTime(restaurantId,this.openTime);
	}

	protected void setClosedTime(String close) {
		this.closedTime = close;
		DatabaseAdapter.modifyRestaurantCloseTime(restaurantId,this.closedTime);
	}

	protected void setTelephone(String tel) {
		this.telephone = tel;
		System.out.println("Tel");
		DatabaseAdapter.modifyRestaurantTelephone(restaurantId,this.telephone);
	}


	protected void setBankNumber(String bankAc) {
		this.bankNumber = bankAc;
		DatabaseAdapter.modifyRestaurantBankAccount(restaurantId,this.bankNumber);
	}

	protected void setType(String type) {
		this.type = type;
		DatabaseAdapter.modifyRestaurantType(restaurantId,this.type);
	}

	protected void setPicture(String url) {
		this.url = url;
		DatabaseAdapter.modifyRestaurantPicture(restaurantId,this.url);
	}

	

}