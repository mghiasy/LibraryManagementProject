package ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import business.Book;
import business.CheckoutEntry;
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

public class DisplayMemberHistory extends Stage implements LibWindow {
	public static final DisplayMemberHistory INSTANCE = new DisplayMemberHistory();
	
	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	
	/* This class is a singleton */
	private DisplayMemberHistory() {}
	
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


        Label memberId = new Label("Insert Member ID:");
        grid.add(memberId, 0, 1);
        TextField memberIdTextField = new TextField();
        //userTextField.setPrefColumnCount(10);
        //userTextField.setPrefWidth(30);
        grid.add(memberIdTextField, 0, 2);
        
        
		Button backBtn = new Button("<= Back to Main");
		Button displaytBtn = new Button("Display Member History");

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Start.hideAllWindows();
        		Start.primStage().show();
        	}
        });

		DataAccess memb = new DataAccessFacade();
		HashMap<String, LibraryMember> mapMemb = memb.readMemberMap();

        displaytBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		SystemController c = new SystemController();
        		List<String> libMembs = c.allMemberIds();
        		
        		if(libMembs.contains(memberIdTextField.getText()))
        		{
    				if(!History.INSTANCE.isInitialized()) {
    					History.INSTANCE.init();
    				}
        		
    		        List<CheckoutEntry> chEntries = mapMemb.get(memberIdTextField.getText()).getCheckRecord().getCheckoutEntries();
		    		
    				for(CheckoutEntry  chEntry: chEntries)
					{
					  System.out.println("Title : " + chEntry.getBookCopy().getBook().getTitle() +"   ||Checkout Date : " + chEntry.getCheckoutDate() + "   ||Due Date : " +chEntry.getDueDate());
					}
		    		
    				
    				String s = null ;
        		  	StringBuilder sb = new StringBuilder();
        		  	for(CheckoutEntry  chEntry: chEntries)
        			{
        			    s = "Title : " + chEntry.getBookCopy().getBook().getTitle() +"  Checkout Date : " + chEntry.getCheckoutDate() + "  Due Date : " +chEntry.getDueDate();
        				sb.append(s + "\n");
        				//System.out.println(s);
        			}
        		  	History.INSTANCE.setData(sb.toString());
        		  	History.INSTANCE.show();
        			
        		}else{
        			System.out.println("Not Found");
        		}
        			
        	}
        });
        
        HBox hDisplay= new HBox(30);
        hDisplay.setAlignment(Pos.CENTER_LEFT);
        displaytBtn.setPrefWidth(150);
        displaytBtn.setPrefHeight(50);
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

