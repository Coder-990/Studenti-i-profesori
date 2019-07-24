package hr.java.vjezbe;

import java.io.IOException;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.util.Datoteke;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProfesorController {

	@FXML
	private TextField textFieldSifra;
	
	@FXML
	private TextField textFieldIme;
	
	@FXML
	private TextField textFieldPrezime;
	
	@FXML
	private TextField textFieldTitula;
	
	@FXML
	private Button buttonPretrazi;
	
	@FXML
	private Button buttonSpremi;
	
	@FXML
	private TableView<Profesor> tableViewProfesor;
	
	@FXML
	private TableColumn<Profesor, String> tableColumnSifra;
	
	@FXML
	private TableColumn<Profesor, String> tableColumnIme;
	
	@FXML
	private TableColumn<Profesor, String> tableColumnPrezime;
	
	@FXML
	private TableColumn<Profesor, String> tableColumnTitula;
	
	private ObservableList<Profesor> profesori;
	
	@FXML
	public void initialize() {
		
		tableColumnIme.setCellValueFactory(new PropertyValueFactory<Profesor, String>("ime"));
		tableColumnIme.setStyle("-fx-alignment: CENTER;");
		
		tableColumnPrezime.setCellValueFactory(new PropertyValueFactory<Profesor, String>("prezime"));
		tableColumnPrezime.setStyle("-fx-alignment: CENTER;");
		
		tableColumnSifra.setCellValueFactory(new PropertyValueFactory<Profesor, String>("sifra"));
		tableColumnSifra.setStyle("-fx-alignment: CENTER;");
		
		tableColumnTitula.setCellValueFactory(new PropertyValueFactory<Profesor, String>("titula"));
		tableColumnTitula.setStyle("-fx-alignment: CENTER;");
		
		profesori = FXCollections.observableList(Datoteke.dohvatiProfesore());
		tableViewProfesor.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableViewProfesor.setItems(profesori);		
	}
	
	public void pretragaProfesora(ActionEvent event) {
		
		List<Profesor> filteredList = profesori
				.filtered(profesor -> profesor.getIme().toLowerCase().contains(textFieldIme.getText().toLowerCase()))
				.filtered(profesor -> profesor.getPrezime().toLowerCase().contains(textFieldPrezime.getText().toLowerCase()))
				.filtered(profesor -> profesor.getSifra().toLowerCase().contains(textFieldSifra.getText().toLowerCase()))
				.filtered(profesor -> profesor.getTitula().toLowerCase().contains(textFieldTitula.getText().toLowerCase()))
				.stream().collect(Collectors.toList());
		
		tableViewProfesor.setItems(FXCollections.observableList(filteredList));
	}
	
	public void spremiProfesora() throws IOException {
		
		String unosSifra = textFieldSifra.getText().trim();
		String unosIme = textFieldIme.getText().trim();
		String unosPrezime = textFieldPrezime.getText().trim();
		String unosTitula = textFieldTitula.getText().trim();
		
		String message = "";
		if(unosSifra == null || unosSifra.length() == 0) message += "\nsifra";
		if(unosIme == null || unosIme.length() == 0) message += "\nime";
		if(unosPrezime == null || unosPrezime.length() == 0) message += "\nprezime";
		if(unosTitula == null || unosTitula.length() == 0) message += "\ntitula";
		
		if (!message.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("FATAL ERROR:");
			alert.setHeaderText("Pogreska, niste unijeli slijedece kolone:");
			alert.setContentText(message);
			alert.showAndWait();
		}else {
			OptionalLong maksimalniId= profesori.stream().mapToLong(profesor-> profesor.get_id()).max();
			Profesor newProfesor= new Profesor(maksimalniId.getAsLong() + 1, 
					textFieldSifra.getText(), textFieldIme.getText(), textFieldPrezime.getText(), textFieldTitula.getText());
			profesori.add(newProfesor);
			Datoteke.noviProfesorUDatoteci(profesori);
			tableViewProfesor.refresh();
		}
		
				
		
	}
}
