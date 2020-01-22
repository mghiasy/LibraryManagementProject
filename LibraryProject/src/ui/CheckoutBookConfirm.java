package ui;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import business.Book;
import business.BookCopy;
import business.CheckoutRecord;
import business.LibraryMember;
import business.LoginException;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CheckoutBookConfirm extends Stage implements LibWindow {
	public static final CheckoutBookConfirm INSTANCE = new CheckoutBookConfirm();
	private Book currentBook;
	private LibraryMember currentMember;

	
	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	

	/* This class is a singleton */
	private CheckoutBookConfirm() {}
	
	public void setMember(LibraryMember member)
	{
		this.currentMember = member;
	}
	
	public LibraryMember getMember()
	{
		return this.currentMember;
	}


	public void setBook(Book book)
	{
		this.currentBook = book;
	}
	
	public Book getBook()
	{
		return this.currentBook;
	}
	
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Checkout information");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 1, 1);
        
        Label getIsbn = new Label("Book ISBN:  " +this.getBook().getIsbn());
        Label getTitle = new Label("Book Title:  "+this.getBook().getTitle());
        
        int copynums = this.getBook().clacCurrentCopyNums();
        String NumsOfCopy = Integer.toString(copynums);
        
        Label getCopyNums = new Label("Avialable Copies:  "+ NumsOfCopy);
        VBox hBook = new VBox(10);
        hBook.setAlignment(Pos.CENTER_RIGHT);
        hBook.getChildren().addAll(getTitle,getIsbn,getCopyNums);
        grid.add(hBook, 0, 2);
        

		Button backBtn = new Button("<= Back to Main");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Start.hideAllWindows();
        		Start.primStage().show();
        	}
        });
         
       // -------- put checkout details -------------------------------------
        
    	LibraryMember member = getMember();	
    	LocalDate checkoutDate = LocalDate.now();
    	LocalDate dueDate = checkoutDate.plusDays(currentBook.getMaxCheckoutLength());

		Button confirm = new Button("Confirm");
		
    	DataAccess storing = new DataAccessFacade();

		confirm.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		
        	member.getCheckRecord().addEntry(currentBook.getNextAvailableCopy(), checkoutDate.toString(), dueDate.toString());	
        	
        	currentBook.getNextAvailableCopy().changeAvailability();
        	storing.saveBooks(currentBook);
        	storing.saveNewMember(currentMember);
        	Start.hideConfirmWindows();
        	
        	}
        });

        
        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().add(backBtn);
        grid.add(hBack, 0, 6);
        
        HBox hconfirm = new HBox(10);
        hconfirm.setAlignment(Pos.BOTTOM_RIGHT);
        hconfirm.getChildren().add(confirm);
        grid.add(hconfirm, 2, 6);

		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
}
