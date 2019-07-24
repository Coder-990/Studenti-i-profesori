package hr.java.vjezbe.entitet;
import java.util.ArrayList;
import java.util.List;

public class Sveuciliste <T extends ObrazovnaUstanova> {

	private List<T> m_listaObrazovnihUstanova;

	public Sveuciliste() {
		super();
		m_listaObrazovnihUstanova =new ArrayList<T>();
	}
	
	public Sveuciliste(List <T> obrazovneUstanove) {
		super();
		m_listaObrazovnihUstanova=obrazovneUstanove;
	}
	
	
public void dodajObrazovnuUstanovu(T obrazovnaUstanova) {
	   m_listaObrazovnihUstanova.add(obrazovnaUstanova);
	}

public T dohvatiObrazovnuUstanovu(Integer indeks) {
	return m_listaObrazovnihUstanova.get(indeks);
	
	}

}
	
