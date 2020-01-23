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

public class CheckoutWindow extends Stage implements LibWindow {
	public static final CheckoutWindow INSTANCE = new CheckoutWindow();
	
	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private Text messageBar = new Text();
	private Text messageBar2 = new Text();

	public void clearID() {
		messageBar.setText("");

	}
	
	public void clearIsbn() {
		messageBar2.setText("");

	}


	/* This class is a singleton */
	private CheckoutWindow() {}
	
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Librarian Window");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 1, 1);

        Label memberID = new Label("Member ID:");
        grid.add(memberID, 0, 1);
        TextField memberIdTextField = new TextField();
        //userTextField.setPrefColumnCount(10);
        //userTextField.setPrefWidth(30);
        grid.add(memberIdTextField, 0, 2);

        
        Label bookISBN = new Label("book ISBN:");
        grid.add(bookISBN, 2, 1);
        TextField ISBNTextField = new TextField();
        //userTextField.setPrefColumnCount(10);
        //userTextField.setPrefWidth(30);
        grid.add(ISBNTextField, 2, 2);

        
        
		Button backBtn = new Button("<= Back to Main");
		Button SearchBtn = new Button("Search");

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Start.hideAllWindows();
        		Start.retLibrarianStartWindow().show();
        	}
        });
        
        
        SearchBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
    			ControllerInterface c = new SystemController();
    			List<String> membIdList = c.allMemberIds();
    			List<String> bookList = c.allBookIds();
    			boolean idFound = false;
    			boolean isbnFound = false;
    			System.out.println("Search Button");
    			
    			if(membIdList.contains(memberIdTextField.getText().trim()))
		    			{     
    					  	idFound = true;
    					  	clearID();
		    			}else    
    					{messageBar.setFill(Start.Colors.red);
		             	 messageBar.setText("Member ID not Found");}

             	DataAccess da = new DataAccessFacade();
             	HashMap<String,Book > map = da.readBooksMap();
             	Book currentBook = null;
             	
        		DataAccess memb = new DataAccessFacade();
        		HashMap<String, LibraryMember> mapMemb = memb.readMemberMap();
        		LibraryMember mem = null;
        		
	    		if(bookList.contains(ISBNTextField.getText().trim()))
	    			{     
	             		
	    					currentBook = map.get(ISBNTextField.getText());

	    				//
		             	if ( ! currentBook.isAvailable())
			             	{
		             		 messageBar2.setFill(Start.Colors.red);
			             	 messageBar2.setText("Book not Available");
			             	}
		             	else {
			             	 messageBar2.setText("Available");
			    				isbnFound = true;
			    				clearIsbn();
		             		}
	    			}else    
					{messageBar2.setFill(Start.Colors.red);
	             	 messageBar2.setText("Book ISBN not Found");}

		    			
			    		if( idFound && isbnFound) {
			        		 mem = mapMemb.get(memberIdTextField.getText());
			    			
			    			//Start.hideAllWindows();
			    			if(!CheckoutBookConfirm.INSTANCE.isInitialized()) {

			    				CheckoutBookConfirm.INSTANCE.setBook(currentBook);
			    				CheckoutBookConfirm.INSTANCE.setMember(mem);
			    				CheckoutBookConfirm.INSTANCE.init();
	            			}
			    			CheckoutBookConfirm.INSTANCE.show();
			    			}
        	}
        });

        
        
        HBox hSearch = new HBox(10);
        hSearch.setAlignment(Pos.BOTTOM_RIGHT);
        hSearch.getChildren().add(SearchBtn);
        grid.add(hSearch, 2 , 7);

        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().add(backBtn);
        grid.add(hBack, 0, 7);
        
        
        VBox messageBox = new VBox(10);
        messageBox.setAlignment(Pos.BOTTOM_RIGHT);
        messageBox.setAlignment(Pos.BOTTOM_RIGHT);
        messageBox.getChildren().addAll(messageBar,messageBar2);
        grid.add(messageBox, 2, 5);

        
		Scene scene = new Scene(grid, 400 , 400);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
        isInitialized(true);
	}
}
