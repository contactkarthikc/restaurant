package application;

import java.util.*;

import Utils.DatabaseAdapter;


class Table {

	private int table_id;

	private int restaurantId;

	private String code ="";

	private int occupation;

	protected Table(int table_id,int restaurantId) {
		this.table_id = table_id;
		this.restaurantId = restaurantId;
		setRandomCode();
		int occupation = 0;
		DatabaseAdapter.createTable(table_id,code,restaurantId);
	}

	protected String getCode() {
		return code;
	}

	protected void setRandomCode() {
		CodeGenerator cg = new CodeGenerator();
		String s = CodeGenerator.generateString(cg.length );

		boolean correctCode= DatabaseAdapter.searchTable(restaurantId,s);

		while(correctCode)
		{
			this.code = s;
			DatabaseAdapter.setTableCode(restaurantId,table_id,code); 
		}

	}

	protected int getTableNumber() { 
		return table_id;
	}

	//1 as occupied
	//0 as empty
	//-1 as paid
	//This will be used for refreshing the code of the table
	protected void changeOccupation(int occupation)
	{
		this.occupation = occupation;
	}

	protected int getOccupation()
	{
		return occupation;
	}

	//Refreshes the table properties
	protected void update()
	{
		occupation = DatabaseAdapter.getOccupation(table_id,restaurantId);
		if(occupation == -1)
		{
			setRandomCode();
			changeOccupation(0);
			DatabaseAdapter.changeTableOccupy(restaurantId, this.code,0);
		}

	}

}