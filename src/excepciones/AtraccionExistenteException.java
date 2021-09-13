package excepciones;

@SuppressWarnings("serial")
public class AtraccionExistenteException extends Exception {
	public AtraccionExistenteException(String mensaje) {
		super(mensaje);
	}
}
