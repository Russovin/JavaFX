package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import util.Connect;
import view.homepage.admin.CupManagementPage;
import view.homepage.user.UserHomePage;

public class Login extends Application{
	
	Connect connect = Connect.getInstance();
	Scene sc;
	Stage stage;
	BorderPane bp;
	VBox vb,vb1;
	
	Label titleLbl, usernameLbl, passwordLbl;
	TextField usernameTf;
	PasswordField passwordPf;
	Button loginBtn;
	Hyperlink registerLink;

	public static String username;
	public void init() {
		bp = new BorderPane();
		vb = new VBox(20);
		vb1 = new VBox();
		
		sc = new Scene(bp, 800, 700);
		
		titleLbl = new Label("Login");
		usernameLbl = new Label("Username");
		passwordLbl = new Label("Password");
		
		usernameTf = new TextField();
		
		passwordPf = new PasswordField();
		
		loginBtn = new Button("Login");
		
		registerLink = new Hyperlink("Don't have an account yet? Register Here!");
		
	}
	
	public void setLayout() {
		bp.setCenter(vb);
	}
	
	public void addComponent() {
		vb.getChildren().addAll(titleLbl,vb1, loginBtn, registerLink);
		vb1.getChildren().addAll(usernameLbl, usernameTf, passwordLbl, passwordPf);
	}
	
	public void setAlignment() {
		vb.setAlignment(Pos.CENTER);
		vb1.setPadding(new Insets(0, 150, 0, 150));
		
		VBox.setMargin(usernameTf, new Insets(0, 0, 30, 0));
		
		loginBtn.setPadding(new Insets(10, 30, 10, 30));
	}
	
	public void editComponent() {
		passwordPf.setPromptText("Input your password here");
		usernameTf.setPromptText("Input your username here");
		titleLbl.setFont(Font.font("Calibri",FontWeight.BOLD,35));
	}
	
	
	public void eventHandler() {
		registerLink.setOnAction(e -> {
			Register register = new Register();
			try {
				register.start(stage);
			} catch (Exception ex) {
				
			}
		});
		
		Alert alert = new Alert(AlertType.ERROR);
		loginBtn.setOnAction(e -> {
			if(e.getSource() == loginBtn) {
				if(usernameTf.getText().equals("")) {
					alert.setContentText("Please fill out your username");
					alert.showAndWait();
				}
				else if(passwordPf.getText().equals("")) {
					alert.setContentText("Please fill out your password");
					alert.showAndWait();
				}
				else {
					boolean validCredentials = connect.checkCredentials(usernameTf.getText(), passwordPf.getText());
		            if (validCredentials) {
		            	
		            	// admin atau user
		            	String role = connect.checkRole(usernameTf.getText());
		            	
		            	if(role.equals("User")) {
		            		UserHomePage user = new UserHomePage();
			            	try {
			            		Login.username = usernameTf.getText();
			            		user.start(stage);
							} catch (Exception e2) {
								
							}
		            	}
		            	else {
		            		CupManagementPage admin = new CupManagementPage();
		            		try {
		            			admin.start(stage);	
							} catch (Exception e2) {
							}
		            	}
		            	
		            } else {
		                alert.setContentText("Invalid username or password. Please try again.");
		                alert.showAndWait();
		            }
				}
				
			}
		});
	}
	
	public Login() {
		init();
		setLayout();
		addComponent();
		setAlignment();
		editComponent();
		eventHandler();
	}
	
	@Override
	public void start(Stage s) throws Exception {
		stage = s;
		s.setTitle("cangkIR");
		s.setScene(sc);
		s.show();
	}
	
	
	
	
}
