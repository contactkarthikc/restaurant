package application;

import java.util.*;

import Utils.DatabaseAdapter;

class ReservationManager{
  
  public int table;
  
  int customerId;

  private static ArrayList<Reservation> ReservationList; 
	
  public ReservationManager(int customerId){
	  ReservationList = new ArrayList<Reservation>();
	  this.customerId = customerId;
	  updateReservationList();
  }
  
  //Creates a new reservation in both database and reservationList
  protected String createReservation(int restaurantId, String time) {
	  int tableNum = DatabaseAdapter.searchForEmptyTable(restaurantId);
	  
	  //if the searchForEmptyTable returns -1, display error message.
	  if(tableNum!=-1){
	  
		  System.out.println("Here?");
		  Reservation r = new Reservation(restaurantId, time, tableNum,customerId);
		  
		  ReservationList.add(r); 
		  
		  return r.reservationCode;
	  }
	  
	  //Empty if there are  no reservations
	  return "";
	  
  }

  //Cancels the reservation with the given reservationCode
 public void cancelReservation(String resCode) {
	 
	 DatabaseAdapter.reservationCancelling(resCode);
	 
	 for(int i = 1; i<=ReservationList.size(); i++){
		  if(ReservationList.get(i).reservationCode == resCode)
			  ReservationList.remove(i);
	  }
  }

 //Returns all the reservations of the customer as objects
 public ArrayList<Reservation> getReservations()
 {
	 updateReservationList();
	 return ReservationList;
 }
 
 //Gets all the reservation info from the database and puts them in the reservationList parameter of ReservationManager
 private void updateReservationList()
 {
	 ArrayList<String> reservations = new ArrayList<String>();
	 
	 if (ReservationList != null)
		 ReservationList.clear();
	 
	 reservations = DatabaseAdapter.getCustomerReservations(customerId);
	 
	 for (int index = 0;index < reservations.size();index++)
	 {
		 String[]result = reservations.get(index).split(",");
		 ReservationList.add(new Reservation(Integer.parseInt(result[0]),result[1],Integer.parseInt(result[2]),Integer.parseInt(result[3]),result[4]));
		 
	 }

	 
 }
  

}