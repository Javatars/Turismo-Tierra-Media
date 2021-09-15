package dominio;

import java.util.ArrayList;

import app.App;

public class Itinerario {
	private ArrayList<Sugerible> sugerenciasAceptadas;	

	public Itinerario() {
		this.sugerenciasAceptadas = new ArrayList<Sugerible>();
	}

	public ArrayList<Sugerible> getSugerenciasAceptadas(){
		return this.sugerenciasAceptadas;
	}

	public double horasNecesarias() {
		double tiempo = 0;
		for (Sugerible unaSugerencia : this.sugerenciasAceptadas)
			tiempo += unaSugerencia.tiempoTotal();
		return tiempo;
	}

	public int costoTotal() {
		int costo = 0;
		for (Sugerible unaSugerencia : this.sugerenciasAceptadas)
			costo += unaSugerencia.costoTotal();
		return costo;
	}

	public void agregarSugerencia(Sugerible sugerencia) {
		this.sugerenciasAceptadas.add(sugerencia);
	}
	
	public ArrayList<Promocion> getPromociones(){
		ArrayList<Promocion> promociones = new ArrayList<Promocion>();
		for(Sugerible unaSugerenciaAceptada : this.sugerenciasAceptadas) {
			if(unaSugerenciaAceptada.esPromocion()) promociones.add((Promocion)unaSugerenciaAceptada);
		}
		return promociones;
	}

	public boolean incluyeAtraccion(Sugerible sugerencia) {
		boolean incluye = false;
		if (!sugerencia.esPromocion()) {
			for (Promocion unaPromocionAceptada : this.getPromociones()) {
				if(unaPromocionAceptada.getAtracciones().contains((Atraccion) sugerencia)) {
					incluye = true;
					break;
				}
			}
		} else {
			for (Atraccion unaAtraccion : ((Promocion) sugerencia).getAtracciones()) {
				if (this.sugerenciasAceptadas.contains((Sugerible) unaAtraccion)) {
					incluye = true;
					break;
				} else {
					for (Promocion unaPromocionAceptada : this.getPromociones()) {
						if (unaPromocionAceptada.getAtracciones().contains(unaAtraccion)){
							incluye = true;
							break;
						}
					}
				}
			}
		}
		return incluye;
	}

	public String resumen() {
		String compras = "";
		for (int i = 0; i < this.sugerenciasAceptadas.size(); i++) {
			if(this.sugerenciasAceptadas.get(i) instanceof Atraccion)
				compras += this.sugerenciasAceptadas.get(i).resumen() + App.nuevaLinea;
			else compras += this.sugerenciasAceptadas.get(i).resumen();
		}	
		return "Compras realizadas: " + App.nuevaLinea + compras + "Total a pagar: " + this.costoTotal() + " monedas"
				+ App.nuevaLinea + "Tiempo necesario para la salida: " + this.horasNecesarias() + " horas";
	}
}
