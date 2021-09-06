package dominio;

public class Atraccion implements Sugerible{
	private String nombre;
	private int costo;
	private double tiempo;
	private int cupo;
	private TipoAtraccion tipo;
	
	public Atraccion(String nombre, int costo, double tiempo, int cupo, TipoAtraccion tipo) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipo = tipo;
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public void disminuirCupo() {
		this.cupo--;
	}

	@Override
	public int costoTotal() {
		return this.costo;
	}

	@Override
	public double tiempoTotal() {
		return this.tiempo;
	}

	@Override
	public boolean hayCupo() {
		return this.cupo > 0;
	}

	@Override
	public TipoAtraccion getTipo() {
		return this.tipo;
	}

	@Override
	public boolean esPromocion() {
		return false;
	}

	@Override
	public String toString() {
		return "La atraccion " + nombre + ", cuesta " + costo + " monedas, se necesita un tiempo de " + tiempo + 
				" horas para realizarlo, y el tipo es " + tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + costo;
		result = prime * result + cupo;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(tiempo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		if (costo != other.costo)
			return false;
		if (cupo != other.cupo)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(tiempo) != Double.doubleToLongBits(other.tiempo))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
}
