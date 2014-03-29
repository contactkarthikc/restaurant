package application;

import java.util.ArrayList;

import Utils.DatabaseAdapter;


//TODO make
public class ReservationList {

	//Gets the reservations as a string arraylist from the database
	protected ArrayList<String> displayReservations(int restaurantID) {

		//As time and table id and reservationCode
		ArrayList<String>reservations = DatabaseAdapter.getRestaurantReservations(restaurantID);

		return reservations;
	}

	//Deletes the reservation from the database
	//by its reservation code
	void eraseReservation(String reserveCode)
	{
		DatabaseAdapter.eraseReservation(reserveCode);
	}
}