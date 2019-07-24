package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import hr.java.vjezbe.sortiranje.StudentSorter;



/**
 * Predstavlja entitet predmeta koji ima svoje profesore i studente,te sifru, naziv i broj ECTS bodova.
 * 
 * @author domagoj
 *
 */
public class Predmet extends Entitet {
	


	private String m_sifra;
	private String m_naziv;
	private Integer m_brojECTSBodova;
	private Profesor m_nositelj;
	private Set<Student> m_studenti;
	
	/**
	 * Sluzi za kreiranje objekta koji sadrzi definirane parametre: objekt klase profesor koji predstavlja odredenog profesora,
	 * objekt klase studenti koji predstavlja odredenog studenta i podatak o broju studenta, sifra, naziv i broj ECTS bodova. 
	 * 
	 * @param sifra
	 * @param naziv
	 * @param brojECTSBodova
	 * @param nositelj
	 * @param studenti
	 */
	public Predmet(long id, String sifra, String naziv, Integer brojECTSBodova, Profesor nositelj, Set<Student> studenti) {
		super(id);
		m_sifra = sifra;
		m_naziv = naziv;
		m_brojECTSBodova = brojECTSBodova;
		m_nositelj = nositelj;
		m_studenti = studenti;
	}

	public String getSifra() {
		return m_sifra;
	}

	public void setSifra(String sifra) {
		m_sifra = sifra;
	}

	public String getNaziv() {
		return m_naziv;
	}

	public void setNaziv(String naziv) {
		m_naziv = naziv;
	}

	public Integer getBrojECTSBodova() {
		return m_brojECTSBodova;
	}

	public void setBrojECTSBodova(Integer brojECTSBodova) {
		m_brojECTSBodova = brojECTSBodova;
	}

	public Profesor getNositelj() {
		return m_nositelj;
	}

	public void setNositelj(Profesor nositelj) {
		m_nositelj = nositelj;
	}

	public Set<Student> getStudenti() {
		return m_studenti;
	}

	public void setStudenti(Set<Student> studenti) {
		m_studenti = studenti;
	}
	
	public void addStudent(Student student) {
		m_studenti.add(student);
	}
	
	public void ispisiSveStudente() {
		
		List<Student> listaStudenta = new ArrayList<>();
		long trenutnoVrijeme=System.currentTimeMillis();	
		listaStudenta=(List<Student>)m_studenti.stream().filter(m_studenti-> getStudenti().equals(m_studenti)).collect(Collectors.toList());	
		long vrijemeLambde=System.currentTimeMillis()-trenutnoVrijeme;
		
		trenutnoVrijeme=System.currentTimeMillis();
		for (Student student : m_studenti) {
			listaStudenta.add(student);
		}
		long vrijemeForPetlje=System.currentTimeMillis()-trenutnoVrijeme;
		
		System.out.println("Vrijeme lambde: "+ vrijemeLambde +" ms");
		System.out.println("Vrijeme for petlje: "+ vrijemeForPetlje +" ms");
	
		Collections.sort(listaStudenta, new StudentSorter());
		for(Student student:listaStudenta) {
			System.out.println(student.toString());
		}
	}

	@Override
	public String toString() {
		return m_naziv + " (" + m_sifra + ")";
	}
	
}
