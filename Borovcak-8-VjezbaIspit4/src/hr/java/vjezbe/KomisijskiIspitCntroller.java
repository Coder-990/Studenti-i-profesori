	package hr.java.vjezbe;

	import java.io.IOException;
	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;
	import java.util.List;
	import java.util.OptionalLong;
	import java.util.stream.Collectors;
	import java.util.stream.IntStream;
	import hr.java.vjezbe.entitet.KomisijskiIspit;
	import hr.java.vjezbe.entitet.Ocjena;
	import hr.java.vjezbe.entitet.Predmet;
	import hr.java.vjezbe.entitet.Profesor;
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


	public class KomisijskiIspitCntroller {

		@FXML
		private TextField textFieldNaziv;
		
		@FXML
		private TextField textFieldStudent;

		@FXML
		private TextField textFieldOcjena;
		
		@FXML
		private TextField textFieldPretragaClana;
		
		@FXML
		private ComboBox<String> ComboBoxOdaberiPredmet;
		
		@FXML
		private ComboBox<String> ComboBoxOdaberiStudenta;
		
		@FXML
		private ComboBox<Integer> ComboBoxOdaberiOcjenu;
		
		@FXML
		private ComboBox<Integer> ComboBoxSati;
		
		@FXML
		private ComboBox<Integer> ComboBoxMinute;
		
		@FXML
		private ComboBox<String> ComboBoxOdaberiClanaKomisije1;
		
		@FXML
		private ComboBox<String> ComboBoxOdaberiClanaKomisije2;
		
		@FXML
		private ComboBox<String> ComboBoxOdaberiClanaKomisije3;
		
		@FXML
		private DatePicker datePickerDatumIspita;

		@FXML
		private Button buttonPretrazi;
		
		@FXML
		private Button buttonSpremi;
			
		@FXML
		private TableView<KomisijskiIspit> tableViewKomisijskiIspit;

		@FXML
		private TableColumn<KomisijskiIspit, Predmet> tableColumnNaziv;

		@FXML
		private TableColumn<KomisijskiIspit, Student> tableColumnStudent;

		@FXML
		private TableColumn<KomisijskiIspit, Ocjena> tableColumnOcjena;

		@FXML
		private TableColumn<KomisijskiIspit, LocalDateTime> tableColumnDatumIVrijemeIspita;
		
		@FXML
		private TableColumn<KomisijskiIspit, Profesor> tableColumnClanKomisije1;
		
		@FXML
		private TableColumn<KomisijskiIspit, Profesor> tableColumnClanKomisije2;
		
		@FXML
		private TableColumn<KomisijskiIspit, Profesor> tableColumnClanKomisije3;
		
		private ObservableList<Profesor>profesori;
		
		private ObservableList<Predmet> predmeti;
		
		private ObservableList<Student> studenti;
		
		private ObservableList<KomisijskiIspit>komisijskiIspiti;
		
		private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA);
		
		private DateTimeFormatter dateTimeFormatterForElement = DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA_I_VREMENA);
		
		@FXML
		public void initialize() {
			
			profesori = FXCollections.observableList(Datoteke.dohvatiProfesore());
			predmeti = FXCollections.observableList(Datoteke.dohvatiPredmete());
			studenti = FXCollections.observableList(Datoteke.dohvatiStudente());
			komisijskiIspiti = FXCollections.observableList(Datoteke.dohvatiKomisijskeIspite());
			
			tableColumnNaziv.setCellValueFactory(new PropertyValueFactory<KomisijskiIspit, Predmet>("predmet"));
			tableColumnNaziv.setStyle("-fx-alignment: CENTER;");
			tableColumnNaziv.setCellFactory(column->{
				return new TableCell<KomisijskiIspit, Predmet>(){
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
			
			tableColumnStudent.setCellValueFactory(new PropertyValueFactory<KomisijskiIspit, Student>("student"));
			tableColumnStudent.setStyle("-fx-alignment: CENTER;");
			tableColumnStudent.setCellFactory(column->{
				return new TableCell<KomisijskiIspit, Student>(){
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
			
			tableColumnOcjena.setCellValueFactory(new PropertyValueFactory<KomisijskiIspit, Ocjena>("ocjena"));
			tableColumnOcjena.setStyle("-fx-alignment: CENTER;");
			tableColumnOcjena.setCellFactory(column->{
				return new TableCell<KomisijskiIspit, Ocjena>(){
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
					
			tableColumnDatumIVrijemeIspita.setCellValueFactory(new PropertyValueFactory<KomisijskiIspit, LocalDateTime>("datumIVrijeme"));
			tableColumnDatumIVrijemeIspita.setStyle("-fx-alignment: CENTER;");		
			tableColumnDatumIVrijemeIspita.setCellFactory(column->{
				return new TableCell<KomisijskiIspit, LocalDateTime>(){
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
			
			tableColumnClanKomisije1.setCellValueFactory(new PropertyValueFactory<KomisijskiIspit, Profesor>("clanKomisije1"));
			tableColumnClanKomisije1.setStyle("-fx-alignment: CENTER;");
			tableColumnClanKomisije1.setCellFactory(column->{
				return new TableCell<KomisijskiIspit, Profesor>(){
					@Override
					protected void updateItem(Profesor item, boolean empty) {
						super.updateItem(item, empty);
						if(item == null || empty) {
							setText(null);
						}else {
							setText(item.getIme() + " " + item.getPrezime());
						}
					}	
				};
			});
			
			tableColumnClanKomisije2.setCellValueFactory(new PropertyValueFactory<KomisijskiIspit, Profesor>("clanKomisije2"));
			tableColumnClanKomisije2.setStyle("-fx-alignment: CENTER;");
			tableColumnClanKomisije2.setCellFactory(column->{
				return new TableCell<KomisijskiIspit, Profesor>(){
					@Override
					protected void updateItem(Profesor item, boolean empty) {
						super.updateItem(item, empty);
						if(item == null || empty) {
							setText(null);
						}else {
							setText(item.getIme() + " " + item.getPrezime());
						}
					}	
				};
			});
			
			tableColumnClanKomisije3.setCellValueFactory(new PropertyValueFactory<KomisijskiIspit, Profesor>("clanKomisije3"));
			tableColumnClanKomisije3.setStyle("-fx-alignment: CENTER;");
			tableColumnClanKomisije3.setCellFactory(column->{
				return new TableCell<KomisijskiIspit, Profesor>(){
					@Override
					protected void updateItem(Profesor item, boolean empty) {
						super.updateItem(item, empty);
						if(item == null || empty) {
							setText(null);
						}else {
							setText(item.getIme() + " " + item.getPrezime());
						}
					}	
				};
			});
				
			tableViewKomisijskiIspit.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	
			tableViewKomisijskiIspit.setItems(komisijskiIspiti);
			
			if(ComboBoxOdaberiOcjenu !=null)IntStream.range(1, 6).forEach(ocjena->ComboBoxOdaberiOcjenu.getItems().add(ocjena));
			if(ComboBoxSati !=null)IntStream.range(0, 23).forEach(sat->ComboBoxSati.getItems().add(sat));
			if(ComboBoxMinute !=null)IntStream.range(0, 59).forEach(min->ComboBoxMinute.getItems().add(min));
			
			if(ComboBoxOdaberiStudenta !=null)studenti.stream().forEach(student->ComboBoxOdaberiStudenta.getItems().add(student.toString()));
			if(ComboBoxOdaberiPredmet !=null)predmeti.stream().forEach(predmet->ComboBoxOdaberiPredmet.getItems().add(predmet.toString()));
			
			if(ComboBoxOdaberiClanaKomisije1 !=null)profesori.stream().forEach(profesor->ComboBoxOdaberiClanaKomisije1.getItems().add(profesor.toString()));
			if(ComboBoxOdaberiClanaKomisije2 !=null)profesori.stream().forEach(profesor->ComboBoxOdaberiClanaKomisije2.getItems().add(profesor.toString()));
			if(ComboBoxOdaberiClanaKomisije3 !=null)profesori.stream().forEach(profesor->ComboBoxOdaberiClanaKomisije3.getItems().add(profesor.toString()));
		}

	public void pretragaKomisijskogIspita(ActionEvent event) {

			List<KomisijskiIspit> filteredList = komisijskiIspiti
					.filtered(komisijskiIspiti -> komisijskiIspiti.getPredmet().getNaziv().toLowerCase().contains(textFieldNaziv.getText().toLowerCase()))
					.filtered(komisijskiIspiti -> String.format("%s %s", komisijskiIspiti.getStudent().getIme(), komisijskiIspiti.getStudent().getPrezime()).toLowerCase().contains(textFieldStudent.getText().toLowerCase()))
					.filtered(komisijskiIspiti -> komisijskiIspiti.getOcjena().getBroj().toString().contains(textFieldOcjena.getText()))							
					.filtered(komisijskiIspiti -> 
					String.format("%s %s", komisijskiIspiti.getClanKomisije1().getIme(), komisijskiIspiti.getClanKomisije1().getPrezime())
					.toLowerCase().contains(textFieldPretragaClana.getText().toLowerCase()) 
					
					||
					
					String.format("%s %s", komisijskiIspiti.getClanKomisije2().getIme(), komisijskiIspiti.getClanKomisije2().getPrezime())
					.toLowerCase().contains(textFieldPretragaClana.getText().toLowerCase())

					||
					
					String.format("%s %s", komisijskiIspiti.getClanKomisije3().getIme(), komisijskiIspiti.getClanKomisije3().getPrezime())
					.toLowerCase().contains(textFieldPretragaClana.getText().toLowerCase()) 
					
					).stream().collect(Collectors.toList());
			
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
			
			tableViewKomisijskiIspit.setItems(FXCollections.observableList(filteredList));
		}
		
	public void spremiKomisijskiIspit() throws IOException {
			
			
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
		   	
		    int odabirClanaKomisije1 = ComboBoxOdaberiClanaKomisije1.getSelectionModel().getSelectedIndex();
		    int odabirClanaKomisije2 = ComboBoxOdaberiClanaKomisije2.getSelectionModel().getSelectedIndex();
		    int odabirClanaKomisije3 = ComboBoxOdaberiClanaKomisije3.getSelectionModel().getSelectedIndex();
			
			String message = "";
			if(odabirStudenta == -1) message += "\nstudent";
			if(odaberiPredmet == -1) message += "\npredmet";
			if(datum =="") message += "\ndatum ispita";
			if(odaberiSate < 0) message += "\nvrijeme sati";
			if(odaberiMinute < 0) message +="\nvrijeme minuta";
			if(odaberiOcjenu < 0) message +="\nocjena";
			if(odabirClanaKomisije1 == -1) message += "\nclanKomisije1";
			if(odabirClanaKomisije2 == -1) message += "\nclanKomisije2";
			if(odabirClanaKomisije3 == -1) message += "\nclanKomisije3";
			
			if (!message.equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("FATAL ERROR:");
				alert.setHeaderText("Pogreška, niste unijeli slijedeće kolone:");
				alert.setContentText(message);
				alert.showAndWait();
			}else {
				
				if( (odabirClanaKomisije1)==(odabirClanaKomisije2)|| 
					(odabirClanaKomisije1)==(odabirClanaKomisije3)||
					(odabirClanaKomisije2)==odabirClanaKomisije3){
						
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("FATAL ERROR:");
						alert.setHeaderText("Pogreška!");
						alert.setContentText("Odabrali ste jednog ili vise istih clanova komisije");
						alert.showAndWait();
					}else {
						Student student=studenti.get(odabirStudenta);
						Predmet predmet=predmeti.get(odaberiPredmet);
						LocalDateTime datumIVrijemeIspita = datePickerDatumIspita.getValue().atStartOfDay();
						datumIVrijemeIspita = datumIVrijemeIspita.plusHours(odaberiSate);
						datumIVrijemeIspita = datumIVrijemeIspita.plusMinutes(odaberiMinute);
						Ocjena ocjena = Datoteke.pretvoriBrojUOcjenu(odaberiOcjenu);
						Profesor clanKomisije1=profesori.get(odabirClanaKomisije1);
						Profesor clanKomisije2=profesori.get(odabirClanaKomisije2);
						Profesor clanKomisije3=profesori.get(odabirClanaKomisije3);
						
						KomisijskiIspit komisijskiIspit = new KomisijskiIspit(nextId(), predmet, student, ocjena, datumIVrijemeIspita,clanKomisije1,
								clanKomisije2,clanKomisije3);
							komisijskiIspiti.add(komisijskiIspit);
							Datoteke.noviKomisijskiIspitUDatoteci(komisijskiIspit);
					}				
			}	
		}

		private Long nextId() {
			OptionalLong maksimalniId = komisijskiIspiti.stream() 
			.mapToLong(komisijskiIspiti -> komisijskiIspiti.get_id()).max();
			return (maksimalniId.getAsLong() + 1);
	}
}


