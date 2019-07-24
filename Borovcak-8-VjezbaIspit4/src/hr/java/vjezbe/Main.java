package hr.java.vjezbe;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	private static VBox m_vBox;

	@Override
	public void start(Stage primaryStage) {
		try {
			m_vBox = (VBox)FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(m_vBox, 1100, 825);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void setMainPage(BorderPane root) {
		try {
			m_vBox.getChildren().set(1, root);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
