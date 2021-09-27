package dominio;

import java.util.Comparator;

public class ComparadorDeSugerencias implements Comparator<Sugerible>{
	private TipoAtraccion preferencia;

	public ComparadorDeSugerencias(TipoAtraccion preferencia) {
		this.preferencia = preferencia;
	}

	@Override
	public int compare(Sugerible s1, Sugerible s2) {
		if(this.preferencia == s1.getTipo() && this.preferencia != s2.getTipo()) return -1;
		else if(this.preferencia != s1.getTipo() && this.preferencia == s2.getTipo()) return 1;
		else {
			if(s1.esPromocion() && !s2.esPromocion())
				return -1;
			else if(!s1.esPromocion() && s2.esPromocion()) 
				return 1;
			else {
				int comparacionCosto = Integer.compare(s2.costoTotal(), s1.costoTotal());
				return (comparacionCosto != 0) ? comparacionCosto : Double.compare(s2.tiempoTotal(), s1.tiempoTotal());
			}
		}
	}
}
