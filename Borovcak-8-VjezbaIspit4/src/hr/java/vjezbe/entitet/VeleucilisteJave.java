package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;



public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {

	public VeleucilisteJave(long id, String nazivObrazovneUstanove, List<Predmet> predmeti, List<Profesor> profesori,
			List<Student> studenti, List<Ispit> ispiti) {
		super(id,nazivObrazovneUstanove, predmeti, profesori, studenti, ispiti);
	}

	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispitStudenta, Integer ocjenaZavrsnogRada,
			Integer ocjenaObraneZavrsnogRada) {

		BigDecimal konacnaOcjenaZaStudenta = new BigDecimal(1);
		BigDecimal prosjekOcjenaStudenta = new BigDecimal(0);
		BigDecimal konacnaOcjena = new BigDecimal(0);

		for (Ispit ispit : ispitStudenta) {
			if (ispit.getOcjena().equals(konacnaOcjenaZaStudenta)) {
				prosjekOcjenaStudenta = konacnaOcjenaZaStudenta;
				System.out.println("Student " + ispit.getStudent().getIme() + " " + ispit.getStudent().getPrezime()
						+ " zbog negativne ocjene na jednom od ispita ima prosjek 'nedovoljan(1)'");
			} else {
				konacnaOcjena = konacnaOcjena.add(new BigDecimal(ispit.getOcjena().getBroj()));
			}
		}
		prosjekOcjenaStudenta = konacnaOcjena.divide(new BigDecimal(ispitStudenta.size()));// treba provjeriti
		prosjekOcjenaStudenta = prosjekOcjenaStudenta.multiply(new BigDecimal(2));
		prosjekOcjenaStudenta = prosjekOcjenaStudenta
				.add(new BigDecimal(ocjenaZavrsnogRada + ocjenaObraneZavrsnogRada));
		prosjekOcjenaStudenta = prosjekOcjenaStudenta.divide(new BigDecimal(4));// ispiti lenght je broj elemenata
																				// polja ispiti koliko ih ima
		return prosjekOcjenaStudenta;
	}

	// metoda filtrira ispite koji su odradeni u ulaznoj godini
	private ArrayList<Ispit> filtrirajIspitePoGodini(Integer godina, ArrayList<Ispit> ispiti) {

		ArrayList<Ispit> ispitiPoGodini = new ArrayList<>();

		for (Ispit ispit : ispiti) {
			if (godina.equals(ispit.getDatumIVrijeme().getYear())) {
				ispitiPoGodini.add(ispit);
			}
		}
		return ispitiPoGodini;
	}

	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {

		ArrayList<Ispit> ispitiDoticnogStudenta = new ArrayList<>();
		ArrayList<Ispit> ispitiDoticnogStudentaUGodini = new ArrayList<>();
		BigDecimal maxProsjek = null;
		BigDecimal tmpProsjek = null;
		Student najboljiStudent = null;

		for (Student student : super.getStudent()) {

			ispitiDoticnogStudenta = (ArrayList<Ispit>) filtrirajIspitePoStudentu(super.getIspit(), student);
			ispitiDoticnogStudentaUGodini = filtrirajIspitePoGodini(godina, ispitiDoticnogStudenta);

			try {
				tmpProsjek = odrediProsjekOcjenaNaIspitima(ispitiDoticnogStudentaUGodini);
			} catch (NemoguceOdreditiProsjekStudentaException iznimkaNemoguceOdreditiProsjekStudenta) {
				System.out.println("Dogodila se pogreska!");
			}
			if (maxProsjek == null) {

				maxProsjek = tmpProsjek;
				najboljiStudent = student;
			} else {
				if (tmpProsjek.compareTo(maxProsjek) == 1) {
					maxProsjek = tmpProsjek;
					najboljiStudent = student;
				}
			}
		}

		return najboljiStudent;
	}

}
