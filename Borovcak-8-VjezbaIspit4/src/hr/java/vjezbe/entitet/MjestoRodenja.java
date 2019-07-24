package hr.java.vjezbe.entitet;

public class MjestoRodenja extends Entitet {

	String m_nazivMjestaRodenja;
	
	
	public MjestoRodenja(long id, String nazivMjestaRodenja) {
		super(id);
		
		m_nazivMjestaRodenja = nazivMjestaRodenja;
	}

	public String get_nazivMjestaRodenja() {
		return m_nazivMjestaRodenja;
	}

	public void set_nazivMjestaRodenja(String nazivMjestaRodenja) {
		this.m_nazivMjestaRodenja = nazivMjestaRodenja;
	}
}
