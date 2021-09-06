package dominio;

import java.util.Comparator;

public class ComparadorDeSugerencias implements Comparator<Sugerible>{
	private TipoAtraccion preferencia;
	
	public ComparadorDeSugerencias(TipoAtraccion preferencia) {
		this.preferencia = preferencia;
	}

	@Override
	public int compare(Sugerible s1, Sugerible s2) {
		if(this.preferencia == s1.getTipo() 
				&& this.preferencia != s2.getTipo()) 
			return -1;
		else if(this.preferencia != s1.getTipo() 
				&& this.preferencia == s2.getTipo()) 
			return 1;
		else {
			if(s1.esPromocion() && !s2.esPromocion())
				return -1;
			else if(!s1.esPromocion() && s2.esPromocion()) 
				return 1;
			else {
				if(s1.costoTotal() > s2.costoTotal()) 
					return -1;
				else if(s1.costoTotal() < s2.costoTotal())
					return 1;
				else {
					if(s1.tiempoTotal() > s2.tiempoTotal())
						return -1;
					else if(s1.tiempoTotal() < s2.tiempoTotal())
						return 1;
					else return 0;
				}
			}	
		}
	}
}
