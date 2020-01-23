package ui;

import java.util.ArrayList;
import java.util.List;
import business.Address;
import business.Author;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

		Label lblcopyNum = new Label("Copy Number :");
		gp.add(lblcopyNum, 0, 1);
		TextField txtcopyNum = new TextField();
		gp.add(txtcopyNum, 1, 1);

		Label lblIsAvailable = new Label("Is Available :");
		gp.add(lblIsAvailable, 0, 2);
		CheckBox chkbIsAvailable = new CheckBox();
		gp.add(chkbIsAvailable, 1, 2);

		gp.setGridLinesVisible(false);
		Address ad = new Address("a", "a", "a", "a");
		Author author = new Author("a", "b", "c", ad, "aa");
		List<Author> authors = new ArrayList<Author>();
		authors.add(author);

		// Button backToMainBtn = new Button("<= Back to Main");
		Button cancelBtn = new Button("Cancel");
		Button addCopyBtn = new Button("Add");

		addCopyBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (isInputValid(txtcopyNum)) {
					INSTANCE.close();
					BookCopies.INSTANCE.addCopy(txtcopyNum.getText(), chkbIsAvailable.isSelected());
					BookCopies.INSTANCE.show();
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
//	private void save(int copyNum,boolean isAvailable, String bookISDN) {
//
//		
//		DataAccess da = new DataAccessFacade();
//		HashMap<String,Book> books =da.readBooksMap();
//		for (Map.Entry<String,Book> bookEntry : books.entrySet()) {
//			if(bookEntry.getValue().getIsbn() == bookISDN) {
//				AddNewCopy.INSTANCE.book=bookEntry.getValue();
//			}
//		}
//		BookCopy bc =new BookCopy(AddNewCopy.INSTANCE.book, copyNum, isAvailable);
//		da.saveNewCopy(bc);
//	}

}
