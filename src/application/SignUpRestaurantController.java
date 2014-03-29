package application;

//import java.awt.TextArea;

import javax.swing.JComboBox;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
public class SignUpRestaurantController 
{

	@FXML
	public TextField SignUpRestaurantNameTextField;
	public TextField SignUpRestaurantTelephoneTextField;
	public TextField SignUpRestaurantIbanTextField;
	public TextArea SignUpRestaurantLocationTextArea;
	public TextField SignUpRestaurantConfirmTextField;
	public PasswordField SignUpRestaurantPasswordTextField;
	public PasswordField SignUpRestaurantRePasswordTextField;
	public ComboBox<String> openCombox;
	public ComboBox<String> closeCombox;
	
	
	public String restaurantName;
	public String restaurantTelephone;
	public String restaurantIban;
	public String restaurantLocation;
	public String restaurantConfirmCode;
	public int restaurantFinalConfirmCode;
	public String openTime = "0";
	public String closeTime = "0";
	public String password;
	public String rePassword;
	
	public void SignUpRestaurantNameController()
	{
		restaurantName = (SignUpRestaurantNameTextField.getText());
	}
	
	public void SignUpRestaurantTelephoneController()
	{
		restaurantTelephone = (SignUpRestaurantTelephoneTextField.getText());
	}
	
	public void SignUpRestaurantIbanController()
	{
		restaurantIban = (SignUpRestaurantIbanTextField.getText());
	}
	
	public void SignUpRestaurantLocationController()
	{	
		restaurantLocation = (SignUpRestaurantLocationTextArea.getText());
	}
	
	public void SignUpRestaurantConfirmController()
	{
		restaurantConfirmCode = (SignUpRestaurantConfirmTextField.getText());
		restaurantFinalConfirmCode = Integer.parseInt(restaurantConfirmCode);
	}
	
	public void SignUpRestaurantOpenTimeController()
	{
		openTime = (String) openCombox.getSelectionModel().getSelectedItem().toString(); 
	}
	
	public void SignUpRestaurantCloseTimeController()
	{
		closeTime = (String) closeCombox.getSelectionModel().getSelectedItem().toString(); 		
	}
	
	public void SignUpRestaurantSignUpButtonController()
	{
		if (!restaurantName.equals("") && !restaurantTelephone.equals("") && !restaurantIban.equals("") && !restaurantLocation.equals("") && 
				!openTime.equals("") && !closeTime.equals("") && !password.equals("") && !rePassword.equals(""))
		{
			if(password.equals(rePassword))
			{
				if(MainController.signUpRestaurant(restaurantName,restaurantLocation,openTime,closeTime, restaurantTelephone,restaurantIban,restaurantFinalConfirmCode,password))
				{
					Main.stages.get(0).setScene(Main.scenes.get(9));
					Main.stages.get(0).show();
				}		
				else
				{
					
					Main.stages.get(2).setScene(Main.scenes.get(8));
					Main.stages.get(2).show();
				}	
			}	
			else
			{
				Main.stages.get(2).setScene(Main.scenes.get(8));
				Main.stages.get(2).show();
			}	
		}
		else
		{
			Main.stages.get(2).setScene(Main.scenes.get(8));
			Main.stages.get(2).show();
		} 
	}
	
	public void SignUpRestaurantBackButtonController()
	{
		Main.stages.get(0).setScene(Main.scenes.get(3));
		Main.stages.get(0).show();
	}
	
	public void SignUpRestaurantPasswordController()
	{
		password = SignUpRestaurantPasswordTextField.getText();
	}
	
	public void SignUpRestaurantRePasswordController()
	{
		rePassword = SignUpRestaurantRePasswordTextField.getText();
	}
}
