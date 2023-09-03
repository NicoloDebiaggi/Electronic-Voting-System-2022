package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import application.Controller;

public class App extends Application {
	private static Scene scene;

    private static String Cf;
   
    public static String getCf() {
        return Cf;
    }

    public static void setCf(String Cf) {
        App.Cf = Cf;
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        DbManager db = DbManager.getInstance();

        db.ensureCreated();

        scene = new Scene(loadView("userChoice"), 600, 400);
        navigate(null, "userChoice");
        stage.setScene(scene);
        stage.setTitle("Voto Elettronico");
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.show();
    }
    
    public static Parent loadView(String view) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("views/" + view + ".fxml"));
        return loader.load();
    }

    public static Parent loadView(Controller sender, String view) throws IOException {
        return loadView(sender, view, null);
    }

    public static Parent loadView(Controller sender, String view, Object parameter) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("views/" + view + ".fxml"));
        Parent parent = loader.load();
        Controller controller = loader.getController();
        controller.onNavigateFrom(sender, parameter);
        controller.init();
        return parent;
    }
    
    public static void navigate(Controller sender, String view, Object parameter) throws IOException {
        var parent = loadView(sender, view, parameter);
        scene.setRoot(parent);
    }

    public static void navigate(Controller sender, String view) throws IOException {
        navigate(sender, view, null);
    }

    public static void main(String[] args) {
        launch();
    }
}
