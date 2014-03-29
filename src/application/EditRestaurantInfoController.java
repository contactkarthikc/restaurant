package application;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class EditRestaurantInfoController
{
	@FXML
	public TextField RestaurantNameTextField;
	public TextField RestaurantTelephoneTextField;
	public TextField RestaurantIbanTextField;
	public TextField RestaurantTypeTextField;
	public String name = "";
	public String iban = "";
	public String telephone = "";
	public String open = "";
	public String close = "";
	public String type = "";
	public ComboBox<String> openCombox;
	public ComboBox<String> closeCombox;

	public void SubmitButtonController()
	{
		if (telephone != "")
			MainController.activeRestaurant.setTelephone(telephone);
		if (close != "")
			MainController.activeRestaurant.setClosedTime(close);
		if (open != "")
			MainController.activeRestaurant.setOpenTime(open);
		if (type != "")
			MainController.activeRestaurant.setType(type);
		if (iban != "")
			MainController.activeRestaurant.setBankNumber(iban);
		if (name != "")
			MainController.activeRestaurant.setName(name);
		Main.stages.get(1).close();
	}

	public void EditIbanController()
	{
		iban = (RestaurantIbanTextField.getText());
	}

	public void EditRestaurantNameController()
	{
		name = (RestaurantNameTextField.getText());
	}

	public void EditTelepController()
	{
		telephone = (RestaurantTelephoneTextField.getText());
	}

	public void EditTypeController()
	{
		type = (RestaurantTypeTextField.getText());
	}

	public void RestaurantCloseController()
	{
		close = (String) closeCombox.getSelectionModel().getSelectedItem().toString(); 
	}
	
	public void RestaurantOpenController()
	{
		open = (String) openCombox.getSelectionModel().getSelectedItem().toString(); 
	}
}
