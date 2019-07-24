package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet Osoba koju nasljeđuju klase Student i Profesor.
 * 
 * @author domagoj
 *
 */
public abstract class Osoba extends Entitet {

	private String m_ime;
	private String m_prezime;

	/**
	 * Ima svrhu roditelja drugim Konkretim klasama, ne moze imati objekte.
	 * 
	 * @param ime
	 * @param prezime
	 */
	public Osoba(long id, String ime, String prezime) {
		super(id);
		m_ime = ime;
		m_prezime = prezime;
	}

	public String getIme() {
		return m_ime;
	}

	public void setIme(String ime) {
		m_ime = ime;
	}

	public String getPrezime() {
		return m_prezime;
	}

	public void setPrezime(String prezime) {
		m_prezime = prezime;
	}
}
