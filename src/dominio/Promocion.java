package dominio;

import java.util.ArrayList;

public class Promocion implements Sugerible {
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
		return nombre;
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
	
	public boolean incluyeAtraccion(Atraccion atraccion) {
		boolean incluye = false;
		for(Atraccion unaAtraccion : atracciones) {
			if(unaAtraccion.equals(atraccion)) {
				incluye = true;
				break;
			}
		}
		return incluye;
	}
}
