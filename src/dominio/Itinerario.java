package dominio;

import java.util.ArrayList;

public class Itinerario {
	private ArrayList<Sugerible> sugerenciasAceptadas;

	public static String nuevaLinea = System.getProperty("line.separator");

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
		String compras = "";
		for(int i = 0; i < this.sugerenciasAceptadas.size(); i++)
			compras += "	" + this.sugerenciasAceptadas.get(i).getNombre() + nuevaLinea;
		return "Compras realizadas: " + nuevaLinea + compras + "Total a pagar: " + this.costoTotal() + " monedas" 
			+ nuevaLinea + "Tiempo necesario para la salida: " + this.horasNecesarias() + " horas";
	}
}
