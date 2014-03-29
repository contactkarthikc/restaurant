package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class ForgotPasswordController 
{
	@FXML
	private TextField forgotPasswordTextField;
	
	public void SendNewPasswordController()
	{
		String forgotEmail = (forgotPasswordTextField.getText());
		
		if(MainController.forgotPassword(forgotEmail))
		{
			Main.stages.get(1).setScene(Main.scenes.get(8));
			Main.stages.get(1).show();
		}
	}
	
}
