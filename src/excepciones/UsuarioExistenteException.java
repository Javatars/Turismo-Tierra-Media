package excepciones;

@SuppressWarnings("serial")
public class UsuarioExistenteException extends Exception {
	public UsuarioExistenteException(String mensaje) {
		super(mensaje);
	}
}
