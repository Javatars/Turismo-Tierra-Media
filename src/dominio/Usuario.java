package dominio;

public class Usuario {
	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private TipoAtraccion tipoAtraccionPreferida;
	private Itinerario itinerario;
	
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
		return this.presupuesto >= sugerencia.costoTotal() && this.tiempoDisponible >= sugerencia.tiempoTotal();
	}

	public void disminuirPresupuesto(int presupuesto) {
		this.presupuesto -= presupuesto;
	}

	public void disminuirTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}
	
	public Itinerario getItinerario() {
		return this.itinerario;
	}

	@Override
	public String toString() {
		String compras = "";
		for(Sugerible unaSugerencia : this.itinerario.getSugerenciasAceptadas()) {
			compras += "	" + unaSugerencia.getNombre() + "/n";
		}
		return "Nombre usuario: " + this.nombre + "/n" + "Tipo atraccion preferida: " + this.tipoAtraccionPreferida + "/n" 
				+ "Compras realizadas: " + compras + "Total a pagar: " + this.itinerario.costoTotal() + "/n"
				+ "Tiempo necesario para la salida: " + this.itinerario.horasNecesarias();
	}
}
