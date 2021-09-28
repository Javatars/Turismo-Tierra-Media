package dominio;

import java.text.DecimalFormat;
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
	public boolean esOcontiene(Sugerible sugerencia) {
		boolean incluye = false;
		for(Atraccion unaAtraccion : this.atracciones) {
			if(unaAtraccion.esOcontiene(sugerencia) || sugerencia.esOcontiene(unaAtraccion)) {
				incluye = true;
				break;
			}
		}
		return incluye;
	}

	@Override
	public String resumen() {
		String resumen = "";
		DecimalFormat formato = new DecimalFormat();
		formato.setMaximumFractionDigits(2);
		resumen += "	" + this.nombre + "[" + App.nuevaLinea;
		for (int j = 0; j < this.atracciones.size(); j++)
			resumen += "	" + this.atracciones.get(j).resumen() + App.nuevaLinea;
		resumen += "	]: el pack cuesta " + this.costoTotal() + " monedas y tiene un tiempo de " 
				+ formato.format(this.tiempoTotal()) + " horas." + App.nuevaLinea;
		return resumen;
	}
}
