//Farahat
package ui;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

public class AdminAccess extends Stage implements LibWindow {
    public static final AdminAccess INSTANCE = new AdminAccess();

    private boolean isInitialized = false;

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    private Text messageBar = new Text();

    public void clear() {
        messageBar.setText("");
    }

    /* This class is a singleton */
    private AdminAccess() {
    }

    public void init() {
        GridPane grid = new GridPane();
        grid.setId("top-container");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Administrator Window");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); // Tahoma
        scenetitle.setStyle("-fx-background-color: #00003f;");
        grid.add(scenetitle, 0, 0, 2, 1);
        // add member
        Button addMember = new Button("Add new Member");
        addMember.setStyle("-fx-background-color: #00003f;");
        addMember.setTextFill(Color.GHOSTWHITE);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        addMember.setPrefWidth(150);
        addMember.setPrefHeight(100);
        hbBtn.getChildren().add(addMember);
        grid.add(hbBtn, 0, 2);

        Button addBook = new Button("Add Book");
        addBook.setStyle("-fx-background-color: #00003f;");
        addBook.setTextFill(Color.GHOSTWHITE);
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.CENTER);
        addBook.setPrefWidth(150);
        addBook.setPrefHeight(100);
        hbBtn1.getChildren().add(addBook);
        grid.add(hbBtn1, 1, 2);

//        Button addCopy = new Button("Add Book Copy");
//        addCopy.setStyle("-fx-background-color: #00003f;");
//        addCopy.setTextFill(Color.GHOSTWHITE);
//        grid.add(addCopy, 2, 2);

        //add member action listener
        addMember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                Start.hideAllWindows();
                if (!AllMembersWindow.INSTANCE.isInitialized()) {
                    AllMembersWindow.INSTANCE.init();
                }
                AllMembersWindow.INSTANCE.show();
            }
        });

//        addCopy.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                Start.hideAllWindows();
////                if (!AddCopyWindow.INSTANCE.isInitialized()) {
////                    AddCopyWindow.INSTANCE.init();
////                }
////                AddCopyWindow.INSTANCE.show();
//               }
//        });
        addBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Start.hideAllWindows();
                if (!AllBooksWindow.INSTANCE.isInitialized()) {
                    AllBooksWindow.INSTANCE.init();

                }
                ControllerInterface ci = new SystemController();
                List<String> ids = ci.allBookIds();
                Collections.sort(ids);
                StringBuilder sb = new StringBuilder();
                for(String s: ids) {
                    sb.append(s + "\n");
                }
                AllBooksWindow.INSTANCE.setData(sb.toString());
                AllBooksWindow.INSTANCE.show();

            }
        });
        Button backBtn = new Button("<< Back");
        backBtn.setStyle("-fx-background-color: #8B0000;");
        backBtn.setTextFill(Color.GHOSTWHITE);

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
