package hr.java.vjezbe.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hr.java.vjezbe.entitet.Ispiti;
import hr.java.vjezbe.entitet.KomisijskiIspit;
import hr.java.vjezbe.entitet.Ocjena;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import javafx.collections.ObservableList;

public class Datoteke {

	public static final String FILES_PATH = "dat";
	public static final String FILE_NAME_PROFESORI = "Profesori.txt";
	public static final String FILE_NAME_STUDENTI = "Studenti.txt";
	public static final String FILE_NAME_PREDMETI = "Predmeti.txt";
	public static final String FILE_NAME_ISPITI = "Ispiti.txt";
	public static final String FILE_NAME_MJESTO_RODJENJA = "mjestaRodjenja.txt";
	public static final String FILE_NAME_OBRAZOVNE_USTANOVE = "Obrazovne_ustanove.txt";
	public static final String FILE_NAME_KOMISIJSKI_ISPITI = "KomisijskiIspiti.txt";

	private static List<String> ucitajDatoteku(String path, String imeDatoteke) {
		Path pathOfFile = Paths.get(path, imeDatoteke);
		List<String> listaIzDatoteke = new ArrayList<>();
		Charset charset = Charset.forName("UTF-8");
		try {
			listaIzDatoteke = Files.readAllLines(pathOfFile, charset);
		} catch (IOException ex) {
			System.out.println("Pogreska kod citanja datoteke " + imeDatoteke);
		}
		return listaIzDatoteke;
	}

	public static List<Profesor> dohvatiProfesore() {

		List<Profesor> profesoriIzDatoteke = new ArrayList<>();
		System.out.println("Ucitavanje profesora...");
		List<String> ucitavanje = ucitajDatoteku(FILES_PATH, FILE_NAME_PROFESORI);
		for (int i = 0; i < ucitavanje.size(); i += 5) {
			Long id = Long.parseLong(ucitavanje.get(i));
			String ime = ucitavanje.get(i + 1);
			String prezime = ucitavanje.get(i + 2);
			String sifra = ucitavanje.get(i + 3);
			String titula = ucitavanje.get(i + 4);
			Profesor profesor = new Profesor(id, ime, prezime, sifra, titula);
			profesoriIzDatoteke.add(profesor);
		}
		return profesoriIzDatoteke;
	}

	public static void noviProfesorUDatoteci(ObservableList<Profesor> profesori) throws IOException {

		File fileOut = new File(FILES_PATH + "\\" + FILE_NAME_PROFESORI);
		FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

		for (int i = 0; i < profesori.size(); i++) {
			Profesor profesor = profesori.get(i);
			List<String> listaStringova = dohvatiMiListuProfesora(profesor);

			for (int j = 0; j < listaStringova.size(); j++) {
				bufferedWriter.write(listaStringova.get(j));
				if (j != listaStringova.size() - 1 || i != profesori.size() - 1) {
					bufferedWriter.newLine();
				}
			}
		}
		bufferedWriter.close();
	}

	private static List<String> dohvatiMiListuProfesora(Profesor profesor) {
		List<String> listaPodatakaProfesora = new ArrayList<>();

		listaPodatakaProfesora.add(String.valueOf(profesor.get_id()));
		listaPodatakaProfesora.add(profesor.getIme());
		listaPodatakaProfesora.add(profesor.getPrezime());
		listaPodatakaProfesora.add(profesor.getSifra());
		listaPodatakaProfesora.add(profesor.getTitula());

		return listaPodatakaProfesora;
	}

	public static List<Student> dohvatiStudente() {

		List<Student> studentIzDatoteke = new ArrayList<>();
		System.out.println("Ucitavanje studenata...");
		List<String> ucitavanje = ucitajDatoteku(FILES_PATH, FILE_NAME_STUDENTI);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA);

		for (int i = 0; i < ucitavanje.size(); i += 5) {
			Long id = Long.parseLong(ucitavanje.get(i));
			String ime = ucitavanje.get(i + 1);
			String prezime = ucitavanje.get(i + 2);
			String jmbag = ucitavanje.get(i + 3);
			LocalDate datumRodenja = LocalDate.parse(ucitavanje.get(i + 4), formatter);
			Student student = new Student(id, jmbag, datumRodenja, ime, prezime);
			studentIzDatoteke.add(student);
		}
		return studentIzDatoteke;
	}

	public static void noviStudentUDatoteci(ObservableList<Student> studenti) throws IOException {
		File fileOut = new File(FILES_PATH + "\\" + FILE_NAME_STUDENTI);
		FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

		for (int i = 0; i < studenti.size(); i++) {
			Student student = studenti.get(i);
			List<String> listaStringova = dohvatiMiListuStudenata(student);

			for (int j = 0; j < listaStringova.size(); j++) {

				bufferedWriter.write(listaStringova.get(j));
				if (j != listaStringova.size() - 1 || i != studenti.size() - 1) {
					bufferedWriter.newLine();
				}
			}
		}
		bufferedWriter.close();
		fileOutputStream.close();
	}

	public static List<String> dohvatiMiListuStudenata(Student studenti) {
		List<String> listaPodatakaStudenata = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA);

		listaPodatakaStudenata.add(String.valueOf(studenti.get_id()));
		listaPodatakaStudenata.add(studenti.getIme());
		listaPodatakaStudenata.add(studenti.getPrezime());
		listaPodatakaStudenata.add(studenti.getJmbag());
		listaPodatakaStudenata.add(formatter.format(studenti.getDatumRodenja()));

		return listaPodatakaStudenata;
	}

	public static List<Predmet> dohvatiPredmete() {
		List<Predmet> predmetiIzDatoteke = new ArrayList<>();
		List<Student> studenti = dohvatiStudente();
		List<Profesor> profesori = dohvatiProfesore();
		System.out.println("Ucitavanje predmeta...");
		List<String> ucitavanje = ucitajDatoteku(FILES_PATH, FILE_NAME_PREDMETI);

		for (int i = 0; i < ucitavanje.size(); i += 6) {
			Long id = Long.parseLong(ucitavanje.get(i));
			String sifra = ucitavanje.get(i + 1);
			String naziv = ucitavanje.get(i + 2);
			Integer brojECTSBodova = Integer.parseInt(ucitavanje.get(i + 3));
			Profesor profesor = getProfesorById(profesori, Long.parseLong(ucitavanje.get(i + 4)));
			Set<Student> studentiSet = getSetOfStudentsByStringOfIds(studenti, ucitavanje.get(i + 5));
			Predmet predmet = new Predmet(id, sifra, naziv, brojECTSBodova, profesor, studentiSet);
			predmetiIzDatoteke.add(predmet);
		}
		return predmetiIzDatoteke;
	}

	public static void noviPredmetUDatoteci(ObservableList<Predmet> predmeti) throws IOException {

		File fileOut = new File(FILES_PATH + "\\" + FILE_NAME_PREDMETI);
		FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

		for (int i = 0; i < predmeti.size(); i++) {
			Predmet predmet = predmeti.get(i);
			List<String> listaStringova = dohvatiMiListuPredmeta(predmet);

			for (int j = 0; j < listaStringova.size(); j++) {
				bufferedWriter.write(listaStringova.get(j));
				if (j != listaStringova.size() - 1 || i != predmeti.size() - 1) {
					bufferedWriter.newLine();
				}
			}
		}
		bufferedWriter.close();
	}

	public static List<String> dohvatiMiListuPredmeta(Predmet predmeti) {
		List<String> listaPodatakaPredmeta = new ArrayList<>();

		String listaStudenata = "";
		if (predmeti.getStudenti() != null) {
			for (Student student : predmeti.getStudenti())
				listaStudenata += String.valueOf(student.get_id()) + "";
			listaStudenata = listaStudenata.trim();
		}

		listaPodatakaPredmeta.add(String.valueOf(predmeti.get_id()));
		listaPodatakaPredmeta.add(predmeti.getSifra());
		listaPodatakaPredmeta.add(predmeti.getNaziv());
		listaPodatakaPredmeta.add(String.valueOf(predmeti.getBrojECTSBodova()));
		listaPodatakaPredmeta.add(String.valueOf(predmeti.getNositelj().get_id()));
		listaPodatakaPredmeta.add(listaStudenata);

		return listaPodatakaPredmeta;

	}

	private static Profesor getProfesorById(List<Profesor> profesori, Long id) {
		Profesor dohvaceni = null;
		for (Profesor profesor : profesori) {
			if (profesor.get_id() == id)
				dohvaceni = profesor;
		}
		return dohvaceni;
	}

	public static Student getStudentById(List<Student> studenti, Long id) {
		Student dohvaceni = null;
		for (Student student : studenti) {
			if (student.get_id() == id)
				dohvaceni = student;
		}
		return dohvaceni;
	}

	public static Set<Student> getSetOfStudentsByStringOfIds(List<Student> studenti, String string) {
		Set<Student> studentIzStringa = new HashSet<>();
		String[] studentIds = string.split(" ");
		for (String studentId : studentIds) {
			Student student = getStudentById(studenti, Long.parseLong(studentId));
			if (student != null)
				studentIzStringa.add(student);
		}
		return studentIzStringa;
	}

	public static List<Ispiti> dohvatiIspite() {
		List<Ispiti> ispitiIzDatoteke = new ArrayList<>();
		System.out.println("Ucitavanje ispita...");
		List<String> ucitavanje = ucitajDatoteku(FILES_PATH, FILE_NAME_ISPITI);

		List<Predmet> predmetiIzDatoteke = dohvatiPredmete();
		List<Student> studenti = dohvatiStudente();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA_I_VREMENA);

		for (int i = 0; i < ucitavanje.size(); i += 5) {
			Long id = Long.parseLong(ucitavanje.get(i));
			Predmet odabraniPredmet = getPredmetById(predmetiIzDatoteke, Long.parseLong(ucitavanje.get(i + 1)));
			Student odabraniStudent = getStudentById(studenti, Long.parseLong(ucitavanje.get(i + 2)));
			Ocjena ocjena = pretvoriBrojUOcjenu(Integer.parseInt(ucitavanje.get(i + 3)));
			LocalDateTime datumIVrijeme = LocalDateTime.parse(ucitavanje.get(i + 4), formatter);
			Ispiti ispiti = new Ispiti(id, odabraniPredmet, odabraniStudent, ocjena, datumIVrijeme);
			ispitiIzDatoteke.add(ispiti);
		}
		return ispitiIzDatoteke;
	}
	

	public static void noviIspitUDatoteci(Ispiti ispiti) throws IOException {

		File fileOut = new File(FILES_PATH + "\\" + FILE_NAME_ISPITI);
		FileOutputStream fileOutputStream = new FileOutputStream(fileOut, true);
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
		List<String> listaStringova= dohvatiMiListuIspita(ispiti);
		
		// for (String st : listaStringova) System.out.println(st);
		
		bufferedWriter.newLine();
		
		for(int i=0;i<listaStringova.size();i++) {
			bufferedWriter.write(listaStringova.get(i));
			if(i != listaStringova.size() -1 )bufferedWriter.newLine();
		}
		bufferedWriter.close();
	}
	
	public static List<String> dohvatiMiListuIspita(Ispiti ispiti) {
		List<String> listaPodatakaIspita = new ArrayList<>();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA_I_VREMENA);

		listaPodatakaIspita.add(String.valueOf(ispiti.get_id()));
		listaPodatakaIspita.add(String.valueOf(ispiti.getPredmet().get_id()));
		listaPodatakaIspita.add(String.valueOf(ispiti.getStudent().get_id()));
		listaPodatakaIspita.add(String.valueOf(ispiti.getOcjena().getBroj()));
		listaPodatakaIspita.add(formatter.format(ispiti.getDatumIVrijeme()));
		
		return listaPodatakaIspita;

	}
	
//KOMISIJSKI ISPITI//KOMISIJSKI ISPITI//KOMISIJSKI ISPITI//KOMISIJSKI ISPITI//KOMISIJSKI ISPITI//KOMISIJSKI ISPITI//KOMISIJSKI ISPITI//KOMISIJSKI ISPITI
	
	public static List<KomisijskiIspit> dohvatiKomisijskeIspite() {
		List<KomisijskiIspit> komisijskiIspitiIzDatoteke = new ArrayList<>();
		System.out.println("Ucitavanje komisijskih ispita...");
		List<String> ucitavanje = ucitajDatoteku(FILES_PATH, FILE_NAME_KOMISIJSKI_ISPITI);

		List<Predmet> predmetiIzDatoteke = dohvatiPredmete();
		List<Student> studentiIzDatoteke  = dohvatiStudente();
		List<Profesor> profesoriIzDatoteke =dohvatiProfesore();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA_I_VREMENA);

		for (int i = 0; i < ucitavanje.size(); i += 8) {
			Long id = Long.parseLong(ucitavanje.get(i));
			Predmet odabraniPredmet = getPredmetById(predmetiIzDatoteke, Long.parseLong(ucitavanje.get(i + 1)));
			Student odabraniStudent = getStudentById(studentiIzDatoteke, Long.parseLong(ucitavanje.get(i + 2)));
			Ocjena ocjena = pretvoriBrojUOcjenu(Integer.parseInt(ucitavanje.get(i + 3)));
			LocalDateTime datumIVrijeme = LocalDateTime.parse(ucitavanje.get(i + 4), formatter);
			Profesor clanKomisije1=getProfesorById(profesoriIzDatoteke, Long.parseLong(ucitavanje.get(i+5)));
			Profesor clanKomisije2=getProfesorById(profesoriIzDatoteke, Long.parseLong(ucitavanje.get(i+6)));
			Profesor clanKomisije3=getProfesorById(profesoriIzDatoteke, Long.parseLong(ucitavanje.get(i+7)));
			KomisijskiIspit komisijskiIspiti = new KomisijskiIspit(id, odabraniPredmet, odabraniStudent, ocjena, datumIVrijeme,
					clanKomisije1,clanKomisije2,clanKomisije3);
			komisijskiIspitiIzDatoteke.add(komisijskiIspiti);
		}
		return komisijskiIspitiIzDatoteke;
	}
	
	public static void noviKomisijskiIspitUDatoteci(KomisijskiIspit komisijskiIspiti) throws IOException {

		File fileOut = new File(FILES_PATH + "\\" + FILE_NAME_KOMISIJSKI_ISPITI);
		FileOutputStream fileOutputStream = new FileOutputStream(fileOut, true);
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
		List<String> listaStringova= dohvatiMiListuKomisijskihIspita(komisijskiIspiti);
		
		bufferedWriter.newLine();
		
		for(int i=0;i<listaStringova.size();i++) {
			bufferedWriter.write(listaStringova.get(i));
			if(i != listaStringova.size() -1 )bufferedWriter.newLine();
		}
		bufferedWriter.close();
	}
	
	public static List<String> dohvatiMiListuKomisijskihIspita(KomisijskiIspit komisijskiIspiti) {
		List<String> listaPodatakaKomisijskihIspita = new ArrayList<>();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern(Konstante.FORMAT_DATUMA_I_VREMENA);

		listaPodatakaKomisijskihIspita.add(String.valueOf(komisijskiIspiti.get_id()));
		listaPodatakaKomisijskihIspita.add(String.valueOf(komisijskiIspiti.getPredmet().get_id()));
		listaPodatakaKomisijskihIspita.add(String.valueOf(komisijskiIspiti.getStudent().get_id()));
		listaPodatakaKomisijskihIspita.add(String.valueOf(komisijskiIspiti.getOcjena().getBroj()));
		listaPodatakaKomisijskihIspita.add(formatter.format(komisijskiIspiti.getDatumIVrijeme()));
		listaPodatakaKomisijskihIspita.add(String.valueOf(komisijskiIspiti.getClanKomisije1().get_id()));
		listaPodatakaKomisijskihIspita.add(String.valueOf(komisijskiIspiti.getClanKomisije2().get_id()));
		listaPodatakaKomisijskihIspita.add(String.valueOf(komisijskiIspiti.getClanKomisije3().get_id()));
		
		return listaPodatakaKomisijskihIspita;

	}
	
	public static Predmet getPredmetById(List<Predmet> predmeti, Long id) {
		Predmet dohvaceni = null;
		for (Predmet predmet : predmeti) {
			if (predmet.get_id() == id)
				dohvaceni = predmet;
		}
		return dohvaceni;
	}

	public static Ocjena pretvoriBrojUOcjenu(Integer ocjena) {
		switch (ocjena) {
		case 1:
			return Ocjena.OCJENA_NEDOVOLJAN;
		case 2:
			return Ocjena.OCJENA_DOVOLJAN;
		case 3:
			return Ocjena.OCJENA_DOBAR;
		case 4:
			return Ocjena.OCJENA_VRLO_DOBAR;
		case 5:
			return Ocjena.OCJENA_IZVRSTAN;
		default:
			return Ocjena.NEMA_OCJENE;
		}
	}

}
