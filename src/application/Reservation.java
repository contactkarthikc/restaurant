package application;
import java.sql.Time;
import java.util.Vector;
import java.util.Random;

import Utils.DatabaseAdapter;

//TODO make
public class Reservation {

  private int customerId;
  
  private int restaurantId;

  private int tableNum;

  private String startTime;
  
  public String reservationCode;

  public Reservation(int restaurantId, String time, int tableNum,int customerId) {
	  this.restaurantId = restaurantId;
	  this.startTime = time;
	  this.tableNum = tableNum;
	  this.customerId = customerId;
	 
	  
	  CodeGenerator c = new CodeGenerator();
	  reservationCode = c.generateString(10);
	  //(Random rng, String characters, int length)
	  
	  //This part is used for protecting the uniqueness of the reservationcode.It tries again
	  //for and unique code
	  boolean codeUniquness = DatabaseAdapter.createReservation(restaurantId, time, tableNum, reservationCode,customerId);
	  
	  while (!codeUniquness)
	  {
		  reservationCode = c.generateString(10);
		  codeUniquness = DatabaseAdapter.createReservation(restaurantId, time, tableNum, reservationCode,customerId);
	  }
	  
  }
  
  
  protected Reservation(int restaurantId, String time, int tableNum,int customerId,String resCode)
  {
	  this.restaurantId = restaurantId;
	  this.startTime = time;
	  this.tableNum = tableNum;
	  this.customerId = customerId;
	  this.reservationCode = resCode;
  }

  //This result string be used by the restaurant GUI to show the reservation Info, we dont need to return an object
  //because the given string will be separated and given to main controller to be deleted from the database 
  public String toString() {
	  return DatabaseAdapter.getRestaurantName(restaurantId) + " - " + startTime + " - " + tableNum + " - " + reservationCode;
  }
  
/*
  private int getRestaurant() {
	  return restaurantId;
  }
*/
  protected String getTime() {
	  return startTime;
  }

  protected int getTableNumber() {
	  return tableNum;
  }

  protected String getReservationCode() {
	  
	  return reservationCode;
  }

}