package hr.java.vjezbe.entitet;

import java.time.LocalDate;

/**
 * Predstavlja entitet studenta koji ima svoje osobne podatke i jmbag
 * 
 * @author domagoj
 *
 */
public class Student extends Osoba {

	private String m_jmbag;
	private LocalDate m_datumRodenja;
	
	private Ocjena m_ocjenaZavrsnogRada;
	private Ocjena m_ocjenaObraneZavrsnogRada;
	
	/**
	 * Sluzi za kreiranje objekta Studentkoji ima definirane parametre: jmbag ,datum rodenja, ime i prezime studenta.
	 * 
	 * @param jmbag
	 * @param datumRodenja
	 * @param ime
	 * @param prezime
	 */
	public Student(long id, String jmbag, LocalDate datumRodenja, String ime, String prezime) {
		super(id,ime,prezime);
		
		m_jmbag = jmbag;
		m_datumRodenja = datumRodenja;
	}

	public String getJmbag() {
		return m_jmbag;
	}

	public void setJmbag(String jmbag) {
		m_jmbag = jmbag;
	}

	public LocalDate getDatumRodenja() {
		return m_datumRodenja;
	}

	public void setDatumRodenja(LocalDate datumRodenja) {
		m_datumRodenja = datumRodenja;
	}
	
	public Ocjena getOcjenaZavrsnogRada() {
		return m_ocjenaZavrsnogRada;
	}

	public void setOcjenaZavrsnogRada(Ocjena ocjenaZavrsnogRada) {
		m_ocjenaZavrsnogRada = ocjenaZavrsnogRada;
	}

	public Ocjena getOcjenaObraneZavrsnogRada() {
		return m_ocjenaObraneZavrsnogRada;
	}

	public void setOcjenaObraneZavrsnogRada(Ocjena ocjenaObraneZavrsnogRada) {
		m_ocjenaObraneZavrsnogRada = ocjenaObraneZavrsnogRada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_datumRodenja == null) ? 0 : m_datumRodenja.hashCode());
		result = prime * result + ((m_jmbag == null) ? 0 : m_jmbag.hashCode());
		result = prime * result + ((m_ocjenaObraneZavrsnogRada == null) ? 0 : m_ocjenaObraneZavrsnogRada.hashCode());
		result = prime * result + ((m_ocjenaZavrsnogRada == null) ? 0 : m_ocjenaZavrsnogRada.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (m_datumRodenja == null) {
			if (other.m_datumRodenja != null)
				return false;
		} else if (!m_datumRodenja.equals(other.m_datumRodenja))
			return false;
		if (m_jmbag == null) {
			if (other.m_jmbag != null)
				return false;
		} else if (!m_jmbag.equals(other.m_jmbag))
			return false;
		if (m_ocjenaObraneZavrsnogRada != other.m_ocjenaObraneZavrsnogRada)
			return false;
		if (m_ocjenaZavrsnogRada != other.m_ocjenaZavrsnogRada)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  getIme() + " " + getPrezime() + " ["+m_jmbag+"]";
	}
	
}
