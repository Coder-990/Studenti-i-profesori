package hr.java.vjezbe;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class MainMenuController {
	
	@FXML 
	private MenuItem pretragaProfesora;
	
	
	@FXML 
	private MenuItem pretragaStudenta;
	
	
	@FXML 
	private MenuItem pretragaIspita;

	@FXML 
	private MenuItem pretragaPredmeta;
	
	@FXML 
	private MenuItem pretragaKomisijskogIspita;
	
	@FXML 
	private MenuItem unosKomisijskogIspita;
	
	@FXML 
	private MenuItem unosProfesora;
	
	@FXML 
	private MenuItem unosStudenta;
	
	@FXML 
	private MenuItem unosIspita;
	
	@FXML 
	private MenuItem unosPredmeta;
	
	
	public void borderPaneProfesor() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Profesor.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void borderPaneStudent() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Student.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void borderPanePredmet() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Predmet.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void borderPaneIspit() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Ispit.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void borderPaneKomisijskiIspit() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("KomisijskiIspit.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void borderPaneUnesiKomisijskiIspit() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosKomisijskiIspit.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void borderPaneUnesiProfesora() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosProfesora.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void borderPaneUnesiStudenta() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosStudenta.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void borderPaneUnesiPredmet() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosPredmeta.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void borderPaneUnesiIspit() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosIspita.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
