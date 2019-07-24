package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;



public interface Visokoskolska {

	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispit, Integer ocjenaZavrsnogRada,
			Integer ocjenaObraneZavrsnogRada);

	default BigDecimal odrediProsjekOcjenaNaIspitima(List<Ispit> ispiti) throws NemoguceOdreditiProsjekStudentaException {
		
		BigDecimal konacniIzracun = new BigDecimal(0);
		BigDecimal prosjek = new BigDecimal(0);
			
			int brIspita = 0;
			for (Ispit ispit : ispiti) {
				if (ispit.getOcjena().getBroj() >= 2) {
					konacniIzracun = konacniIzracun.add(new BigDecimal(ispit.getOcjena().getBroj()));
					brIspita++;
				}
				else {
					throw new NemoguceOdreditiProsjekStudentaException("Student "+ispit.getStudent().getIme()+" "
				+ispit.getStudent().getPrezime()+" doobio je ocjenu nedovoljan(1) iz predmeta "
							+ispit.getPredmet().getNaziv());
				}	
				prosjek = konacniIzracun.divide(new BigDecimal(brIspita));		
		}
			
		return prosjek;
		
		

		// ispiti moraju biti polozeni s pozitivnom ocjenom
	}

	private ArrayList<Ispit> filtrirajPolozeneIspite(List<Ispit> ispiti) {

		List<Ispit> polozeniIspiti=new ArrayList<>();
		for(Ispit ispit:ispiti) {
			if(ispit.getOcjena().getBroj()>=2) {
				polozeniIspiti.add(ispit);
			}
		}
		return (ArrayList<Ispit>) polozeniIspiti;
	}

	default List<Ispit> filtrirajIspitePoStudentu(List<Ispit> ispiti, Student student) {

		long trenutnoVrijeme=System.currentTimeMillis();
		List<Ispit> filtriraniIspiti =(List<Ispit>) ispiti.stream().filter(ispit->ispit.getStudent().equals(student)).collect(Collectors.toList());
		long lambdaVrijeme=System.currentTimeMillis();
		
		trenutnoVrijeme=System.currentTimeMillis();
		List<Student> filtriraniIspitiStudenta=new ArrayList<Student>();
		for (Ispit ispit : ispiti) {
			if (ispit.getStudent().equals(student)) {
				filtriraniIspitiStudenta.add(student);
			}
		}
		long forPetljaVrijeme=System.currentTimeMillis() - trenutnoVrijeme;

		System.out.println("Filtriranje pomocu lambde iznosi: " + lambdaVrijeme+ "ms");
		System.out.println("Filtriranje pomocu for petlje iznosi: " + forPetljaVrijeme+ "ms");
	
		return filtriraniIspiti;
	}

}
