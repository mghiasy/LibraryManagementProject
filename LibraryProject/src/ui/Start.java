package ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Start extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	private static Stage primStage = null;
	public static Stage primStage() {
		return primStage;
	}

	public static class Colors {
		static Color green = Color.ANTIQUEWHITE;
		static Color red = Color.FIREBRICK;
	}


//	public static class Colors {
//		static Color green = Color.web("#034220");
//		static Color red = Color.FIREBRICK;
//	}

	private static Stage[] allWindows = {
			LoginWindow.INSTANCE,
			AllMembersWindow.INSTANCE,
			AllBooksWindow.INSTANCE,
			CheckoutWindow.INSTANCE,
			CheckoutBookConfirm.INSTANCE,
			LibrarianStartWindow.INSTANCE,
			DisplayMemberHistory.INSTANCE,
			History.INSTANCE,
			NewMember.INSTANCE,
			AdminAccess.INSTANCE,
//			AddCopyWindow.INSTANCE,
			Both.INSTANCE,
//			AddBookWindow.INSTANCE

	};
//
//	public static Stage retDisplayMemberHistory() {
//		return allWindows[6];
//	}
//
//	public static void hideHistory() {
//		allWindows[7].hide();
//	}

	public static void hideAllWindows() {
		primStage.hide();
		for(Stage st: allWindows) {
			st.hide();
		}
	}
	public static void hideAddWindow () {
		allWindows[10].hide();
	}

	public static void hideCheckout() {
		primStage.hide();
		allWindows[5].hide();
	}

	public static void hideConfirmWindows() {
		primStage.hide();
		allWindows[4].hide();
	}
	public static Stage retLibrarianStartWindow() {
		return allWindows[5];
	}
	public static void hideHistory() {
		allWindows[7].hide();
	}
	public static Stage retDisplayMemberHistory() {
		return allWindows[6];
	}

	@Override
	public void start(Stage primaryStage) {
		primStage = primaryStage;
		primaryStage.setTitle("Main Page");
					
		VBox topContainer = new VBox();
		topContainer.setId("top-container");
		MenuBar mainMenu = new MenuBar();
		VBox imageHolder = new VBox();
		Image image = new Image("ui/library.jpg", 400, 300, false, false);

        // simply displays in ImageView the image as is
        ImageView iv = new ImageView();
        iv.setImage(image);
        imageHolder.getChildren().add(iv);
        imageHolder.setAlignment(Pos.CENTER);
        HBox splashBox = new HBox();
        Label splashLabel = new Label("Library Memeber Login");
        splashLabel.setFont(Font.font("Trajan Pro", FontWeight.BOLD, 30));
        splashBox.getChildren().add(splashLabel);
        splashBox.setAlignment(Pos.CENTER);
		
		topContainer.getChildren().add(mainMenu);
		topContainer.getChildren().add(splashBox);
		topContainer.getChildren().add(imageHolder);

		//Using button
		Button lbtn = new Button("Continue To Login:)");
		lbtn.setStyle("-fx-background-color: #2c5800;");
		lbtn.setTextFill(Color.GHOSTWHITE);
		lbtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				hideAllWindows();
				if(!LoginWindow.INSTANCE.isInitialized()) {
					LoginWindow.INSTANCE.init();
				}
				LoginWindow.INSTANCE.clear();
				LoginWindow.INSTANCE.show();
			}
		});

		//Using the menu option
//		Menu optionsMenu = new Menu("Options");
//		MenuItem login = new MenuItem("Login");
//
//		login.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//            	hideAllWindows();
//    			if(!LoginWindow.INSTANCE.isInitialized()) {
//    				LoginWindow.INSTANCE.init();
//    			}
//    			LoginWindow.INSTANCE.clear();
//    			LoginWindow.INSTANCE.show();
//            }
//        });

		//view books in the library
//		MenuItem bookIds = new MenuItem("All Book Ids");
//		bookIds.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//				hideAllWindows();
//				if(!AllBooksWindow.INSTANCE.isInitialized()) {
//					AllBooksWindow.INSTANCE.init();
//				}
//				ControllerInterface ci = new SystemController();
//				List<String> ids = ci.allBookIds();
//				Collections.sort(ids);
//				StringBuilder sb = new StringBuilder();
//				for(String s: ids) {
//					sb.append(s + "\n");
//				}
//				AllBooksWindow.INSTANCE.setData(sb.toString());
//				AllBooksWindow.INSTANCE.show();
//            }
//		});
		//view library members
//		MenuItem memberIds = new MenuItem("View Library Members");
//		memberIds.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//				hideAllWindows();
//				if(!AllMembersWindow.INSTANCE.isInitialized()) {
//					AllMembersWindow.INSTANCE.init();
//				}
//
//				//System.out.println(members);
////				StringBuilder sb = new StringBuilder();
//
//
////				for(LibraryMember s: members) {
////					sb.append(s + "\n");
////				}
////
////
////				System.out.println(sb.toString());
////				AllMembersWindow.INSTANCE.setData(sb.toString());
//				AllMembersWindow.INSTANCE.show();
//            }
//		});
//		optionsMenu.getItems().addAll(login, bookIds, memberIds);
//
//		mainMenu.getMenus().addAll(optionsMenu);

		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_CENTER);
		hBack.getChildren().add(lbtn);
		topContainer.getChildren().add(hBack);


		Scene scene = new Scene(topContainer, 420, 375);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		primaryStage.show();
	}
	
}
