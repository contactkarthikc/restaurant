package application;

import javafx.scene.control.ComboBox;

public class ReservationScreenController 
{
	public String month;
	public String date;
	public String hour;
	public String code;
	public static int id;
	public ComboBox<String> hourSel;
	public ComboBox<String> monthSel;
	public ComboBox<String> dateSel;
	ReservationSuccessController control;
	


	public void ReservationButton()
	{
		String time = hour +"-" + date +"-"+ month;
		code = MainController.makeReservation(id, time);
		control.setCode(code);
		if (code != ""){
			Main.stages.get(2).setScene(Main.scenes.get(7));
			Main.stages.get(2).show();}
	}

	public void SetHourController()
	{
		hour = (String) hourSel.getSelectionModel().getSelectedItem().toString();
	}

	public void SetMonthController()
	{
		month = (String) monthSel.getSelectionModel().getSelectedItem().toString();
	}

	public void SetDateController()
	{
		date = (String) dateSel.getSelectionModel().getSelectedItem().toString();
	}

	public static void setId(int NewId) {
		id = NewId;

	}


}
