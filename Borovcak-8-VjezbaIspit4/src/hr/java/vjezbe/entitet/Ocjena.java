package hr.java.vjezbe.entitet;

/**
 * Entitet enumeracija tip podatka koja sadrzi pobrojani nis konstanti ocjena i njihov opis
 * 
 * @author domagoj
 *
 */
public enum Ocjena {

	    OCJENA_IZVRSTAN(5,"Izvrstan"),
	    OCJENA_VRLO_DOBAR(4,"Vrlo dobar"),
	    OCJENA_DOBAR(3,"Dobar"),
	    OCJENA_DOVOLJAN(2,"Dovoljan"),
	    OCJENA_NEDOVOLJAN(1,"Nedovoljan"),
		NEMA_OCJENE(0, "Nema ocjene");
	
	private Integer m_broj;
	private String m_naziOcjene;
	
	/**
	 * Sluzi za kreiranje objekta koji ima definirane parametre: broj i naziv ocjene.
	 * 
	 * @param broj
	 * @param nazivOcjene 
	 */
	private Ocjena(Integer broj,String nazivOcjene) {
		m_broj=broj;
		m_naziOcjene=nazivOcjene;
	}

	public Integer getBroj() {
		return m_broj;
	}

	public String getNazivOcjene() {
		return m_naziOcjene;
	}

	


	    
}
