package main;

import javafx.application.Application;
import javafx.stage.Stage;
import util.Connect;
import view.Login;
import view.Register;
import view.homepage.admin.CupManagementPage;
import view.homepage.user.CartPage;
import view.homepage.user.PopUp;
import view.homepage.user.UserHomePage;

public class Main extends Application{
	
	private Connect connect = Connect.getInstance();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage s) throws Exception {
		Login login = new Login();
		login.start(s);

		s.setResizable(false);
		
		
	} 
	
}
