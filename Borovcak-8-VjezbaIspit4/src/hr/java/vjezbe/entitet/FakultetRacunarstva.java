package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

/**
 * @author domagoj
 * 
 * Predstavlja entitet Fakultet racunrastva koji nasljeduje klasu Obrazovna ustanova i implementira sucelje Diplomski.
 *
 */
public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {

	//private static final Logger log=org.slf4j.LoggerFactory.getLogger(Glavna.class);
	
	public FakultetRacunarstva(long id, String nazivObrazovneUstanove, List<Predmet> predmeti,
			List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
		super(id, nazivObrazovneUstanove, predmeti, profesori, studenti, ispiti);
	}

	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Integer ocjenaDiplomskogRada,
			Integer ocjenaObraneDiplomskogRada) {

		BigDecimal konacnaOcjenaZaStudenta = new BigDecimal(1);
		try {
			konacnaOcjenaZaStudenta=odrediProsjekOcjenaNaIspitima(ispiti);
		}catch (NemoguceOdreditiProsjekStudentaException ex) {
			konacnaOcjenaZaStudenta=new BigDecimal(1);
			Student student=ispiti.get(0).getStudent();
			String message="Student "+ student.getIme()+" "+student.getPrezime()+" zbog jedne negativne ocjene na jednom od ispita student ima prosjek: \"nedovoljan (1)\".";
		//	log.error("Greska: "+ex);
			System.out.println(message);
			return konacnaOcjenaZaStudenta;
		}
		
		konacnaOcjenaZaStudenta=konacnaOcjenaZaStudenta.multiply(new BigDecimal(3));
		konacnaOcjenaZaStudenta=konacnaOcjenaZaStudenta.add(new BigDecimal(ocjenaDiplomskogRada));
		konacnaOcjenaZaStudenta=konacnaOcjenaZaStudenta.add(new BigDecimal(ocjenaObraneDiplomskogRada));
		konacnaOcjenaZaStudenta=konacnaOcjenaZaStudenta.divide(new BigDecimal(5));
		
		return konacnaOcjenaZaStudenta;
	}
		

	// metoda filtrira ispite koji su odradeni u ulaznoj godini
	private ArrayList<Ispit> filtrirajIspitePoGodini(Integer godina, ArrayList<Ispit> ispiti) {

		ArrayList<Ispit> filtriraniPoGodini = new ArrayList<>();
		for (Ispit ispit : ispiti) {
			if (godina.equals(ispit.getDatumIVrijeme().getYear())) {
				filtriraniPoGodini.add(ispit);
			}
		}
		return filtriraniPoGodini;
	}

	private ArrayList<Ispit> IzvrstanStudent(ArrayList<Ispit> ispiti, Student student) {

		ArrayList<Ispit> OcjenaIzvrstan=new ArrayList<>();

		for (Ispit ispit : ispiti) {
			if (ispit.getOcjena().getBroj().equals(5)) {
				OcjenaIzvrstan.add(ispit);
			}
		}

		ArrayList<Ispit> ispitiIzvrsnihStudenta = new ArrayList<Ispit>();
	
		for (Ispit ispit : OcjenaIzvrstan) {
			if (ispit.getStudent().equals(student)) {
			ispitiIzvrsnihStudenta.add(ispit);
				}
		}
		return ispitiIzvrsnihStudenta;
	}

	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {

		ArrayList<Ispit> ispitiIzvrsnogStudenta = new ArrayList<>();
		ArrayList<Ispit> ispitiIzvrsnogStudentaUGodini = new ArrayList<>();
		BigDecimal studentSmanjimIndeksom = null;
		BigDecimal tmpStudentSmanjimIndeksom = null;
		Student najboljiStudent = null;

		// iteriraj po studentima i za svakoga daj njegove ispite
		for (Student student : super.getStudent()) {

			ispitiIzvrsnogStudenta = (ArrayList<Ispit>) filtrirajIspitePoStudentu(super.getIspit(), student);
			ispitiIzvrsnogStudentaUGodini = filtrirajIspitePoGodini(godina, ispitiIzvrsnogStudenta);	
			ispitiIzvrsnogStudentaUGodini = IzvrstanStudent(ispitiIzvrsnogStudentaUGodini, najboljiStudent);

			if (studentSmanjimIndeksom == null) {

				studentSmanjimIndeksom = tmpStudentSmanjimIndeksom;
				najboljiStudent = student;
			} else {
				if (tmpStudentSmanjimIndeksom.compareTo(studentSmanjimIndeksom) == -1) {
					studentSmanjimIndeksom = tmpStudentSmanjimIndeksom;
					najboljiStudent = student;
				}
			}

		}
		return najboljiStudent;
	}

	@Override
	public Student odrediStudentaZaRektorovuNagradu() {

		Student najuspjesnijiStudenti = null;
		BigDecimal studentSaMAXProsjekom = new BigDecimal(0);
		ArrayList<Ispit> ispitSvakogStudenta=new ArrayList<Ispit>();
		BigDecimal prosjek = new BigDecimal(0);
		int brNajmadihStudenata = 0;

		for (Student student : getStudent()) {

			if (studentSaMAXProsjekom.compareTo(prosjek) < 0) {
				studentSaMAXProsjekom = prosjek;
				najuspjesnijiStudenti = student;

			} else if (studentSaMAXProsjekom.compareTo(prosjek) == 0) {
				if (student.getDatumRodenja().isAfter(najuspjesnijiStudenti.getDatumRodenja())) {
					najuspjesnijiStudenti = student;
					brNajmadihStudenata++;
				}

				else if (brNajmadihStudenata == 1) {
					throw new RuntimeException("Najmladi student: " + student.getIme() + " " + student.getPrezime());
				} else {
		//			Glavna.log.error("Najmladi studenti: " + student.getIme() + " " + student.getPrezime());
					System.exit(1);
				}
				// ako se detektira da ima više najmlađih studenata, program mora zapisati
				// informacije u „log“ datoteku,
				// ispisati poruku korisniku i završiti s izvođenjem
			}
		
			ispitSvakogStudenta = (ArrayList<Ispit>) (filtrirajIspitePoStudentu(getIspit(), student));

			try {
				prosjek = odrediProsjekOcjenaNaIspitima(ispitSvakogStudenta);
			} catch (NemoguceOdreditiProsjekStudentaException iznimkaNemoguceOdreditiProsjekStudenta) {
				System.out.println("Dogodila se pogreska!");
			}
		}
		return najuspjesnijiStudenti;
	}

}
