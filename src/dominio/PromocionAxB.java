package dominio;

import java.text.DecimalFormat;
import java.util.ArrayList;

import app.App;

public class PromocionAxB extends Promocion {
	private Atraccion atraccionGratis;

	public PromocionAxB(String nombre, TipoAtraccion tipoAtraccion, ArrayList<Atraccion> atracciones, Atraccion atraccion) {
		super(nombre, tipoAtraccion, atracciones);
		this.atraccionGratis = atraccion;
		this.atracciones.add(atraccion);
	}

	@Override
	public int costoTotal() {
		return super.costoTotal() - this.atraccionGratis.costoTotal();
	}

	@Override
	public String toString() {
		DecimalFormat formato = new DecimalFormat();
		formato.setMaximumFractionDigits(2);
		String nombresAtracciones = "";
		for(int i=0; i < atracciones.size() - 1; i++) {
			if(i + 1 == atracciones.size() - 1) nombresAtracciones += atracciones.get(i).getNombre();
			else nombresAtracciones += atracciones.get(i).getNombre() + ",";
		}
		return "La promocion " + this.nombre + ", es " + this.tipoAtraccion + ", cuesta " + formato.format(this.costoTotal()) + " monedas, se necesita un tiempo de " + this.tiempoTotal() 
				+ " horas para realizarlo, incluye las siguientes atracciones " + nombresAtracciones
				+ " y tiene la atraccion "+ this.atraccionGratis.getNombre() + " gratis";
	}
	
	@Override
	public String resumen() {
		DecimalFormat formato = new DecimalFormat();
		formato.setMaximumFractionDigits(2);
		String resumen = "";
		resumen += "	" +  this.nombre + "[" + App.nuevaLinea;
		for (int j = 0; j < this.atracciones.size() - 1; j++)
			resumen += "	" + this.atracciones.get(j).resumen() + App.nuevaLinea;
		resumen += "		" + this.atraccionGratis.getNombre() + ": es gratis y tiene un tiempo de "
				+ this.atraccionGratis.tiempoTotal() + " horas." + App.nuevaLinea;
		resumen += "	]: el pack cuesta " + this.costoTotal() + " monedas y tiene un tiempo de " 
				+ formato.format(this.tiempoTotal()) + " horas." + App.nuevaLinea;
		return resumen;
	}
}
