package dominio;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {
	private int costoFinal;
	
	public PromocionAbsoluta(String nombre, TipoAtraccion tipoAtraccion, ArrayList<Atraccion> atracciones, int costo) {
		super(nombre, tipoAtraccion, atracciones);
		this.costoFinal = costo;
	}
	
	@Override
	public int costoTotal() {
		return this.costoFinal;
	}

	@Override
	public String toString() {
		String nombresAtracciones = "";
		for(int i=0; i < atracciones.size(); i++) {
			if(i + 1 == atracciones.size()) nombresAtracciones += atracciones.get(i).getNombre();
			else nombresAtracciones += atracciones.get(i).getNombre() + ",";
		}
		return "La promocion " + this.nombre + ", es " + this.tipoAtraccion + ", cuesta " + this.costoTotal() + " monedas, se necesita un tiempo de " + this.tiempoTotal() 
				+ " horas para realizarlo, e incluye las siguientes atracciones: " + nombresAtracciones;
	}
}
