package hr.java.vjezbe.entitet;



/**
 * Predstavlja entitet profesora koji ima svoje osobne podatke, sifru i titulu.
 * 
 * @author domagoj
 *
 */
public class Profesor extends Osoba {

	

	private String m_sifra;
	private String m_titula;
	
	/**
	 * Sluzi za kreiranje objekta koji ima definirane parametre: ime, prezime, sifru i titulu profesora.
	 * 
	 * @param ime
	 * @param prezime
	 * @param sifra
	 * @param titula
	 */
	public Profesor(long id, String ime, String prezime, String sifra, String titula) {
		super(id,ime, prezime);
		m_sifra = sifra;
		m_titula = titula;
	}

	public String getSifra() {
		return m_sifra;
	}

	public void setSifra(String sifra) {
		m_sifra = sifra;
	}

	public String getTitula() {
		return m_titula;
	}

	public void setTitula(String titula) {
		m_titula = titula;
	}
	
	@Override
	public String toString() {
		return getIme() + " " + getPrezime();
	}
	
}
