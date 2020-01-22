//Farahat
package ui;
import javafx.application.Application;

import business.ControllerInterface;
import business.LibraryMember;
import business.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import business.Address;
//import business.CheckoutRecord;
import business.LoginException;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

public class Both extends Stage implements LibWindow {
    public static final Both INSTANCE = new Both();

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
    private Both() {}
    public void init() {
        GridPane grid = new GridPane();
        grid.setId("top-container");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Admin and Librarian Window");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);
        // add member
        Button admin = new Button("Admin Access");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(admin);
        grid.add(hbBtn, 0, 2);


        Button librarian = new Button("Librarian Acess");
        HBox hbBtn1 = new HBox(20);
        hbBtn1.setAlignment(Pos.CENTER);
        hbBtn1.getChildren().add(librarian);
        grid.add(hbBtn1, 1, 2);


        admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {


                Start.hideAllWindows();
                if(!AdminAccess.INSTANCE.isInitialized()) {
                    AdminAccess.INSTANCE.init();
                }
                AdminAccess.INSTANCE.show();
            }
        });
        //Mark, start from here man
        librarian.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Start.hideAllWindows();
                if(!LibrarianStartWindow.INSTANCE.isInitialized()) {
                    LibrarianStartWindow.INSTANCE.init();
                }
                LibrarianStartWindow.INSTANCE.show();

            }
        });

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
        grid.add(hBack, 0, 7);
        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);

    }




}