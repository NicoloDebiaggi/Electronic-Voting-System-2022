package application;

import java.io.IOException;
import java.util.NoSuchElementException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginInterfaceAdminController {
	@FXML
	private TextField cfField;
	@FXML
	private PasswordField pswField;
	@FXML
	private Button login;
	
	public void effettuaLogin(ActionEvent event) {
		AdminDAO aDAO = new AdminDAO();
		Admin ad =null;
		String cf = cfField.getText();
		String psw = pswField.getText();
		if (cf.isBlank() || psw.isBlank()) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setContentText("Inserire il codice utente e la password.");
    		a.show();
    		return;
    	}
		try { ad = aDAO.effettuaLogin(cf, psw); }
    	catch (IllegalStateException er) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setContentText(er.getMessage());
    		a.show();
    		return;
    	} catch (NoSuchElementException er) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setContentText(er.getMessage());
    		a.show();
    		return;
    	}
		
		try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginOK.fxml"));
			Parent mainChoiceParent = loader.load();
			Scene scene = new Scene(mainChoiceParent);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch(IOException er) {
			er.printStackTrace();
		}
		
	}
	
	
}
