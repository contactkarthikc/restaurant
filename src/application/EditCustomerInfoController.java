package application;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class EditCustomerInfoController
{
	@FXML
	public TextField editNameTextField;
	public PasswordField editNewPasswordTextField;
	public TextField editNewCrediCardNumberTextField;
	
	public String name = "";
	public String pass = "";
	public String credit = "";

	public void SubmitButtonController()
	{
		MainController.manageAccount(name,pass,"",credit);
		
		Main.stages.get(1).close();
	}
	
	public void EditCustomerPassController()
	{
		pass = (editNewPasswordTextField.getText());
	}
	
	public void EditCustomerNameController()
	{
		name = (editNameTextField.getText());
	}
	
	public void EditCustomerCreditController()
	{
		credit = (editNewCrediCardNumberTextField.getText());
	}
}
