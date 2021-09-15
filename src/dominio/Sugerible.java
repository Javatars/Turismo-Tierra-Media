package dominio;

public interface Sugerible {
	public void disminuirCupo();
	public int costoTotal();
	public double tiempoTotal();
	public boolean hayCupo();
	public TipoAtraccion getTipo();
	public boolean esPromocion();
	public String getNombre();
	public String toString();
	public String resumen();
}
