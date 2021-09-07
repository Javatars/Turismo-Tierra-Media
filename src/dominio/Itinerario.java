package dominio;

import java.util.ArrayList;

public class Itinerario {
	private ArrayList<Sugerible> sugerenciasAceptadas;
	
	public Itinerario() {
		this.sugerenciasAceptadas = new ArrayList<Sugerible>();
	}
	
	public double horasNecesarias() {
		double tiempo = 0;
		for(Sugerible unaSugerencia : this.sugerenciasAceptadas)
			tiempo += unaSugerencia.tiempoTotal();
		return tiempo;
	}
	
	public int costoTotal() {
		int costo = 0;
		for(Sugerible unaSugerencia : this.sugerenciasAceptadas)
			costo += unaSugerencia.costoTotal();
		return costo;
	}
	
	public void agregarSugerencia(Sugerible sugerencia) {
		this.sugerenciasAceptadas.add(sugerencia);
	}
	
	public ArrayList<Sugerible> getSugerenciasAceptadas(){
		return this.sugerenciasAceptadas;
	}
	
	public boolean hayPromocionQueIncluyeAtraccion(Atraccion atraccion) {
		boolean incluye = false;
		for(Sugerible unaSugerencia : this.sugerenciasAceptadas) {
			if(unaSugerencia instanceof Promocion && ((Promocion) unaSugerencia).incluyeAtraccion(atraccion)) {
				incluye = true;
				break;
			}
		}
		return incluye;
	}
	
	public String resumen() {
		return "Resumen itinerario del usuario: costo igual a " + this.costoTotal() + " monedas, y tiempo necesario igual a " + this.horasNecesarias() + " horas";
	}
}
