package hr.java.vjezbe.iznimke;

public class PostojiViseNajmladjihStudenataException extends RuntimeException{

	/**
	 * Klasa za hvatanje neoznaÄ�enih iznimka PostojiViseNajmladjihStudenata.
	 */
	private static final long serialVersionUID = 2704467502137810162L;

	public PostojiViseNajmladjihStudenataException() {
		super("Dogodila se pogreska u radu pograma: Postoji vise najmladih studenta!");
		}
	
	public PostojiViseNajmladjihStudenataException(String message) {
		super(message);
		}
	
	public PostojiViseNajmladjihStudenataException(String message, Throwable cause) {
		super(message, cause);
		}
	
	public PostojiViseNajmladjihStudenataException(Throwable cause) {
		super(cause);
	}
	
}
