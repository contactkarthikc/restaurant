package application;

import java.util.ArrayList;

import Utils.DatabaseAdapter;

class OrderList {

	//Called by the main controller to return the order clicked which has the given table number
	//It only returns the clicked order
	protected ArrayList<String> displayOrder(int restaurantID,int table_num) {

		//Gets the information about the order to the ArrayList of String,
		//Foods and their portions as
		// "FoodName x Portion : If notes"
		//The last string will contain its ordercode
		ArrayList<String>order = DatabaseAdapter.getOrder(restaurantID,table_num);

		return order;
	}

	protected void eraseOrder(int restaurantId,int table_num)
	{
		//This also deletes all the food that are connected to it
		DatabaseAdapter.cancelOrder(restaurantId,table_num);

		String tableCode = DatabaseAdapter.getTableCode(restaurantId,table_num);
		//Changes the tableOccupy situation as not occupied to -1 for its code to be refreshed
		DatabaseAdapter.changeTableOccupy(restaurantId,tableCode,-1);

	}
}