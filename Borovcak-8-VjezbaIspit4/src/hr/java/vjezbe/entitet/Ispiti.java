package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

/**
 * Predstavlja entitet ispita koji opisuju predmeti i studenti koji polazu
 * ispite.
 * 
 * @author domagoj
 *
 */
public class Ispiti extends Entitet {
	private Predmet m_predmet;
	private Student m_student;
	private Ocjena m_ocjena;
	private LocalDateTime m_datumIVrijeme;

	/**
	 * Sluzi za kreiranje objekta koji sadrzi definirane parametre: objekt klase
	 * predmet koji predstavlja odredeni predmet i podatak o broju predmeta, objekt
	 * klase studenti koji predstavlja odredenog studenta i podatak o broju
	 * studenta, ocejna, datum i vrijeme polaganja ispita.
	 * 
	 * @param predmet
	 * @param student
	 * @param ocjena
	 * @param datumIVrijeme
	 */
	public Ispiti(long id, Predmet predmet, Student student, Ocjena ocjena, LocalDateTime datumIVrijeme) {
		super(id);
		m_predmet = predmet;
		m_student = student;
		m_ocjena = ocjena;
		m_datumIVrijeme = datumIVrijeme;
	}

	public Predmet getPredmet() {
		return m_predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.m_predmet = predmet;
	}

	public Student getStudent() {
		return m_student;
	}

	public void setStudent(Student student) {
		m_student = student;
	}

	public Ocjena getOcjena() {
		return m_ocjena;
	}

	public void setOcjena(Ocjena ocjena) {
		m_ocjena = ocjena;
	}

	public LocalDateTime getDatumIVrijeme() {
		return m_datumIVrijeme;
	}

	public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
		m_datumIVrijeme = datumIVrijeme;
	}

}
