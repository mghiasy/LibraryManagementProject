package ui;

import java.util.HashMap;
import java.util.List;
import business.Book;
import business.ControllerInterface;
import business.LibraryMember;
import business.LoginException;
import business.SystemController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LibrarianStartWindow extends Stage implements LibWindow {
	public static final LibrarianStartWindow INSTANCE = new LibrarianStartWindow();
	
	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	/* This class is a singleton */
	private LibrarianStartWindow() {}
	
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Librarian Features");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 1, 1);

        
		Button backBtn = new Button("<= Back to Main");
		Button checkoutBtn = new Button("Checkout");
		Button displaytBtn = new Button("Display Member History");

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Start.hideAllWindows();
        		Start.primStage().show();
        	}
        });
        
        checkoutBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
       		
        	Start.hideAllWindows();
   			if(!CheckoutWindow.INSTANCE.isInitialized()) {
   				CheckoutWindow.INSTANCE.init();
   			}
   			CheckoutWindow.INSTANCE.show();
        		
        	}
        });

        displaytBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
       		
        	Start.hideAllWindows();
   			if(!DisplayMemberHistory.INSTANCE.isInitialized()) {
   				DisplayMemberHistory.INSTANCE.init();
   			}
   			DisplayMemberHistory.INSTANCE.show();
        		
        	}
        });


        
        
        HBox hcheckout = new HBox(30);
        hcheckout.setAlignment(Pos.CENTER_LEFT);
        checkoutBtn.setPrefWidth(150);
        checkoutBtn.setPrefHeight(100);
        hcheckout.getChildren().add(checkoutBtn);
        grid.add(hcheckout, 0 , 3);

        HBox hDisplay= new HBox(30);
        hDisplay.setAlignment(Pos.CENTER_RIGHT);
        displaytBtn.setPrefWidth(150);
        displaytBtn.setPrefHeight(100);
        hDisplay.getChildren().add(displaytBtn);
        grid.add(hDisplay, 3 , 3);

        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().add(backBtn);
        grid.add(hBack, 0, 7);
        
        
		Scene scene = new Scene(grid, 400 , 300);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
        isInitialized(true);
	}
}

