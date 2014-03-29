package application;

import java.util.ArrayList;

import Utils.DatabaseAdapter;

public class TableManager {

	private int restaurantId;
	private ArrayList <Table> allTables;

	protected TableManager(int restaurantId)
	{
		allTables = new ArrayList<Table>();
		this.restaurantId = restaurantId;
	}
	
	protected void addTable(int tableId) {
		allTables.add(new Table(tableId,restaurantId));
	}

	protected boolean deleteTable(int tableId) {
		
		for(int i = 0; i<=allTables.size(); i++){
			if(allTables.get(i).getTableNumber() == tableId && allTables.get(i).getOccupation() == 0)
			{
				allTables.remove(i);
				
				DatabaseAdapter.deleteTable(tableId,restaurantId);
				
				return true;
			}
		}
		return false;
		
	}
	
	//If returns null system prints no table
	protected ArrayList<String> displayTables()
	{
		ArrayList<String>tables = new ArrayList<String>();
		String table = "";

		
		for (int tableIndex = 0;tableIndex < allTables.size();tableIndex++)
		{
			allTables.get(tableIndex).update();
			table = allTables.get(tableIndex).getTableNumber() + " : "  + allTables.get(tableIndex).getCode() + " - ";
			if (allTables.get(tableIndex).getOccupation() == 1)
			{
				table += "occupied";
			}
			else
				table += "empty";
			tables.add(table);
		}
		
		return tables;
		
	}
	
	protected void changeTableOccupation(int tableNo)
	{
		String tableCode = "";
		int occupy = 0;
		int tableIndex = 0;
		
		for (int index = 0;index < allTables.size();index++)
		{
			if (allTables.get(index).getTableNumber() == tableNo){
				occupy = allTables.get(index).getOccupation();
				tableCode= allTables.get(index).getCode();
				tableIndex = index;
				break;}
		}
		
		if (occupy == 1 && !tableCode.equals(""))
			DatabaseAdapter.changeTableOccupy(restaurantId, tableCode, 0);
		else if (!tableCode.equals(""))
			DatabaseAdapter.changeTableOccupy(restaurantId, tableCode, 1);
		
		allTables.get(tableIndex).update();
	}

}