package hr.java.vjezbe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.java.vjezbe.entitet.Ispiti;
import hr.java.vjezbe.entitet.KomisijskiIspit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.util.Datoteke;

public class TmlClassKomisijskiIspitIspis {

	public static void main(String[] args) {
		
		List<Profesor> profesori=Datoteke.dohvatiProfesore();
		List<Student> studenti=Datoteke.dohvatiStudente();
		List<Predmet> predmeti=Datoteke.dohvatiPredmete();
		List<Ispiti> ispiti=Datoteke.dohvatiIspite();
		List<KomisijskiIspit> komisijskiIspiti=Datoteke.dohvatiKomisijskeIspite();

		System.out.println();
		System.out.println("Studenti: ");
		int brojacStandardnihIzlazaka=0;
		
		for(KomisijskiIspit komisijskiIspit:komisijskiIspiti) {
			brojacStandardnihIzlazaka=0;
			
			for(Ispiti ispit:ispiti) {
				if(ispit.getPredmet().get_id()==komisijskiIspit.getPredmet().get_id() &&
				   ispit.getStudent().get_id()==komisijskiIspit.getStudent().get_id()) {
					brojacStandardnihIzlazaka++;
				}
			}
			System.out.println("Student "+ komisijskiIspit.getStudent().toString()+" je prije komisijskog ispita izasao na ispit "
					+ brojacStandardnihIzlazaka +" puta");
		}
		
		
		Map<Student, Integer> mapaStudentaSaNajcescimIzlaskomNaIspit=new HashMap<>();
		
		Integer brojacStudenata=0;
		
		for(KomisijskiIspit komisijskiIspit:komisijskiIspiti) {
			
			Student sviStudenti=komisijskiIspit.getStudent();
			if(mapaStudentaSaNajcescimIzlaskomNaIspit.containsKey(sviStudenti)) {
				brojacStudenata=mapaStudentaSaNajcescimIzlaskomNaIspit.get(sviStudenti);
				mapaStudentaSaNajcescimIzlaskomNaIspit.put(sviStudenti, brojacStudenata+1);
			}else {
				mapaStudentaSaNajcescimIzlaskomNaIspit.put(sviStudenti, 1);
			}		
		}
		
		int studentSaMaxIspita=0;
		//Studenti sa max izlaskom
		for(Student student:mapaStudentaSaNajcescimIzlaskomNaIspit.keySet()) {
			
			if(mapaStudentaSaNajcescimIzlaskomNaIspit.get(student)>studentSaMaxIspita) {
				studentSaMaxIspita=mapaStudentaSaNajcescimIzlaskomNaIspit.get(student);
			}
		}
		
		System.out.println("Studenti sa vise izlazaka: ");
		//Vise studenata sa max izlaskom
		for(Student student:mapaStudentaSaNajcescimIzlaskomNaIspit.keySet()) {	
			if(mapaStudentaSaNajcescimIzlaskomNaIspit.get(student)==studentSaMaxIspita) {
				System.out.println("\n"+student.toString()+"broj izlazaka: "+studentSaMaxIspita);
			}
		}
		
		}
}
