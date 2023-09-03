package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RegistrationInterface {
	@FXML
	private TextField cfField;
	@FXML
	private PasswordField pswField;
	@FXML
	private PasswordField confpswField;
	@FXML
	private Button Registration;
	
	private ElettoreDAO eDAO;
	
	@FXML
	public void effetuaRegistrazione(ActionEvent e) {
		String cf = cfField.getText();
		String psw = pswField.getText();
		String confpsw = confpswField.getText();
		
		if(cf.isBlank() || psw.isBlank() || psw.isBlank()) {
			Alert a = new Alert(AlertType.ERROR);
    		a.setContentText("Compila tutti i campi riguardanti i dati dell'abbonamento.");
    		a.show();
    		return;
		}
		if(!(psw.equals(confpsw))) {
			Alert a = new Alert(AlertType.ERROR);
    		a.setContentText("Le due password devono coincidere.");
    		a.show();
    		return;
		}
		if(!(eDAO.checkCf(cf))) {
			Alert a = new Alert(AlertType.ERROR);
    		a.setContentText("Questo utente risulta già registrato.");
    		a.show();
    		return;
		}
		try {
			eDAO.effettuaRegistrazione(cf, psw);
	    	Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText("Hai creato il tuo account.");
			a.show();
	    	
		} catch (Exception ex) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setContentText(ex.getMessage());
    		a.show();
    		return;
		}
		
    	try {
			Parent mainChoiceParent = FXMLLoader.load(getClass().getResource("LoginOK.fxml"));
			Scene scene = new Scene(mainChoiceParent);
			Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
