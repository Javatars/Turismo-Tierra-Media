package excepciones;

@SuppressWarnings("serial")
public class PromocionExistenteException extends Exception {
	public PromocionExistenteException(String mensaje) {
		super(mensaje);
	}
}
