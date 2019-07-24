package hr.java.vjezbe;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.OptionalLong;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import hr.java.vjezbe.entitet.Ispiti;
import hr.java.vjezbe.entitet.Ocjena;
import hr.java.vjezbe.entitet.Predmet;

import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.util.Datoteke;
import hr.java.vjezbe.util.Konstante;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class IspitController {

	@FXML
	private TextField textFieldNaziv;
	
	@FXML
	private TextField textFieldStudent;

	@FXML
	private TextField textFieldOcjena;
	
	@FXML
	private ComboBox<String> ComboBoxOdaberiPredmet;
	
	@FXML
	private ComboBox<String> ComboBoxOdaberiStudenta;
	
	@FXML
	private ComboBox<Integer> ComboBoxOdaberiOcjenu ;
	
	@FXML
	private ComboBox<Integer> ComboBoxSati;
	
	@FXML
	private  ComboBox<Integer> ComboBoxMinute;
	
	@FXML
	private DatePicker datePickerDatumIspita;

	@FXML
	private Button buttonPretrazi;
	
	@FXML
	private Button buttonSpremi;
	
	
	@FXML
	private TableView<Ispiti> tableViewIspit;

	@FXML
	private TableColumn<Ispiti, Predmet> tableColumnNaziv;

	@FXML
	private TableColumn<Ispiti, Student> tableColumnStudent;

	@FXML
	private TableColumn<Ispiti, Ocjena> tableColumnOcjena;

	@FXML
	private TableColumn<Ispiti, LocalDateTime> tableColumnDatumIVrijemeIspita;

	private ObservableList<Ispiti> ispiti;
	
	private ObservableList<Predmet> predmeti;
	
	private ObservableList<Student> studenti;
	
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA);
	
	private DateTimeFormatter dateTimeFormatterForElement = DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA_I_VREMENA);
	
	@FXML
	public void initialize() {
		
		ispiti = FXCollections.observableList(Datoteke.dohvatiIspite());
		predmeti = FXCollections.observableList(Datoteke.dohvatiPredmete());
		studenti = FXCollections.observableList(Datoteke.dohvatiStudente());
		
		tableColumnNaziv.setCellValueFactory(new PropertyValueFactory<Ispiti, Predmet>("predmet"));
		tableColumnNaziv.setStyle("-fx-alignment: CENTER;");
		tableColumnNaziv.setCellFactory(column->{
			return new TableCell<Ispiti, Predmet>(){
				@Override
				protected void updateItem(Predmet item, boolean empty) {
					super.updateItem(item, empty);
					if (item== null || empty) {
						setText(null);
					}else {
						setText(item.getNaziv());
					}
				}
			};
		});
		
		tableColumnStudent.setCellValueFactory(new PropertyValueFactory<Ispiti, Student>("student"));
		tableColumnStudent.setStyle("-fx-alignment: CENTER;");
		tableColumnStudent.setCellFactory(column->{
			return new TableCell<Ispiti, Student>(){
				@Override
				protected void updateItem(Student item, boolean empty) {
					super.updateItem(item, empty);
					if(item == null || empty) {
						setText(null);
					}else {
						setText(item.getIme() + " " + item.getPrezime());
					}
				}	
			};
		});
		
		tableColumnOcjena.setCellValueFactory(new PropertyValueFactory<Ispiti, Ocjena>("ocjena"));
		tableColumnOcjena.setStyle("-fx-alignment: CENTER;");
		tableColumnOcjena.setCellFactory(column->{
			return new TableCell<Ispiti, Ocjena>(){
				@Override
				protected void updateItem(Ocjena item, boolean empty) {
					super.updateItem(item, empty);
					if(item == null || empty) {
						setText(null);
					}else {
						setText(item.getBroj().toString());
					}
				}
			};
		});
				
		tableColumnDatumIVrijemeIspita.setCellValueFactory(new PropertyValueFactory<Ispiti, LocalDateTime>("datumIVrijeme"));
		tableColumnDatumIVrijemeIspita.setStyle("-fx-alignment: CENTER;");		
		tableColumnDatumIVrijemeIspita.setCellFactory(column->{
			return new TableCell<Ispiti, LocalDateTime>(){
				@Override
				protected void updateItem(LocalDateTime item, boolean empty) {
					super.updateItem(item, empty);
					if(item == null || empty) {
						setText(null);
					}else {
						setText(dateTimeFormatterForElement.format(item));
					}
				}
			};
		});
		
		tableViewIspit.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	
		tableViewIspit.setItems(ispiti);
		
		if(ComboBoxOdaberiOcjenu !=null)IntStream.range(1, 6).forEach(ocjena->ComboBoxOdaberiOcjenu.getItems().add(ocjena));
		if(ComboBoxSati !=null)IntStream.range(0, 23).forEach(sat->ComboBoxSati.getItems().add(sat));
		if(ComboBoxMinute !=null)IntStream.range(0, 59).forEach(min->ComboBoxMinute.getItems().add(min));
		
		if(ComboBoxOdaberiStudenta !=null)studenti.stream().forEach(student->ComboBoxOdaberiStudenta.getItems().add(student.toString()));
		if(ComboBoxOdaberiPredmet !=null)predmeti.stream().forEach(predmet->ComboBoxOdaberiPredmet.getItems().add(predmet.toString()));
		
	}

	public void pretragaIspita(ActionEvent event) {

		List<Ispiti> filteredList = ispiti
				.filtered(ispit -> ispit.getPredmet().getNaziv().toLowerCase().contains(textFieldNaziv.getText().toLowerCase()))
				.filtered(ispit -> String.format("%s %s", ispit.getStudent().getIme(), ispit.getStudent().getPrezime()).toLowerCase().contains(textFieldStudent.getText().toLowerCase()))
				.filtered(ispit -> ispit.getOcjena().getBroj().toString().contains(textFieldOcjena.getText()))							
				.stream().collect(Collectors.toList());
		
		if (datePickerDatumIspita.getValue() != null) {
			filteredList = filteredList.stream()
					.filter(ispit -> ispit.getDatumIVrijeme()
					.format(DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA))
					.contains(datePickerDatumIspita.getValue()
					.format(DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA)))).collect(Collectors.toList());
			datePickerDatumIspita.setValue(null);
		}
		
		if (ComboBoxSati.getValue() != null) {
			filteredList = filteredList.stream()
					.filter(ispit -> String.format("%02d", ispit.getDatumIVrijeme().getHour())
							.contains(String.format("%02d", ComboBoxSati.getValue())))
					.collect(Collectors.toList());
			ComboBoxSati.setValue(null);
		}
		
		if (ComboBoxMinute.getValue() != null) {
			filteredList = filteredList.stream()
					.filter(ispit -> String.format("%02d", ispit.getDatumIVrijeme().getMinute())
							.contains(String.format("%02d", ComboBoxMinute.getValue())))
					.collect(Collectors.toList());
			ComboBoxMinute.setValue(null);
		}		
		
		tableViewIspit.setItems(FXCollections.observableList(filteredList));
	}
	
public void spremiIspit() throws IOException {
		
		
		int odabirStudenta = ComboBoxOdaberiStudenta.getSelectionModel().getSelectedIndex();
		int odaberiPredmet = ComboBoxOdaberiPredmet.getSelectionModel().getSelectedIndex();
		
		String datum =(datePickerDatumIspita.getValue() !=null) ?
				dateTimeFormatter.format(datePickerDatumIspita.getValue()) : "";
		
	    int odaberiSate=(ComboBoxSati.getSelectionModel().getSelectedIndex() >=0)?
	    		ComboBoxSati.getValue() :-1;
	    int odaberiMinute=(ComboBoxMinute.getSelectionModel().getSelectedIndex() >=0)?
	    		ComboBoxMinute.getValue() :-1;
	    int odaberiOcjenu=(ComboBoxOdaberiOcjenu.getSelectionModel().getSelectedIndex() >=0)?
	    		ComboBoxOdaberiOcjenu.getValue() :-1;
		
		String message = "";
		if(odabirStudenta == -1) message += "\nstudent";
		if(odaberiPredmet == -1) message += "\npredmet";
		if(datum =="") message += "\ndatum ispita";
		if(odaberiSate < 0) message += "\nvrijeme sati";
		if(odaberiMinute < 0) message +="\nvrijeme minuta";
		if(odaberiOcjenu < 0) message +="\nocjena";
		
		if (!message.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("FATAL ERROR:");
			alert.setHeaderText("Pogreška, niste unijeli slijedeće kolone:");
			alert.setContentText(message);
			alert.showAndWait();
		}else {
			Student student=studenti.get(odabirStudenta);
			Predmet predmet=predmeti.get(odaberiPredmet);
			LocalDateTime datumIVrijemeIspita = datePickerDatumIspita.getValue().atStartOfDay();
			datumIVrijemeIspita = datumIVrijemeIspita.plusHours(odaberiSate);
			datumIVrijemeIspita = datumIVrijemeIspita.plusMinutes(odaberiMinute);
			Ocjena ocjena = Datoteke.pretvoriBrojUOcjenu(odaberiOcjenu);
			
			Ispiti ispit = new Ispiti(nextId(), predmet, student, ocjena, datumIVrijemeIspita);
			ispiti.add(ispit);
			Datoteke.noviIspitUDatoteci(ispit);
		}	
	}

		private Long nextId() {
			OptionalLong maksimalniId = ispiti.stream() 
			.mapToLong(ispit -> ispit.get_id()).max();
			return (maksimalniId.getAsLong() + 1);
		}
}
