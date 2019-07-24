package hr.java.vjezbe.sortiranje;

import java.util.Comparator;

import hr.java.vjezbe.entitet.Student;

public class StudentSorter implements Comparator<Student> {

	@Override
	public int compare(Student student1, Student student2) {

		if (student1.getPrezime().compareTo(student2.getPrezime()) > 0) {
			return 1;//ako je prvo prezime vece od drugoga onda ih zamjeni
		} else if (student1.getPrezime().compareTo(student2.getPrezime()) < 0) {
			return -1;//ako je prvo prezime manje od drugoga onda ostaje tak kak je.
		} else {//ako su prezimena ista onda provjeri po imenima
			if (student1.getIme().compareTo(student2.getIme()) > 0) {
				return 1;//ako je prvo ime vece od drugoga onda ih zamjeni
			} else if (student1.getIme().compareTo(student2.getIme()) < 0) {
				return -1;//ako je prvo ime manje od drugoga onda ostaje tak kak je.
			} else {
				return 0;//inace vrati mi jednake
			}
		
		}
	}

}
