package hr.java.vjezbe;

import java.io.IOException;
import java.util.Arrays;

import java.util.HashSet;
import java.util.List;
import java.util.OptionalLong;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.util.Datoteke;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class PredmetController {
	
	@FXML
	private TextField textFieldSifra;

	@FXML
	private TextField textFieldNaziv;
	
	@FXML
	private TextField textFieldBrojEctsBodova;;
	
	@FXML
	private TextField textFieldNositelj;

	@FXML
	private ComboBox<String> comboBoxNositelj;

	@FXML
	private ComboBox<Integer> comboBoxBrojECTSBodova;
	
	@FXML
	private MenuButton menuButtonStudenti;

	@FXML
	private Button buttonPretrazi;
	
	@FXML
	private Button buttonSpremi;

	@FXML
	private TableView<Predmet> tableViewPredmet;

	@FXML
	private TableColumn<Predmet, String> tableColumnSifra;

	@FXML
	private TableColumn<Predmet, String> tableColumnNaziv;

	@FXML
	private TableColumn<Predmet, Integer> tableColumnBrojECTSBodova;

	@FXML
	private TableColumn<Predmet, String> tableColumnNositelj;

	private ObservableList<Predmet> predmeti;
	
	private ObservableList<Profesor> profesori;
	
	private ObservableList<Student> studenti;
	
	private Set<String> odabraniStudenti= new HashSet<String>();
	
	@FXML
	public void initialize() {
		
		predmeti = FXCollections.observableList(Datoteke.dohvatiPredmete());
		profesori=FXCollections.observableList(Datoteke.dohvatiProfesore());
		studenti=FXCollections.observableList(Datoteke.dohvatiStudente());
		System.out.println(profesori.size());
		
		
		tableColumnSifra.setCellValueFactory(new PropertyValueFactory<Predmet, String>("sifra"));
		tableColumnSifra.setStyle("-fx-alignment: CENTER;");
		
		tableColumnNaziv.setCellValueFactory(new PropertyValueFactory<Predmet, String>("naziv"));
		tableColumnNaziv.setStyle("-fx-alignment: CENTER;");
		
		tableColumnBrojECTSBodova.setCellValueFactory(new PropertyValueFactory<Predmet, Integer>("brojECTSBodova"));
		tableColumnBrojECTSBodova.setStyle("-fx-alignment: CENTER;");
	
		tableColumnNositelj.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Predmet, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Predmet, String> predmet) {
						SimpleStringProperty property = new SimpleStringProperty();
						Profesor nositelj = predmet.getValue().getNositelj();
						if (nositelj == null) {
							property.setValue("");
							return property;
						}
						String ime = (nositelj.getIme() != null) ? nositelj.getIme() : "";
						String prezime = (nositelj.getPrezime() != null) ? nositelj.getPrezime() : "";
						property.setValue(String.format("%s %s", ime, prezime));
						return property;
					}
				});
		tableColumnNositelj.setStyle("-fx-alignment: CENTER;");
		
		tableViewPredmet.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableViewPredmet.setItems(predmeti);
		
		ObservableList<String> listaProfesora=FXCollections.observableArrayList();
		profesori.stream().forEach(student->listaProfesora.add(student.getPrezime() + " " + student.getIme()));
		
		if(comboBoxNositelj!=null)profesori.stream().forEach(profesor->comboBoxNositelj.getItems().add(profesor.toString()));
		if(comboBoxBrojECTSBodova!=null)IntStream.rangeClosed(1, 7).forEach(ects->comboBoxBrojECTSBodova.getItems().add(ects));
		
		odabraniStudenti=new HashSet<String>();
		
		if(menuButtonStudenti !=null) {
			EventHandler<ActionEvent>event=new EventHandler<>() {
				public void handle(ActionEvent e)
				{
					String odabrano=((CheckMenuItem)e.getSource()).getText();
					List<String>temp=Arrays.asList(odabrano.split("\\["));
					String imePrezime=temp.get(0);
					if(((CheckMenuItem)e.getSource()).isSelected())odabraniStudenti.add(imePrezime);
					else odabraniStudenti.remove(imePrezime);
					String menu=String.join(", ", odabraniStudenti);
					menuButtonStudenti.setText(menu);
				}
			};
			studenti.stream().forEach(studnt->{
				CheckMenuItem item= new CheckMenuItem(studnt.toString());
				item.setOnAction(event);
				menuButtonStudenti.getItems().add(item);
			});
		}
		
	}

	public void pretragaPredmeta(ActionEvent event) {

		String naziv=textFieldNaziv.getText();
		String sifra=textFieldSifra.getText();
		String nositelj=textFieldNositelj.getText();
		String ects=textFieldBrojEctsBodova.getText();
		
		List<Predmet> filteredList = predmeti
				.filtered(predmet -> predmet.getSifra().toLowerCase().contains(sifra.toLowerCase()))
				.filtered(predmet -> predmet.getNaziv().toLowerCase().contains(naziv.toLowerCase()))
				.filtered(predmet -> ects ==null || ects.equals("") || 
						  predmet.getBrojECTSBodova() == Integer.parseInt(ects))
				.filtered(predmet -> (predmet.getNositelj().getIme() + " " + predmet.getNositelj().getPrezime())
				.toLowerCase().contains(nositelj.toLowerCase())).stream().collect(Collectors.toList());
		
		tableViewPredmet.setItems(FXCollections.observableList(filteredList));
	}
	
		public void spremiPredmet() throws IOException {
		
		String unosSifra = textFieldSifra.getText().trim();
		String unosNaziv = textFieldNaziv.getText().trim();
		
		int odabirNositelj=comboBoxNositelj.getSelectionModel().getSelectedIndex();
		System.out.println("Nositelj");
		
		int odaberiEcts=(comboBoxBrojECTSBodova.getSelectionModel().getSelectedIndex()>=0) ? 
				comboBoxBrojECTSBodova.getSelectionModel().getSelectedItem(): -1;
				
		int odabraniStudent= (odabraniStudenti !=null) ? odabraniStudenti.size() :0 ;
		
		String message = "";
		if(unosNaziv == null || unosNaziv.length() == 0) message += "\nsifra";
		if(unosSifra == null || unosSifra.length() == 0) message += "\nime";
		if(odabirNositelj == -1) message += "\nprezime";
		if(odaberiEcts == -1) message += "\ntitula";
		if(odaberiEcts == 0) message +="\nlista studenata";
		
		if (!message.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("FATAL ERROR:");
			alert.setHeaderText("Pogreška, niste unijeli slijedeće kolone:");
			alert.setContentText(message);
			alert.showAndWait();
		}else {
			Profesor profesor=profesori.get(odabirNositelj);
			Set<Student> setStudenata = dohvacanjeStudentaMenuButton();
			Predmet predmet = new Predmet(nextId(), unosSifra, unosNaziv, odaberiEcts, profesor,setStudenata);
			predmeti.add(predmet);
			Datoteke.noviPredmetUDatoteci(predmeti);
		}	
	}

		private Set<Student> dohvacanjeStudentaMenuButton() {
			Set<Student> setStudenata= new HashSet<Student>();
			for(MenuItem cmi:menuButtonStudenti.getItems()) {
				CheckMenuItem checkMnuItem=(CheckMenuItem)cmi;
				if(checkMnuItem.isSelected()) {
					String string=checkMnuItem.getText();
					for(Student stud:studenti) {
						if(stud.toString().equals(string))setStudenata.add(stud);
					}
				}
			}
			return setStudenata;
	}
		

		private Long nextId() {
			OptionalLong maksimalniId = predmeti.stream() 
			.mapToLong(predmet -> predmet.get_id()).max();
			return (maksimalniId.getAsLong() + 1);
		}
		
		private Profesor getProfesorById(Long id) {
			Profesor dohvaceni = null;
			for (Profesor profesor : profesori) {
				if (profesor.get_id() == id)
					dohvaceni = profesor;
			}
			return dohvaceni;
		}

}
