package dominio;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PromocionPorcentual extends Promocion {
	private double porcentajeDescuento;
	
	public PromocionPorcentual(String nombre, TipoAtraccion tipo, ArrayList<Atraccion> atracciones, double porcentajeDescuento) {
		super(nombre, tipo, atracciones);
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	@Override
	public int costoTotal() {
		return (int) (super.costoTotal() - super.costoTotal() * this.porcentajeDescuento);
	}

	@Override
	public String toString() {
		DecimalFormat formato = new DecimalFormat();
		formato.setMaximumFractionDigits(2);
		String nombresAtracciones = "";
		for(int i=0; i < atracciones.size(); i++) {
			if(i + 1 == atracciones.size()) nombresAtracciones += atracciones.get(i).getNombre();
			else nombresAtracciones += atracciones.get(i).getNombre() + ",";
		}
		return "La promocion " + this.nombre + ", tiene un descuento del " + this.porcentajeDescuento
				+ "%, es " + this.tipoAtraccion + ", cuesta " + this.costoTotal() + " monedas, se necesita un tiempo de " + formato.format(this.tiempoTotal()) 
				+ " horas para realizarlo, e incluye las siguientes atracciones: " + nombresAtracciones;
	}
	
	
}
