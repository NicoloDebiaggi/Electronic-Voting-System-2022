package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class userChoiceController extends Controller {
	@FXML
	private Button adminLogin;
	@FXML
	private Button userLogin;
	@FXML
	private Button userReg;
	
	@FXML
	public void goToUserLogin(ActionEvent event) {
		try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginInterface.fxml"));
			Parent mainChoiceParent = loader.load();
			Scene scene = new Scene(mainChoiceParent);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
    public void goToAdminLogin(ActionEvent event) {
		try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginInterfaceAdmin.fxml"));
			Parent mainChoiceParent = loader.load();
			Scene scene = new Scene(mainChoiceParent);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void goToUserReg(ActionEvent event) {
		try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrationInterface.fxml"));
			Parent mainChoiceParent = loader.load();
			Scene scene = new Scene(mainChoiceParent);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

