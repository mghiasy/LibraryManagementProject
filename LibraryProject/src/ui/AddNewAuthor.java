package ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import business.Address;
import business.Author;
import business.ControllerInterface;
import business.Person;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddNewAuthor extends Stage implements LibWindow {
	public static final AddNewAuthor INSTANCE = new AddNewAuthor();
	public Author author;

	@Override
	public void init() {
		setTitle("Add new Author");
		GridPane gp = new GridPane();
		gp.setId("AddNewAuthorgp");
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(25, 25, 25, 25));
		Text scenetitle = new Text("Add new Author");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); // Tahoma
		gp.add(scenetitle, 0, 0, 2, 1);

		Label lblFirstName = new Label("FirstName :");
		gp.add(lblFirstName, 0, 1);
		TextField txtFirstName = new TextField();
		gp.add(txtFirstName, 1, 1);

		Label lblLastName = new Label("LastName :");
		gp.add(lblLastName, 0, 2);
		TextField txtLastName = new TextField();
		gp.add(txtLastName, 1, 2);

		Label lbltelephone = new Label("Telephone :");
		gp.add(lbltelephone, 0, 3);
		TextField txttelephone = new TextField();
		gp.add(txttelephone, 1, 3);

		Label lblbio = new Label("Bio :");
		gp.add(lblbio, 0, 4);
		TextField txtBio = new TextField();
		gp.add(txtBio, 1, 4);

		Label lblAddress = new Label("Adress :");
		gp.add(lblAddress, 0, 5);
		Label lblEmpty = new Label("--------------------------------------------");
		gp.add(lblEmpty, 1, 5);

		Label lblStreet = new Label("Street :");
		gp.add(lblStreet, 0, 6);
		TextField txtStreet = new TextField();
		gp.add(txtStreet, 1, 6);

		Label lblCity = new Label("City :");
		gp.add(lblCity, 0, 7);
		TextField txtCity = new TextField();
		gp.add(txtCity, 1, 7);

		Label lblstate = new Label("State :");
		gp.add(lblstate, 0, 8);
		TextField txtstate = new TextField();
		gp.add(txtstate, 1, 8);

		Label lblzip = new Label("Zipcode :");
		gp.add(lblzip, 0, 9);
		TextField txtzip = new TextField();
		gp.add(txtzip, 1, 9);

		gp.setGridLinesVisible(false);

		Button backToMainBtn = new Button("<= Back to Main");
		Button backBtn = new Button("<= Back to list");
		Button addBtn = new Button("Add");

		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				 if (isInputValid(txtFirstName, txtLastName, txttelephone, txtBio,txtStreet,txtCity,txtstate,txtzip)) {

				Address address = new Address(txtStreet.getText(),txtCity.getText(), txtstate.getText(), txtzip.getText());

				author = new Author(txtFirstName.getText(), txtLastName.getText(), txttelephone.getText(), address, txtBio.getText());
				AddNewAuthor.INSTANCE.close();
				BookAuthors.INSTANCE.addAuther(author);
				BookAuthors.INSTANCE.show();
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

		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backToMainBtn);
		hBack.getChildren().add(backBtn);
		hBack.getChildren().add(addBtn);
		gp.add(hBack, 1, 10);
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

	private boolean isInputValid(TextField txtFirstName, TextField txtLastName, TextField txttelephone,TextField txtBio, TextField txtStreet, TextField txtCity,TextField txtstate,TextField txtzip) {
		String errorMessage = "";
		if (txtFirstName.getText() == null || txtFirstName.getText().length() == 0) {
			errorMessage += "Please enter FirstName!\n";
		}
		if (txtLastName.getText() == null || txtLastName.getText().length() == 0) {
			errorMessage += "Please enter LastName! \n";
		}
		if (txttelephone.getText() == null || txttelephone.getText().length() == 0) {
			errorMessage += "Please enter telephone \n";
		}
		if (txtBio.getText() == null || txtBio.getText().length() == 0) {
			errorMessage += "Please enter Bio \n";
		}
		if (txtStreet.getText() == null || txtStreet.getText().length() == 0) {
			errorMessage += "Please enter Street \n";
		}
		if (txtstate.getText() == null || txtstate.getText().length() == 0) {
			errorMessage += "Please enter state \n";
		}
		if (txtCity.getText() == null || txtCity.getText().length() == 0) {
			errorMessage += "Please enter City \n";
		}
		if (txtzip.getText() == null || txtzip.getText().length() == 0) {
			errorMessage += "Please enter zip \n";
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

}
