//Farahat
package ui;
import javafx.application.Application;

import business.ControllerInterface;
import business.LibraryMember;
import business.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import business.Address;
import business.CheckoutRecord;
import business.LoginException;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import business.LibraryMember;

public class NewMember extends Stage implements LibWindow {
    public static final NewMember INSTANCE = new NewMember();

    private boolean isInitialized = false;
    public boolean isInitialized() {
        return isInitialized;
    }
    public void isInitialized(boolean val) {
        isInitialized = val;
    }
    private Text messageBar = new Text();

    //private EventHandler<ActionEvent> ;
    public void clear() {
        messageBar.setText("");}
    private TextArea ta;
    public void setData(String data) {
        ta.setText(data);
    }

    /* This class is a singleton */
    private NewMember() {}
    public void init() {
        GridPane grid = new GridPane();
        grid.setId("top-container");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Library Member Form");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);
        //first name
        Label fName = new Label("First Name:");
        grid.add(fName, 0, 1);
        TextField fNameField = new TextField();
        //userTextField.setPrefColumnCount(10);
        //userTextField.setPrefWidth(30);
        grid.add(fNameField, 0, 2);
        //last name
        Label lname = new Label("Last Name:");
        grid.add(lname, 1, 1);
        TextField lNameField = new TextField();
        grid.add(lNameField, 1, 2);
        // id
        Label id = new Label("ID:");
        grid.add(id, 0,3);
        TextField idField = new TextField();
        grid.add(idField, 0, 4);
        //tel
        Label tel = new Label("Tel:");
        grid.add(tel, 1, 3);
        TextField telField = new TextField();
        grid.add(telField, 1, 4);
        //City
        Label city = new Label("City:");
        grid.add(city, 0,5);
        TextField cityField = new TextField();
        grid.add(cityField, 0, 6);
        //street
        Label street = new Label("Street:");
        grid.add(street, 1, 5);
        TextField streetField = new TextField();
        grid.add(streetField, 1, 6);
        //State
        Label state = new Label("State:");
        grid.add(state, 0, 7);
        TextField stateField = new TextField();
        grid.add(stateField, 0, 8);
        //zip
        Label zip = new Label("Zip:");
        grid.add(zip, 1, 7);
        TextField zipField = new TextField();
        grid.add(zipField, 1, 8);


        Button signUp = new Button("Save");
        signUp.setStyle("-fx-background-color: #468b00;");
        signUp.setTextFill(Color.GHOSTWHITE);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);//me: from Bottom_R to Bottom_c
        hbBtn.getChildren().add(signUp);
        grid.add(hbBtn, 1, 9);

        HBox messageBox = new HBox(10);
        messageBox.setAlignment(Pos.BOTTOM_LEFT);
        messageBox.getChildren().add(messageBar);;
        grid.add(messageBox, 0, 9);
        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                messageBar.setFill(Start.Colors.red);
                if(fNameField.getText().trim().length()== 0)
                    messageBar.setText("Error, first name!");
                else if(lNameField.getText().trim().length()== 0)
                    messageBar.setText("Error, last name!");
                else if(idField.getText().trim().length()== 0)
                    messageBar.setText("Error, ID!");
                else if(telField.getText().trim().length()== 0)
                    messageBar.setText("Error, Telephone Number!");
                else if(cityField.getText().trim().length()== 0)
                    messageBar.setText("Error, City!");
                else if(streetField.getText().trim().length()== 0)
                    messageBar.setText("Error, Street!");
                else if(stateField.getText().trim().length()== 0)
                    messageBar.setText("Error,State!");
                else if(zipField.getText().trim().length()== 0)
                    messageBar.setText("Error, Zip!");
                else {
                    Address ad = new Address(streetField.getText().trim(),
                            cityField.getText().trim(),stateField.getText().trim(),zipField.getText().trim());
                    CheckoutRecord checkout = new CheckoutRecord();
                    LibraryMember lm = new LibraryMember(idField.getText().trim(),fNameField.getText().trim(),lNameField.getText().trim(),telField.getText().trim(),ad);
                    ControllerInterface in = new SystemController();// modified
                    in.addMember(lm);
                    messageBar.setText("Successfully Added New Member");
                    messageBar.setFill(Start.Colors.green);
                    backAdmin();
                }
                }

        });
        // back to admin window
        Button backBtn = new Button("<<Admin");
        backBtn.setOnAction(e -> backAdmin());
        backBtn.setStyle("-fx-background-color: #2c5800;");
        backBtn.setTextFill(Color.GHOSTWHITE);
        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().add(backBtn);
        grid.add(hBack, 1, 10);
        //back to main
        Button backBtn1 = new Button("<<Main");
        backBtn1.setStyle("-fx-background-color: #2c5800;");
        backBtn1.setTextFill(Color.GHOSTWHITE);
        backBtn1.setOnAction(e -> back());
        HBox hBack1 = new HBox(10);
        hBack1.setAlignment(Pos.BOTTOM_LEFT);
        hBack1.getChildren().add(backBtn1);
        grid.add(hBack1, 0, 10);

        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);

    }
    void back()
    {
        Start.hideAllWindows();
        Start.primStage().show();
    }
    void backAdmin()
    {
        Start.hideAllWindows();
        AdminAccess.INSTANCE.show();

    }

}
