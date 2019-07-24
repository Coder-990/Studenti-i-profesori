package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

public class KomisijskiIspit extends Ispit {

	private Profesor m_ClanKomisije1;
	private Profesor m_ClanKomisije2;
	private Profesor m_ClanKomisije3;

	public KomisijskiIspit(long id, Predmet predmet, Student student, Ocjena ocjena, LocalDateTime datumIVrijeme,
			Profesor clanKomisije1, Profesor clanKomisije2, Profesor clanKomisije3) {
		super(id, predmet, student, ocjena, datumIVrijeme);
		
		m_ClanKomisije1 = clanKomisije1;
		m_ClanKomisije2 = clanKomisije2;
		m_ClanKomisije3 = clanKomisije3;
	}

	public Profesor getClanKomisije1() {
		return m_ClanKomisije1;
	}

	public void setClanKomisije1(Profesor clanKomisije1) {
		m_ClanKomisije1 = clanKomisije1;
	}

	public Profesor getClanKomisije2() {
		return m_ClanKomisije2;
	}

	public void setClanKomisije2(Profesor clanKomisije2) {
		m_ClanKomisije2 = clanKomisije2;
	}

	public Profesor getClanKomisije3() {
		return m_ClanKomisije3;
	}

	public void setClanKomisije3(Profesor clanKomisije3) {
		m_ClanKomisije3 = clanKomisije3;
	}

	@Override
	public String toString() {
		return m_predmet.getNaziv() + "\n" +"\n"
				+ m_ClanKomisije1.getIme() + " " + m_ClanKomisije1.getPrezime() + "\n"
				+ m_ClanKomisije2.getIme() + " " + m_ClanKomisije2.getPrezime() + "\n"
				+ m_ClanKomisije3.getIme() + " " + m_ClanKomisije3.getPrezime() + "\n";
	}

}
