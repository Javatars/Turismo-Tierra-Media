package dominio;

import java.util.ArrayList;

public class PromocionAxB extends Promocion {
	private Atraccion atraccionGratis;

	public PromocionAxB(String nombre, TipoAtraccion tipoAtraccion, ArrayList<Atraccion> atracciones, Atraccion atraccion) {
		super(nombre, tipoAtraccion, atracciones);
		this.atraccionGratis = atraccion;
	}
	
	public Atraccion getAtraccionGratis() {
		return this.atraccionGratis;
	}
	
	@Override
	public double tiempoTotal() {
		return super.tiempoTotal() + this.atraccionGratis.tiempoTotal();
	}

	@Override
	public String toString() {
		String nombresAtracciones = "";
		for(int i=0; i < atracciones.size(); i++) {
			if(i + 1 == atracciones.size()) nombresAtracciones += atracciones.get(i).getNombre();
			else nombresAtracciones += atracciones.get(i).getNombre() + ",";
		}
		return "La promocion " + this.nombre + ", es " + this.tipoAtraccion + ", cuesta " + this.costoTotal() + " monedas, se necesita un tiempo de " + this.tiempoTotal() 
				+ " horas para realizarlo, incluye las siguientes atracciones " + nombresAtracciones
				+ " y tiene la atraccion "+ this.atraccionGratis.getNombre() + " gratis";
	}
}
