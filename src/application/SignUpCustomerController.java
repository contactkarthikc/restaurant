package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class SignUpCustomerController implements Initializable
{
	@FXML
	public TextField SignUpCustomerNameTextField;
	public TextField SignUpCustomerEmailTextField;
	public PasswordField SignUpCustomerPasswordField;
	public PasswordField SignUpCustomerRePasswordField;
	public TextField SignUpCustomerCardNumberTextField;

	public String name;
	public String email;
	public String password;
	public String rePassword;
	public String cardNumber;


	@FXML
	public void SignUpCustomerNameController()
	{
		name = (SignUpCustomerNameTextField.getText());
		
	}

	@FXML
	public void SignUpCustomerEmailController()
	{
		email = (SignUpCustomerEmailTextField.getText());
		
	}

	@FXML
	public void SignUpCustomerPasswordController()
	{
		password = (SignUpCustomerPasswordField.getText());
	}

	@FXML
	public void SignUpCustomerRePasswordController()
	{
		rePassword = (SignUpCustomerRePasswordField.getText());
	}

	@FXML
	public void SignUpCustomerCardNumberController()
	{
		cardNumber = (SignUpCustomerCardNumberTextField.getText());
	}

	@FXML
	public void SignUpCustomerButton()
	{
		if (!name.equals("") && !password.equals("") && !rePassword.equals("") && !email.equals(""))
		{
			if(password.equals(rePassword))
			{
				if(MainController.signUpCustomer(name, email, password, cardNumber))
				{
					Main.stages.get(0).setScene(Main.scenes.get(1));
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

	@FXML
	public void SignUpCustomerBackButtonController()
	{
		Main.stages.get(0).setScene(Main.scenes.get(3));
		Main.stages.get(0).show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	} 
}
