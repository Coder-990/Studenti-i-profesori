package hr.java.vjezbe.entitet;

import java.util.List;

/**
 * Predstavlja entitet Obrazovne ustanove koju nasljeđuju klase Fakultet racunarstva i Veleuciliste jave.
 * 
 * @author domagoj
 *
 */
public abstract class ObrazovnaUstanova extends Entitet{

	private String m_nazivObrazovneUstanove; 
	private List<Predmet> m_predmet;
	private List<Profesor> m_profesor;
	private List<Student> m_student;
	private List<Ispit> m_ispit;
	
	/**
	 * Sluzi za odredivanje objekta obrazovne ustanove, koji sadrzi definirane parametre: naziv ustanove, lista svih predmeta,
	 * profesora, studenta, ispita.
	 * 
	 * @param nazivObrazovneUstanove - naziv ustanove
	 * @param predmet - lista svih predmeta
	 * @param profesori - lista svih profesora
	 * @param studenti - lista svih studenata
	 * @param ispiti - lista svih ispita 
	 */
	public ObrazovnaUstanova(long id, String nazivObrazovneUstanove, List<Predmet> predmet,
			List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
		super(id);
		m_nazivObrazovneUstanove = nazivObrazovneUstanove;
		m_predmet = predmet;
		m_profesor = profesori;
		m_student = studenti;
		m_ispit = ispiti;
	}

	public String getNazivObrazovneUstanove() {
		return m_nazivObrazovneUstanove;
	}

	public void setNazivObrazovneUstanove(String nazivObrazovneUstanove) {
		m_nazivObrazovneUstanove = nazivObrazovneUstanove;
	}

	public List<Predmet> getPredmet() {
		return m_predmet;
	}

	public void setPredmet(List<Predmet> predmet) {
		m_predmet = predmet;
	}

	public List<Profesor> getProfesor() {
		return m_profesor;
	}

	public void setProfesor(List<Profesor> profesor) {
		m_profesor = profesor;
	}

	public List<Student> getStudent() {
		return m_student;
	}

	public void setStudent(List<Student> student) {
		m_student = student;
	}

	public List<Ispit> getIspit() {
		return m_ispit;
	}

	public void setIspit(List<Ispit> ispit) {
		m_ispit = ispit;
	}
	
	/**
	 * Apstrakna metoda za odredivanje najuspjesnijeg studenta u godini
	 * 
	 * @param godina
	 * @return Student najuspjesniji student
	 */
	public abstract Student odrediNajuspjesnijegStudentaNaGodini(Integer godina); 
}
