package hr.java.vjezbe.iznimke;


public class NemoguceOdreditiProsjekStudentaException extends Exception{
		
	/**
	 * Klasa za hvatanje iznimka NemoguceOdreditiProsjekStudenta.
	 * 
	 */
	private static final long serialVersionUID = -7974994568218559818L;

	public NemoguceOdreditiProsjekStudentaException() {
		super("Dogodila se pogreska u radu pograma: Nemoguce odrediti prosjek studenta!");
		}
	
	public NemoguceOdreditiProsjekStudentaException(String message) {
		super(message);
		}
	
	public NemoguceOdreditiProsjekStudentaException(String message, Throwable cause) {
		super(message, cause);
		}
	
	public NemoguceOdreditiProsjekStudentaException(Throwable cause) {
		super(cause);
	}
		
	

}
