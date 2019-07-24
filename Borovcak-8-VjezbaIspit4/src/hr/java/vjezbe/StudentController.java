package hr.java.vjezbe;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.util.Datoteke;
import hr.java.vjezbe.util.Konstante;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class StudentController {

	@FXML
	private TextField textFieldIme;

	@FXML
	private TextField textFieldPrezime;

	@FXML
	private TextField textFieldJMBAG;

	@FXML
	private DatePicker datePickerDatumRođenja;

	@FXML
	private Button buttonPretrazi;

	@FXML
	private Button buttonSpremi;

	@FXML
	private TableView<Student> tableViewStudent;

	@FXML
	private TableColumn<Student, String> tableColumnIme;

	@FXML
	private TableColumn<Student, String> tableColumnPrezime;

	@FXML
	private TableColumn<Student, String> tableColumnJMBAG;

	@FXML
	private TableColumn<Student, String> tableColumnDatumRođenja;

	private ObservableList<Student> studenti;

	@FXML
	public void initialize() {

		tableColumnIme.setCellValueFactory(new PropertyValueFactory<Student, String>("ime"));
		tableColumnIme.setStyle("-fx-alignment: CENTER;");
		
		tableColumnPrezime.setCellValueFactory(new PropertyValueFactory<Student, String>("prezime"));
		tableColumnPrezime.setStyle("-fx-alignment: CENTER;");
		
		tableColumnJMBAG.setCellValueFactory(new PropertyValueFactory<Student, String>("jmbag"));
		tableColumnJMBAG.setStyle("-fx-alignment: CENTER;");

		tableColumnDatumRođenja.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student) {
						SimpleStringProperty property = new SimpleStringProperty();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA);
						property.setValue(student.getValue().getDatumRodenja().format(formatter));
						return property;
					}
				});
		tableColumnDatumRođenja.setStyle("-fx-alignment: CENTER;");

		studenti = FXCollections.observableList(Datoteke.dohvatiStudente());
		
		tableViewStudent.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableViewStudent.setItems(studenti);
	}

	public void pretragaStudenata(ActionEvent event) {

		List<Student> filteredList = studenti
				.filtered(student -> student.getIme().toLowerCase().contains(textFieldIme.getText().toLowerCase()))
				.filtered(student -> student.getPrezime().toLowerCase().contains(textFieldPrezime.getText().toLowerCase()))
				.filtered(student -> student.getJmbag().toLowerCase().contains(textFieldJMBAG.getText().toLowerCase()))
				.stream().collect(Collectors.toList());

		if (datePickerDatumRođenja.getValue() != null) {
			filteredList = filteredList.stream()
					.filter(student -> student.getDatumRodenja()
					.format(DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA)).contains(datePickerDatumRođenja.getValue()
					.format(DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA)))).collect(Collectors.toList());
			datePickerDatumRođenja.setValue(null);
		}

		tableViewStudent.setItems(FXCollections.observableList(filteredList));
	}

	public void spremiStudenta() throws IOException {

		String unosIme = textFieldIme.getText().trim();
		String unosPrezime = textFieldPrezime.getText().trim();
		String unosJMBAG = textFieldJMBAG.getText().trim();
		LocalDate unosDatumRodenja = datePickerDatumRođenja.getValue();

		String message = "";
		if (unosIme == null || unosIme.length() == 0) message += "\nime";
		if (unosPrezime == null || unosPrezime.length() == 0)message += "\nprezime";
		if (unosJMBAG == null || unosJMBAG.length() == 0)message += "\njmbag";
		if (unosDatumRodenja == null || unosDatumRodenja.getMonthValue() == 0)message += "\ndatumRodenja";

		if (!message.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("FATAL ERROR:");
			alert.setHeaderText("Pogreska, niste unijeli slijedece kolone:");
			alert.setContentText(message);
			alert.showAndWait();
		} else {
			OptionalLong maksimalniId = studenti.stream().mapToLong(student -> student.get_id()).max();
			Student newStudent = new Student(maksimalniId.getAsLong() + 1, unosJMBAG, unosDatumRodenja, unosIme,
					unosPrezime);	
			
			System.out.println(newStudent.getDatumRodenja());

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA);

			System.out.println(String.valueOf(newStudent.get_id()));
			System.out.println(newStudent.getJmbag());
			System.out.println(formatter.format(newStudent.getDatumRodenja()));
			System.out.println(newStudent.getIme());
			System.out.println(newStudent.getPrezime());

			studenti.add(newStudent);
			Datoteke.noviStudentUDatoteci(studenti);
			tableViewStudent.refresh();
		}
	}
}
