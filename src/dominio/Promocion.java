package dominio;

import java.util.ArrayList;

import app.App;

public abstract class Promocion implements Sugerible {
	protected String nombre;
	protected TipoAtraccion tipoAtraccion;
	protected ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();

	public Promocion(String nombre, TipoAtraccion tipoAtraccion, ArrayList<Atraccion> atracciones) {
		super();
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
		this.atracciones = atracciones;
	}

	public ArrayList<Atraccion> getAtracciones(){
		return this.atracciones;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public void disminuirCupo() {
		for(Atraccion unaAtraccion : atracciones)
			unaAtraccion.disminuirCupo();
	}

	@Override
	public int costoTotal() {
		int costo = 0;
		for(Atraccion unaAtraccion : atracciones)
			costo += unaAtraccion.costoTotal();
		return costo;
	}

	@Override
	public double tiempoTotal() {
		double tiempo = 0;
		for(Atraccion unaAtraccion : atracciones)
			tiempo += unaAtraccion.tiempoTotal();
		return tiempo;
	}

	@Override
	public boolean hayCupo() {
		boolean hayCupo = true;
		for(Atraccion unaAtraccion : atracciones) {
			if(!unaAtraccion.hayCupo()) {
				hayCupo = false;
				break;
			}
		}
		return hayCupo;
	}

	@Override
	public TipoAtraccion getTipo() {
		return this.tipoAtraccion;
	}

	@Override
	public boolean esPromocion() {
		return true;
	}

	@Override
	public String resumen() {
		String resumen = "";
		resumen += "	" + this.nombre + "[" + App.nuevaLinea;
		for (int j = 0; j < this.atracciones.size(); j++)
			resumen += "	" + this.atracciones.get(j).resumen() + App.nuevaLinea;
		resumen += "	]: el pack cuesta " + this.costoTotal() + " monedas y tiene un tiempo de " 
				+ this.tiempoTotal() + " horas." + App.nuevaLinea;
		return resumen;
	}
}
