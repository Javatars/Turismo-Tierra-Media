package dominio;

import java.util.ArrayList;

public class Itinerario {
	private ArrayList<Sugerible> sugerenciasAceptadas;

	//Se usa en el metodo resumen() para que devuelva el string con un salto de linea
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

	public boolean incluyeAtraccion(Sugerible sugerencia) {
		boolean incluye = false;
		if(!sugerencia.esPromocion()) {
			for(Sugerible unaSugerenciaAceptada : this.sugerenciasAceptadas) {
				if(unaSugerenciaAceptada instanceof Promocion && ((Promocion) unaSugerenciaAceptada).incluyeAtraccion((Atraccion)sugerencia)) {
					incluye = true;
					break;
				}
			}
		}else {
			ArrayList<Atraccion> listAtracciones = new ArrayList<Atraccion>();
			for(Atraccion atraccion : ((Promocion)sugerencia).atracciones) {
				listAtracciones.add(atraccion);
			}if(sugerencia instanceof PromocionAxB)
				listAtracciones.add(((PromocionAxB)sugerencia).getAtraccionGratis());
			for(Atraccion unaAtraccion : listAtracciones) {
				if(this.sugerenciasAceptadas.contains((Sugerible)unaAtraccion)) {
					incluye = true;
					break;
				}else {
					for(Sugerible unaSugerenciaAceptada : this.sugerenciasAceptadas) {
						if(unaSugerenciaAceptada instanceof Promocion && ((Promocion) unaSugerenciaAceptada).incluyeAtraccion(unaAtraccion)) {
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
		for(int i = 0; i < this.sugerenciasAceptadas.size(); i++) {
			if(this.sugerenciasAceptadas.get(i) instanceof Promocion) {
				compras += "	" + this.sugerenciasAceptadas.get(i).getNombre() + "[" + nuevaLinea;
				Promocion unaPromocion = (Promocion) this.sugerenciasAceptadas.get(i);
				for(int j = 0; j < unaPromocion.atracciones.size(); j++) {
					compras += "		" + unaPromocion.atracciones.get(j).getNombre()+ ": cuesta " + 
							unaPromocion.atracciones.get(j).costoTotal() + " monedas y tiene un tiempo de " +
							unaPromocion.atracciones.get(j).tiempoTotal() + " horas."+ nuevaLinea;
				}if(this.sugerenciasAceptadas.get(i) instanceof PromocionAxB) {
					compras += "		" + ((PromocionAxB) this.sugerenciasAceptadas.get(i)).getAtraccionGratis().getNombre() + ": es gratis y tiene un tiempo de "
							+ ((PromocionAxB) this.sugerenciasAceptadas.get(i)).getAtraccionGratis().tiempoTotal() + " horas." + nuevaLinea;
				}
				compras += "	]: el pack cuesta " + this.sugerenciasAceptadas.get(i).costoTotal() + " monedas y tiene un tiempo de " +
						this.sugerenciasAceptadas.get(i).tiempoTotal() + " horas." + nuevaLinea;
			}
			else compras += "	" + this.sugerenciasAceptadas.get(i).getNombre() + ": cuesta " + 
					this.sugerenciasAceptadas.get(i).costoTotal() + " monedas y tiene un tiempo de " +
					this.sugerenciasAceptadas.get(i).tiempoTotal() + " horas." + nuevaLinea;
		}
		return "Compras realizadas: " + nuevaLinea + compras + "Total a pagar: " + this.costoTotal() + " monedas" 
			+ nuevaLinea + "Tiempo necesario para la salida: " + this.horasNecesarias() + " horas";
	}
}
