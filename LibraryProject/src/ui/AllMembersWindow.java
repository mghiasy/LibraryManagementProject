package ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import business.ControllerInterface;
import business.LibraryMember;
import business.Person;
import business.SystemController;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;

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

	private TableView<LibraryMember> table;
	private ObservableList<LibraryMember> data;

	public void setData(String data) {
		ta.setText(data);
	}

//	private TableView tableView;
//
//	public void setDataTry(ObservableList data) {
//
//		tableView.setItems(data);
//
//	}
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
		table = new TableView<>();
		ControllerInterface ci = new SystemController();
		data = ci.allMembers();

		TableColumn nameCol = new TableColumn("Member Id");
		nameCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("memberId"));

		TableColumn first_name = new TableColumn("First Name");
		first_name.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("firstName"));

		TableColumn last_name = new TableColumn("Last Name");
		last_name.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("lastName"));

		TableColumn tel_Num = new TableColumn("Telephone No.");
		tel_Num.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("telephone"));

		TableColumn addressCol = new TableColumn("Address");
		addressCol.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("address"));

		//Inserting Button Into Table
		TableColumn editCol = new TableColumn("Edit");
		editCol.setSortable(false);

		//Un-Editable Column

//		TableColumn col_id = new TableColumn("ID");
//		table.getColumns().add(col_id);
//		col_id.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("memberId"));
//
//		editCol.setCellValueFactory(
//				new Callback<TableColumn.CellDataFeatures<LibraryMember, String>,
//										ObservableValue<Boolean>>() {
//					@Override
//					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<LibraryMember, String> p) {
//						return new SimpleBooleanProperty(p.getValue() != null);
//					}
//				});


		table.setItems(data);
		table.getColumns().setAll(nameCol, first_name, last_name, tel_Num, addressCol, editCol);
		table.setPrefWidth(700);
		//table.setPrefHeight(400);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(Boolean.TRUE);
		grid.add(table, 0,5);

		//Add New Member Button
		Button addMember = new Button("Add Member");
		addMember.setStyle("-fx-background-color: #2c5800;");
		addMember.setTextFill(Color.GHOSTWHITE);
		HBox hBack1 = new HBox(5);
		hBack1.setAlignment(Pos.TOP_CENTER);
		hBack1.getChildren().add(addMember);
		grid.add(hBack1, 2, 2);
		addMember.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Start.hideAllWindows();
				if (!NewMember.INSTANCE.isInitialized()) {
					NewMember.INSTANCE.init();
				}
				NewMember.INSTANCE.show();
			}
		});

		//Back to  Button
		Button backBtn = new Button("<= Back");
		backBtn.setStyle("-fx-background-color: #8B0000;");
		backBtn.setTextFill(Color.GHOSTWHITE);
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
				Start.hideAllWindows();
				AdminAccess.INSTANCE.show();
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
