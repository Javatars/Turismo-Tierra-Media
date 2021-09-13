package excepciones;

@SuppressWarnings("serial")
public class AtraccionNoExisteException extends Exception {
	public AtraccionNoExisteException(String mensaje) {
		super(mensaje);
	}
}
