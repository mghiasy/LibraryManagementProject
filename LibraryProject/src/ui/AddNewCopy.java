package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import business.Address;
import business.Author;
import business.Book;
import business.LibrarySystemException;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddNewCopy extends Stage implements LibWindow {
	public static final AddNewCopy INSTANCE = new AddNewCopy();
	public business.BookCopy bookCopy;

	private AddNewCopy() {
	}

	@Override
	public void init() {
		setTitle("Add new book copy");
		GridPane gp = new GridPane();
		gp.setId("AddNewBookCopygp");
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(25, 25, 25, 25));
		Text scenetitle = new Text("Add new Copy");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); // Tahoma
		gp.add(scenetitle, 0, 0, 2, 1);

		Label lblcopyNum = new Label("Number of copies  :");
		gp.add(lblcopyNum, 0, 2);
		TextField txtcopyNum = new TextField();
		gp.add(txtcopyNum, 1, 2);

//		Label lblIsAvailable = new Label("Is Available :");
//		gp.add(lblIsAvailable, 0, 2);
//		CheckBox chkbIsAvailable = new CheckBox();
//		chkbIsAvailable.setSelected(true);
//		chkbIsAvailable.disableProperty()=true;
//		gp.add(chkbIsAvailable, 1, 2);
		
		Label lblBookList = new Label("Select book :");
		gp.add(lblBookList, 0, 1);
		DataAccess da = new DataAccessFacade();
		List<String> myList =da.readBooksIsdn();
		ObservableList<String> oListStavaka = FXCollections.observableArrayList(myList);
		ComboBox<String> cmbBookList=new ComboBox<String>(oListStavaka);
		gp.add(cmbBookList, 1, 1);

		gp.setGridLinesVisible(false);

		// Button backToMainBtn = new Button("<= Back to Main");
		Button cancelBtn = new Button("Cancel");
		Button addCopyBtn = new Button("Add");

		addCopyBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (isInputValid(txtcopyNum)) {
					INSTANCE.close();
					addCopy(Integer.parseInt(txtcopyNum.getText()), cmbBookList.getValue());
					Start.hideAllWindows();
	        		Start.primStage().show();
				}
			}
		});

		cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				AddNewCopy.INSTANCE.close();
			}
		});

		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(cancelBtn);
		hBack.getChildren().add(addCopyBtn);
		gp.add(hBack, 1, 5);
		Scene scene = new Scene(gp);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		setScene(scene);
	}

	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub

	}

	private boolean isInputValid(TextField txtcopyNum) {
		String errorMessage = "";
		if (txtcopyNum.getText() == null || txtcopyNum.getText().length() == 0) {
			errorMessage += "Please enter copy number!\n";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}
	public void addCopy(int copyNum, String  ISBN) {
//		DataAccess da = new DataAccessFacade();
//		Book book = da.findBookByIsdn(ISBN);
//		for (int i=1;i<=copyNum;i++) {
//			book.addCopy();
//		}

		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> bookSet = da.readBooksMap(); 
		Book book = bookSet.get(ISBN); 
		book.addCopy();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(AddNewCopy.INSTANCE);
		alert.setContentText("Success!");
		alert.showAndWait();
	}

}
