package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class LoginScreenController implements Initializable
{
	@FXML
	private TextField TextFieldFx;

	@FXML
	private PasswordField PassFieldFx;


	@FXML
	//Works
	public void LogInButtonController()
	{
		String password = (PassFieldFx.getText());
		String email = (TextFieldFx.getText());

		if (password.equals("") || email.equals("") )
		{
			Main.stages.get(1).setScene(Main.scenes.get(8));
			Main.stages.get(1).show();
		}

		else if(MainController.loginCustomer(email,password) == true)
		{
			Main.stages.get(0).setScene(Main.scenes.get(1));
			Main.stages.get(0).show();
		}
		else{ 
			try { 
				if(MainController.loginRestaurant(Integer.parseInt(email),password)){
					Main.stages.get(0).setScene(Main.scenes.get(9));
					Main.stages.get(0).show();}
				else
				{
					Main.stages.get(1).setScene(Main.scenes.get(8));
					Main.stages.get(1).show();
				}
			} catch(NumberFormatException e) {
				Main.stages.get(1).setScene(Main.scenes.get(8));
				Main.stages.get(1).show();
			}
		}
		
	}

	@FXML
	public void ForgotButtonController()
	{
		Main.stages.get(1).setScene(Main.scenes.get(11));
		Main.stages.get(1).show();
		
	}

	@FXML
	public void SignUpButtonController()
	{
		Main.stages.get(0).setScene(Main.scenes.get(3));
		Main.stages.get(0).show();
	}

	@FXML
	public void TextFieldController()
	{

	}

	@FXML
	public void PasswordFieldController()
	{

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
