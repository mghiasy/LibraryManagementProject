package ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import business.ControllerInterface;
import business.LibraryMember;
import business.Person;
import business.SystemController;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AllMembersWindow extends Stage implements LibWindow {
	public static final AllMembersWindow INSTANCE = new AllMembersWindow();
	
	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private TextArea ta;
	private ObservableList Ob;
	private TableView<LibraryMember> table;
	private ObservableList<LibraryMember> data;

	public void setData(String data) {
		ta.setText(data);
	}

	private TableView tableView;

	public void setDataTry(ObservableList data) {

		tableView.setItems(data);

	}
	private AllMembersWindow() {}
	
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("All Library Members");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        //library Members table
//		TableView tableView = new TableView();
//
//		TableColumn<String, LibraryMember> column1 = new TableColumn<>("Member ID");
//		column1.setCellValueFactory(new PropertyValueFactory<>("id"));
//
//		TableColumn<String, LibraryMember> column2 = new TableColumn<>("Name");
//		column2.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//		TableColumn<String, LibraryMember> column3 = new TableColumn<>("Address");
//		column2.setCellValueFactory(new PropertyValueFactory<>("address"));
//
//		tableView.getColumns().add(column1);
//		tableView.getColumns().add(column2);
//		tableView.getColumns().add(column3);
//
//		//tableView.getSelectionModel().selectedIndexProperty().addListener(new RowSelectChangeListener());
//
//		tableView.setItems(Ob);


		table = new TableView<>();
		//data = getInitialTableData();
		table.setItems(data);

		ta = new TextArea();
		grid.add(ta, 0,1);

		Button backBtn = new Button("<= Back to Main");
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
        grid.add(hBack, 0, 2);
		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
}
