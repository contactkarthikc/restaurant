package application;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class SignUpMainController implements Initializable
{
	@FXML
	public void SignUpRestaurantController()
	{
		Main.stages.get(0).setScene(Main.scenes.get(5));
		Main.stages.get(0).show();
	}
	@FXML
	public void SignUpCustomerController()
	{
		Main.stages.get(0).setScene(Main.scenes.get(4));
		Main.stages.get(0).show();
	}
	@FXML
	public void SignUpMainCustomerBackButtonController()
	{
		Main.stages.get(0).setScene(Main.scenes.get(0));
		Main.stages.get(0).show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
