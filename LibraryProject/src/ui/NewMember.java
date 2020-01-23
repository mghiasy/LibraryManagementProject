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
        TextField firstname = new TextField();
        //userTextField.setPrefColumnCount(10);
        //userTextField.setPrefWidth(30);
        grid.add(firstname, 0, 2);
        //last name
        Label lname = new Label("Last Name:");
        grid.add(lname, 1, 1);
        TextField lastname = new TextField();
        grid.add(lastname, 1, 2);
        // id
        Label id = new Label("ID:");
        grid.add(id, 0,3);
        TextField memberId = new TextField();
        grid.add(memberId, 0, 4);
        //tel
        Label tel = new Label("Tel:");
        grid.add(tel, 1, 3);
        TextField telNum= new TextField();
        grid.add(telNum, 1, 4);
        //City
        Label cityLabel = new Label("City:");
        grid.add(cityLabel, 0,5);
        TextField city = new TextField();
        grid.add(city, 0, 6);
        //street
        Label street = new Label("Street:");
        grid.add(street, 1, 5);
        TextField streetField = new TextField();
        grid.add(streetField, 1, 6);
        //State
        Label stateLabel = new Label("State:");
        grid.add(stateLabel, 0, 7);
        TextField state = new TextField();
        grid.add(state, 0, 8);
        //zip
        Label zipLabel = new Label("Zip:");
        grid.add(zipLabel, 1, 7);
        TextField zip = new TextField();
        grid.add(zip, 1, 8);


        Button signUp = new Button("Save");
        signUp.setStyle("-fx-background-color: #468b00;");
        signUp.setTextFill(Color.GHOSTWHITE);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);//me: from Bottom_R to Bottom_c
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
                if(firstname.getText().trim().length()== 0)
                    messageBar.setText("Error, first name!");
                else if(lastname.getText().trim().length()== 0)
                    messageBar.setText("Error, last name!");
                else if(memberId.getText().trim().length()== 0)
                    messageBar.setText("Error, ID!");
                else if(telNum.getText().trim().length()== 0)
                    messageBar.setText("Error, Telephone Number!");
                else if(city.getText().trim().length()== 0)
                    messageBar.setText("Error, City!");
                else if(street.getText().trim().length()== 0)
                    messageBar.setText("Error, Street!");
                else if(state.getText().trim().length()== 0)
                    messageBar.setText("Error,State!");
                else if(zip.getText().trim().length()== 0)
                    messageBar.setText("Error, Zip!");
                else {
                    Address ad = new Address(street.getText().trim(),
                            city.getText().trim(),state.getText().trim(),zip.getText().trim());
                    CheckoutRecord checkout = new CheckoutRecord();
                    LibraryMember lm = new LibraryMember(memberId.getText().trim(),firstname.getText().trim(),lastname.getText().trim(),telNum.getText().trim(),ad,checkout);
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
        backBtn.setStyle("-fx-background-color: #8B0000;");
        backBtn.setTextFill(Color.GHOSTWHITE);
        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().add(backBtn);
        grid.add(hBack, 0, 10);
        //back to main
//        Button backBtn1 = new Button("<<Main");
//        backBtn1.setStyle("-fx-background-color: #2c5800;");
//        backBtn1.setTextFill(Color.GHOSTWHITE);
//        backBtn1.setOnAction(e -> back());
//        HBox hBack1 = new HBox(10);
//        hBack1.setAlignment(Pos.BOTTOM_LEFT);
//        hBack1.getChildren().add(backBtn1);
//        grid.add(hBack1, 0, 10);

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
        AllMembersWindow.INSTANCE.show();

    }

}
