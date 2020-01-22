package ui;

import java.util.ArrayList;
import java.util.List;

import business.Address;
import business.Author;
import business.Book;
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
	private Stage dialogStage;
	public Book book;

	private AddNewBook() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
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
		Address ad = new Address("a", "a", "a", "a");
		Author author = new Author("a", "b", "c", ad, "aa");
		List<Author> authors = new ArrayList<Author>();
		authors.add(author);

		Button backBtn = new Button("<= Back to Main");
		Button saveBtn = new Button("Save");
		Button addCopyBtn = new Button("Add copy");
		saveBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override

			public void handle(ActionEvent e) {
				if (isInputValid(txtIsbn, txtTitle, txtmaxChkoutLength, authors)) {
					save(txtIsbn.getText(), txtTitle.getText(), Integer.parseInt(txtmaxChkoutLength.getText()),
							authors);
					dialogStage.close();
				}
			}
		});
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Start.hideAllWindows();
				Start.primStage().show();
			}
		});
		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backBtn);
		hBack.getChildren().add(saveBtn);
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

	private void save(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
		// if (isInputValid()) {
		book = new Book(isbn, title, maxCheckoutLength, authors);
		DataAccess da = new DataAccessFacade();
		da.saveNewBook(book);
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
			return false;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Input");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}
}
