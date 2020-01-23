package ui;

import java.util.List;

import business.BookCopy;
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

public class BookCopies extends Stage {
	public static final BookCopies INSTANCE = new BookCopies();
	private TextArea ta;
	List<BookCopy> bookCopyList;
	
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
		Button newCopyBtn = new Button("Add new Copy");
		Button okBtn = new Button("Ok");

		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Start.hideAllWindows();
				AddNewBook.INSTANCE.show();
			}
		});
		newCopyBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Start.hideAllWindows();
				if (!AddNewCopy.INSTANCE.isInitialized()) {
					AddNewCopy.INSTANCE.init();
				}
				AddNewCopy.INSTANCE.show();
			}
		});
		okBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				INSTANCE.close();
				AddNewBook.INSTANCE.addCopies(bookCopyList);
				AddNewBook.INSTANCE.show();
			}
		});
		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backBtn);
		hBack.getChildren().add(okBtn);
		hBack.getChildren().add(newCopyBtn);

		grid.add(hBack, 0, 2);
		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		setScene(scene);
	}

	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub

	}
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addCopy(String text, String value) {
		// TODO Auto-generated method stub
		
	}


}
