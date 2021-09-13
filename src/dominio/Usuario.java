package dominio;

public class Usuario {
	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private TipoAtraccion tipoAtraccionPreferida;
	private Itinerario itinerario;

	public static String nuevaLinea = System.getProperty("line.separator");

	public Usuario(String nombre, int presupuesto, double tiempoDisponible, TipoAtraccion tipoAtraccionPreferida) {
		super();
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoAtraccionPreferida = tipoAtraccionPreferida;
		this.itinerario = new Itinerario();
	}

	public String getNombre() {
		return this.nombre;
	}

	public TipoAtraccion getTipoAtraccionPreferida() {
		return this.tipoAtraccionPreferida;
	}

	public boolean puedeComprar(Sugerible sugerencia) {
		return ((this.presupuesto >= sugerencia.costoTotal()) && (Double.doubleToLongBits(this.tiempoDisponible) >= Double.doubleToLongBits(sugerencia.tiempoTotal())));
	}

	public void disminuirPresupuesto(int presupuesto) {
		this.presupuesto -= presupuesto;
	}

	public void disminuirTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible -= tiempoDisponible;
	}

	public Itinerario getItinerario() {
		return this.itinerario;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}
}
