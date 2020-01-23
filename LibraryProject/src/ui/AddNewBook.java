package ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import business.Address;
import business.Author;
import business.Book;
import business.ControllerInterface;
import business.SystemController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddNewBook extends Stage implements LibWindow {
	public static final AddNewBook INSTANCE = new AddNewBook();
	private boolean isInitialized = false;
	public Book book;
	public List<Author> authors;
	public List<business.BookCopy> copies;

	private AddNewBook() {
	}

	@Override
	public void init() {

		setTitle("Add new book");
		GridPane gp = new GridPane();
		gp.setId("AddNewBookgp");
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(25, 25, 25, 25));
		Text scenetitle = new Text("Add new book");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); // Tahoma
		gp.add(scenetitle, 0, 0, 2, 1);

		Label lblTitle = new Label("Title :");
		gp.add(lblTitle, 0, 1);
		TextField txtTitle = new TextField();
		gp.add(txtTitle, 1, 1);

		Label lblisbn = new Label("ISBN :");
		gp.add(lblisbn, 0, 2);
		TextField txtIsbn = new TextField();
		gp.add(txtIsbn, 1, 2);

		Label lblmaxChkoutLength = new Label("Max checkout Lenght :");
		gp.add(lblmaxChkoutLength, 0, 3);
		TextField txtmaxChkoutLength = new TextField();
		gp.add(txtmaxChkoutLength, 1, 3);

		gp.setGridLinesVisible(false);

		Button backToMainBtn = new Button("<= Back to Main");
		Button backBtn = new Button("<= Back to list");
		Button saveBtn = new Button("Save");
		Button authorListBtn = new Button("List of authors");
		Button addCopyBtn = new Button("List of copies");
		addCopyBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Start.hideAllWindows();
				if(!BookCopies.INSTANCE.isInitialized()) {
					BookCopies.INSTANCE.init();
				}
				BookCopies.INSTANCE.show();	
			}
		});
		saveBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (isInputValid(txtIsbn, txtTitle, txtmaxChkoutLength, authors)) {
					System.out.println(authors.toString());
					save(txtIsbn.getText(), txtTitle.getText(), Integer.parseInt(txtmaxChkoutLength.getText()),
							authors,copies);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.initOwner(AddNewBook.INSTANCE);
					alert.setContentText("Success!");
					alert.showAndWait();
					ControllerInterface ci = new SystemController();
					List<String> ids = ci.allBookIds();
					if (ids != null) {
						Collections.sort(ids);
						StringBuilder sb = new StringBuilder();
						for (String s : ids) {
							sb.append(s + "\n");
						}
						AllBooksWindow.INSTANCE.setData(sb.toString());
						AddNewBook.INSTANCE.close();
						AllBooksWindow.INSTANCE.show();
					}
				}
			}
		});
		backToMainBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Start.hideAllWindows();
				Start.primStage().show();
			}
		});
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Start.hideAllWindows();
				AllBooksWindow.INSTANCE.show();
			}
		});
		authorListBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Start.hideAllWindows();
				if (!BookAuthors.INSTANCE.isInitialized()) {
					BookAuthors.INSTANCE.init();
				}
				BookAuthors.INSTANCE.show();
			}
		});
		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backToMainBtn);
		hBack.getChildren().add(backBtn);
		hBack.getChildren().add(authorListBtn);
        hBack.getChildren().add(addCopyBtn);
		hBack.getChildren().add(saveBtn);
		
		gp.add(hBack, 1, 5);
		Scene scene = new Scene(gp);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		setScene(scene);
	}

	public void addAuthers(List<Author> newAuthors) {
		authors = newAuthors;
	}

	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;

	}

	private void save(String isbn, String title, int maxCheckoutLength, List<Author> authors,List<business.BookCopy> copies) {
		// if (isInputValid()) {
		book = new Book(isbn, title, maxCheckoutLength, authors);
		DataAccess da = new DataAccessFacade();
		da.saveNewBook(book);
		for(business.BookCopy bc : copies) {
		//book.updateCopies(bc);
		book.updateCopies(bc);
		}
		// }
	}

	private boolean isInputValid(TextField txtIsbn, TextField txtTitle, TextField txtmaxChkoutLength,
			List<Author> authors) {
		String errorMessage = "";
		if (txtIsbn.getText() == null || txtIsbn.getText().length() == 0) {
			errorMessage += "Please enter Isbn!\n";
		}
		if (txtTitle.getText() == null || txtTitle.getText().length() == 0) {
			errorMessage += "Please enter title! \n";
		}
		if (txtmaxChkoutLength.getText() == null || txtmaxChkoutLength.getText().length() == 0) {
			errorMessage += "Please enter maxCheckoutLength \n";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(AddNewBook.INSTANCE);
			alert.setTitle("Invalid Input");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}

	public void addCopies(List<business.BookCopy> bookCopyList) {
		// TODO Auto-generated method stub
		copies=bookCopyList;
	}
}
