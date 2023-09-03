package application;

import java.io.IOException;

public abstract class Controller {
	public void init() {

    }

    public void onNavigateFrom(Controller sender, Object parameter) {

    }

    public String getCf() {
        return App.getCf();
    }

    public void setCf(String cf) {
        App.setCf(cf);
    }

    public void navigate(String view, Object parameter) {
        try {
            App.navigate(this, view, parameter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigate(String view) {
        navigate(view, null);
    }
    
    
}
