package ui;

import java.util.ArrayList;
import java.util.List;
import business.Author;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BookAuthors extends Stage {
	public static final BookAuthors INSTANCE = new BookAuthors();
	private TextArea ta;
	List<Author> authors;

	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("List of Book Authors");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); // Tahoma
		grid.add(scenetitle, 0, 0, 2, 1);

		ta = new TextArea();

		grid.add(ta, 0, 1);
		Button backBtn = new Button("<= Back");
		Button newAuthorBtn = new Button("Add new Author");
		Button okBtn = new Button("Ok");

		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Start.hideAllWindows();
				AddNewBook.INSTANCE.show();
			}
		});
		newAuthorBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Start.hideAllWindows();
				if (!AddNewAuthor.INSTANCE.isInitialized()) {
					AddNewAuthor.INSTANCE.init();
				}
				AddNewAuthor.INSTANCE.show();
			}
		});
		okBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				INSTANCE.close();
				AddNewBook.INSTANCE.addAuthers(authors);
				AddNewBook.INSTANCE.show();
			}
		});
		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backBtn);
		hBack.getChildren().add(okBtn);
		hBack.getChildren().add(newAuthorBtn);

		grid.add(hBack, 0, 2);
		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		setScene(scene);
	}
	
	public void addAuther(Author author) {
		ta.appendText(author.getFirstName() + " "+ author.getLastName() + "\n");
		if (authors == null) {
			authors = new ArrayList<Author>();
			authors.add(author);
		} else {
			authors.add(author);			
		}
	}
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub

	}
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

}
